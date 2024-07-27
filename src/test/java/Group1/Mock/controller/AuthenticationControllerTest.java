package Group1.Mock.controller;

import Group1.Mock.dto.*;
import Group1.Mock.entity.*;
import Group1.Mock.enums.TokenType;
import Group1.Mock.exception.EmailExistException;
import Group1.Mock.exception.UsernameExistException;
import Group1.Mock.service.AuthenticationService;
import Group1.Mock.service.EmailService;
import Group1.Mock.service.JWTService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthenticationControllerTest {

    @InjectMocks
    private AuthenticationController authenticationController;

    @Mock
    private JWTService jwtService;

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterSuccess() throws Exception {
        RegisterUserDto input = new RegisterUserDto("username","123", "email@example.com", RoleEnum.STUDENT);
        User registeredUser = new User();
        registeredUser.setEmail("email@example.com");
        registeredUser.setUsername("username");

        when(authenticationService.searchUserByEmail(anyString())).thenReturn(Optional.empty());
        when(authenticationService.searchUserByUsername(anyString())).thenReturn(Optional.empty());
        when(authenticationService.signup(any(RegisterUserDto.class))).thenReturn(registeredUser);
        when(authenticationService.createVerificationToken(any(User.class), any(TokenType.class))).thenReturn("token");

        ResponseEntity<BasicResponse> response = authenticationController.register(input);

//        verify(emailService).sendMimeEmail(anyString(), anyString(), anyString());
        assert response.getStatusCode() == HttpStatus.OK;
//        assert response.getBody().getMessage().equals("Success");
    }

    @Test
    void testRegisterEmailExists() throws Exception {

        RegisterUserDto input = new RegisterUserDto("username","123", "email@example.com", RoleEnum.STUDENT);
        when(authenticationService.searchUserByEmail(anyString())).thenReturn(Optional.of(new User()));

        try {
            authenticationController.register(input);
        } catch (EmailExistException e) {
            assert e.getMessage().equals("Email này đã có người sử dụng");
        }
    }

    @Test
    void testSigninSuccess() {
        LoginUserDto input = new LoginUserDto("username", "password");
        User authenticatedUser = new User();
        String jwtToken = "jwtToken";
        Date expiryDate = Calendar.getInstance().getTime();

        when(authenticationService.login(any(LoginUserDto.class))).thenReturn(authenticatedUser);
        when(jwtService.generateToken(any(User.class))).thenReturn(jwtToken);
        when(jwtService.extractExpiration(anyString())).thenReturn(expiryDate);
        when(jwtService.getExpiration()).thenReturn(3600L);

        ResponseEntity<LoginResponse> response = authenticationController.signin(input);

        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody().getToken().equals(jwtToken);
        assert response.getBody().getExpiresIn() == 3600L;
    }

    @Test
    void testVerifyEmailSuccess() {
        String token = "validToken";
        User user = new User();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        when(authenticationService.getVerificationToken(anyString())).thenReturn(Optional.of(verificationToken));
        when(authenticationService.isTokenVerify(any(VerificationToken.class))).thenReturn(true);
        when(authenticationService.isTokenExpired(any(VerificationToken.class), anyLong())).thenReturn(false);

        ResponseEntity<BasicResponse> response = authenticationController.verifyEmail(token);

        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody().getMessage().equals("Thành công");
        verify(authenticationService).saveRegisteredUser(any(User.class));
        verify(authenticationService).deleteVerificationToken(anyString());
    }

    @Test
    void testSendForgotPwRequestSuccess() {
        ForgotPasswordRequest request = new ForgotPasswordRequest("email@example.com");
        User user = new User();
        when(authenticationService.searchUserByEmail(anyString())).thenReturn(Optional.of(user));
        when(authenticationService.createVerificationToken(any(User.class), any(TokenType.class))).thenReturn("token");

        ResponseEntity<BasicResponse> response = authenticationController.sendForgotPwRequest(request);

        assert response.getStatusCode() == HttpStatus.OK;
//        assert response.getBody().getMessage().equals("Thành công");
//        verify(emailService).sendMimeEmail(anyString(), anyString(), anyString());
    }

    @Test
    void testResetPasswordSuccess() {
        String token = "validToken";
        ResetPasswordRequest request = new ResetPasswordRequest("newPassword");
        User user = new User();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        when(authenticationService.getVerificationToken(anyString())).thenReturn(Optional.of(verificationToken));
        when(authenticationService.isTokenResetPass(any(VerificationToken.class))).thenReturn(true);
        when(authenticationService.isTokenExpired(any(VerificationToken.class), anyLong())).thenReturn(false);

        ResponseEntity<BasicResponse> response = authenticationController.resetPassword(token, request);

        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody().getMessage().equals("Thành công");
        verify(authenticationService).setNewPassword(any(User.class), anyString());
        verify(authenticationService).saveRegisteredUser(any(User.class));
        verify(authenticationService).deleteVerificationToken(anyString());
    }
}
