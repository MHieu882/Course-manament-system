package Group1.Mock.service;

import Group1.Mock.dto.BasicResponse;
import Group1.Mock.dto.LoginUserDto;
import Group1.Mock.dto.RegisterUserDto;
import Group1.Mock.entity.Role;
import Group1.Mock.entity.User;
import Group1.Mock.entity.VerificationToken;
import Group1.Mock.enums.TokenType;
import Group1.Mock.reponsitory.RoleRepository;
import Group1.Mock.reponsitory.UserRepository;
import Group1.Mock.reponsitory.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private RoleRepository roleRepository;
    private VerificationTokenRepository verificationTokenRepository;

    public User signup(RegisterUserDto input) throws Exception {
        Optional<Role> optionalRole = roleRepository.findByName(input.getRole());

        if (optionalRole.isEmpty()) {
            throw new Exception("Role must not be null");
        }

        User user = new User(
                input.getUsername(),
                passwordEncoder.encode(input.getPassword()),
                input.getEmail(),
                optionalRole.get()
        );


        return userRepository.save(user);
    }

    public User login(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        return userRepository.findByUsername(input.getUsername())
                .orElseThrow();
    }

    public Optional<User> searchUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> searchUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public String createVerificationToken(User user, TokenType type) {
        String token = UUID.randomUUID().toString();
        VerificationToken newToken = new VerificationToken(token, user, type);
        verificationTokenRepository.save(newToken);
        return token;
    }

    public Optional<VerificationToken> getVerificationToken(String VerificationToken) {
        return verificationTokenRepository.findByToken(VerificationToken);
    }

    public boolean isTokenExpired(VerificationToken token, long timestamp) {
        return (token.getExpiryDate().getTime() - timestamp) <= 0;
    }

    public boolean isTokenVerify(VerificationToken token) {
        return token.getType() == TokenType.VERIFICATION_EMAIL;
    }

    public boolean isTokenResetPass(VerificationToken token) {
        return token.getType() == TokenType.FORGOT_PASSWORD;
    }

    public void setNewPassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
    }

    @Transactional
    public void deleteVerificationToken(String token) {
        verificationTokenRepository.deleteByToken(token);
    }

    public User saveRegisteredUser(User user) {
        return userRepository.save(user);
    }
}
