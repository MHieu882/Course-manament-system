package Group1.Mock.service;

import Group1.Mock.dto.LoginUserDto;
import Group1.Mock.dto.RegisterUserDto;
import Group1.Mock.entity.Role;
import Group1.Mock.entity.RoleEnum;
import Group1.Mock.entity.User;
import Group1.Mock.entity.VerificationToken;
import Group1.Mock.enums.TokenType;
import Group1.Mock.reponsitory.RoleRepository;
import Group1.Mock.reponsitory.UserRepository;
import Group1.Mock.reponsitory.VerificationTokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class AuthenticationServiceTest {

    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private VerificationTokenRepository verificationTokenRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void testSignup_Success() throws Exception {
//        RegisterUserDto registerUserDto = new RegisterUserDto("username", "password", "email@example.com", RoleEnum.STUDENT);
//        Role role = new Role();
//        User user = new User("username", "encodedPassword", "email@example.com", role);
//
//        when(roleRepository.findByName(RoleEnum.STUDENT)).thenReturn(Optional.of(role));
//        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
//        when(userRepository.save(user)).thenReturn(user);
//
//        User savedUser = authenticationService.signup(registerUserDto);
//
//        assertThat(savedUser).isEqualTo(user);
//        verify(userRepository).save(user);
//    }

    @Test
    void testSignup_RoleNotFound() {
        RegisterUserDto registerUserDto = new RegisterUserDto("username", "password", "email@example.com", RoleEnum.STUDENT);

//        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> authenticationService.signup(registerUserDto))
                .isInstanceOf(Exception.class)
                .hasMessage("Role must not be null");
    }

    @Test
    void testLogin_Success() {
        LoginUserDto loginUserDto = new LoginUserDto("username", "password");
        User user = new User("username", "encodedPassword", "email@example.com", new Role());

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        when(userRepository.findByUsername("username")).thenReturn(Optional.of(user));

        User loggedInUser = authenticationService.login(loginUserDto);

        assertThat(loggedInUser).isEqualTo(user);
        verify(userRepository).findByUsername("username");
    }

//    @Test
//    void testCreateVerificationToken() {
//        User user = new User();
//        String token = UUID.randomUUID().toString();
//        VerificationToken verificationToken = new VerificationToken(token, user, TokenType.VERIFICATION_EMAIL);
//
//        when(verificationTokenRepository.save(any(VerificationToken.class))).thenReturn(verificationToken);
//
//        String createdToken = authenticationService.createVerificationToken(user, TokenType.VERIFICATION_EMAIL);
//
//        assertThat(createdToken).isEqualTo(token);
//        verify(verificationTokenRepository).save(any(VerificationToken.class));
//    }

    @Test
    void testIsTokenExpired() {
        VerificationToken token = new VerificationToken();
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.add(Calendar.HOUR, 1);
        token.setExpiryDate(expiryDate.getTime());

        long timestamp = Calendar.getInstance().getTimeInMillis();

        boolean expired = authenticationService.isTokenExpired(token, timestamp);

        assertThat(expired).isFalse();
    }

    @Test
    void testDeleteVerificationToken() {
        String token = "someToken";

        authenticationService.deleteVerificationToken(token);

        verify(verificationTokenRepository).deleteByToken(token);
    }

    @Test
    void testSetNewPassword() {
        User user = new User();
        String newPassword = "newPassword";

        when(passwordEncoder.encode(newPassword)).thenReturn("encodedNewPassword");

        authenticationService.setNewPassword(user, newPassword);

        assertThat(user.getPassword()).isEqualTo("encodedNewPassword");
    }
}
