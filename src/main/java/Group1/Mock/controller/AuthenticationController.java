package Group1.Mock.controller;

import Group1.Mock.dto.*;
import Group1.Mock.entity.JwtToken;
import Group1.Mock.entity.RoleEnum;
import Group1.Mock.entity.User;
import Group1.Mock.entity.VerificationToken;
import Group1.Mock.enums.TokenType;
import Group1.Mock.exception.EmailExistException;
import Group1.Mock.exception.UsernameExistException;
import Group1.Mock.service.AuthenticationService;
import Group1.Mock.service.EmailService;
import Group1.Mock.service.JWTService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class AuthenticationController {
    private JWTService jwtService;
    private AuthenticationService authenticationService;
    private EmailService emailService;

    @PostMapping("signup")
    public ResponseEntity<BasicResponse> register(@RequestBody RegisterUserDto input) throws Exception {

        Optional<User> userOptional = authenticationService.searchUserByEmail(input.getEmail());

        if (userOptional.isPresent()) {
            throw new EmailExistException("Email này đã có người sử dụng");
        }

        userOptional = authenticationService.searchUserByUsername(input.getUsername());
        if (userOptional.isPresent()) {
            throw new UsernameExistException("Tài khoản này đã có người sử dụng");
        }


        if (input.getRole() == RoleEnum.ADMIN) {
            return new ResponseEntity<>(new BasicResponse("Không thể đăng ký tài khoản"), HttpStatus.UNAUTHORIZED);
        }

        User registeredUser = authenticationService.signup(input);
        if (input.getRole() == RoleEnum.STUDENT) {
            String token = authenticationService.createVerificationToken(registeredUser, TokenType.VERIFICATION_EMAIL);
            String link = "http://localhost:8080/auth/verify-token/" + token;
            String message = "Để xác thực địa chỉ email đã đăng ký vui lòng ấn vào <a href='" + link + "'>đây</a>.";
            String completedMessage = emailService.createMessageWithTemplate(message, registeredUser.getEmail());
            emailService.sendMimeEmail(registeredUser.getEmail(), "Xác nhận địa chỉ email của bạn", completedMessage);
        }
        return ResponseEntity.ok(new BasicResponse("Success"));
    }

    @PostMapping("signin")
    public ResponseEntity<LoginResponse> signin(@RequestBody LoginUserDto input) {
        User authenticatedUser = authenticationService.login(input);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        Date expiryDate = jwtService.extractExpiration(jwtToken);
        JwtToken token = new JwtToken();
        token.setToken(jwtToken);
        token.setExpiryDate(expiryDate);
        token.setUser(authenticatedUser);
        jwtService.saveToken(token);
        LoginResponse response = new LoginResponse();
        response.setToken(jwtToken);
        response.setExpiresIn(jwtService.getExpiration());
        return ResponseEntity.ok(response);
    }

    @GetMapping("verify-token/{token}")
    public ResponseEntity<BasicResponse> verifyEmail(@PathVariable String token) {
        Optional<VerificationToken> verificationTokenWrapper = authenticationService.getVerificationToken(token);

        if (verificationTokenWrapper.isEmpty() || !authenticationService.isTokenVerify(verificationTokenWrapper.get())) {
            return new ResponseEntity<>(new BasicResponse("Token không hợp lệ"), HttpStatus.UNAUTHORIZED);
        }

        VerificationToken verificationToken = verificationTokenWrapper.get();
        long timestamp = System.currentTimeMillis();
        if (authenticationService.isTokenExpired(verificationToken, timestamp)) {
            return new ResponseEntity<>(new BasicResponse("Token đã hết hạn"), HttpStatus.UNAUTHORIZED);
        }

        User user = verificationToken.getUser();
        user.setEnabled(true);
        authenticationService.saveRegisteredUser(user);

        // Token chỉ một lần sử dụng
        authenticationService.deleteVerificationToken(verificationToken.getToken());
        return ResponseEntity.ok(new BasicResponse("Thành công"));
    }

    @PostMapping("forgot-password")
    public ResponseEntity<BasicResponse> sendForgotPwRequest(@RequestBody ForgotPasswordRequest req) {
        Optional<User> user = authenticationService.searchUserByEmail(req.getEmail());

        if (user.isEmpty()) {
            return new ResponseEntity<>(new BasicResponse("Không tìm thấy tài khoản nào với email này"), HttpStatus.NOT_FOUND);
        }

        String token = authenticationService.createVerificationToken(user.get(), TokenType.FORGOT_PASSWORD);
        String link = "http://localhost:8080/auth/reset-password/" + token;
        String message = "Vui lòng nhấn vào <a href='" + link + "'>đây</a> để khôi phục tài khoản của bạn.";
        String completedMessage = emailService.createMessageWithTemplate(message, req.getEmail());
        emailService.sendMimeEmail(req.getEmail(), "Khôi phục mật khẩu", completedMessage);
        return ResponseEntity.ok(new BasicResponse("Thành công"));
    }

    @PostMapping("/reset-password/{token}")
    public ResponseEntity<BasicResponse> resetPassword(@PathVariable String token, @RequestBody ResetPasswordRequest req) {
        Optional<VerificationToken> verificationTokenWrapper = authenticationService.getVerificationToken(token);

        if (verificationTokenWrapper.isEmpty() || !authenticationService.isTokenResetPass(verificationTokenWrapper.get())) {
            return new ResponseEntity<>(new BasicResponse("Token không hợp lệ"), HttpStatus.UNAUTHORIZED);
        }

        VerificationToken verificationToken = verificationTokenWrapper.get();
        long timestamp = System.currentTimeMillis();
        if (authenticationService.isTokenExpired(verificationToken, timestamp)) {
            return new ResponseEntity<>(new BasicResponse("Token đã hết hạn"), HttpStatus.UNAUTHORIZED);
        }

        User user = verificationToken.getUser();
        authenticationService.setNewPassword(user, req.getPassword());
        authenticationService.saveRegisteredUser(user);

        // Token chỉ một lần sử dụng
        authenticationService.deleteVerificationToken(verificationToken.getToken());
        return ResponseEntity.ok(new BasicResponse("Thành công"));
    }
}
