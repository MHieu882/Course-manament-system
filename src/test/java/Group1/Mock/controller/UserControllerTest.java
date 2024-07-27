package Group1.Mock.controller;

import Group1.Mock.Mapper.UserMapper;
import Group1.Mock.dto.*;
import Group1.Mock.entity.RoleEnum;
import Group1.Mock.entity.User;
import Group1.Mock.entity.UserProfile;
import Group1.Mock.exception.SearchNotFoundException;
import Group1.Mock.service.JWTService;
import Group1.Mock.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private JWTService jwtService;

    @Mock
    private Authentication authentication;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void testAllUsers() {
        // Setup
        List<User> users = Arrays.asList(new User(), new User());
        when(userService.allUsers()).thenReturn(users);
//        when(UserMapper.mapToUserDto(any(User.class))).thenReturn(new UserDto());

        // Execute
        ResponseEntity<List<UserDto>> response = userController.allUsers();

        // Verify
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(2);
        verify(userService, times(1)).allUsers();
    }

    @Test
    void testChangePassword_Success() {
        // Setup
        User currentUser = new User();
        when(authentication.getPrincipal()).thenReturn(currentUser);
        ChangePasswordRequest request = new ChangePasswordRequest();
        request.setPassword("oldPassword");
        request.setNewPassword("newPassword");

        when(userService.isPasswordEqual("oldPassword", currentUser)).thenReturn(true);

        // Execute
        ResponseEntity<BasicResponse> response = userController.changePassword(request);

        // Verify
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody().getMessage()).isEqualTo("Thành công");
        verify(userService, times(1)).setNewPassword("newPassword", currentUser);
        verify(userService, times(1)).saveUserState(currentUser);
    }

    @Test
    void testChangePassword_Failure() {
        // Setup
        User currentUser = new User();
        when(authentication.getPrincipal()).thenReturn(currentUser);
        ChangePasswordRequest request = new ChangePasswordRequest();
        request.setPassword("wrongPassword");
        request.setNewPassword("newPassword");

        when(userService.isPasswordEqual("wrongPassword", currentUser)).thenReturn(false);

        // Execute
        ResponseEntity<BasicResponse> response = userController.changePassword(request);

        // Verify
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getMessage()).isEqualTo("Sai mật khẩu");
        verify(userService, never()).setNewPassword(anyString(), any(User.class));
    }

//    @Test
//    void testAddSubscription_Success() {
//        // Setup
//        User currentUser = new User();
//        User instructor = new User();
//        when(authentication.getPrincipal()).thenReturn(currentUser);
//        when(userService.findUserId(1L)).thenReturn(Optional.of(instructor));
//        when(instructor.getRole().getName()).thenReturn(RoleEnum.INSTRUCTOR);
//
////        AddSubscriptionRequest request = new AddSubscriptionRequest();
////        request.setInstructorId(1L);
//
//        // Execute
////        ResponseEntity<BasicResponse> response = userController.addSubscription(request);
//
//        // Verify
////        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
////        assertThat(response.getBody().getMessage()).isEqualTo("Thành công");
//        verify(userService, times(1)).addSubscribe(currentUser, instructor);
//    }

    @Test
    void testFindUserById_UserNotFound() {
        // Setup
        when(userService.findUserId(1L)).thenReturn(Optional.empty());

        // Execute & Verify
        assertThatThrownBy(() -> userController.findUserById(1L))
                .isInstanceOf(SearchNotFoundException.class)
                .hasMessageContaining("User is not found");
    }

    @Test
    void testLogout() {
        // Setup
        String token = "someToken";
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);

        // Execute
        ResponseEntity<BasicResponse> response = userController.logout(request);

        // Verify
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody().getMessage()).isEqualTo("Đăng xuất thành công");
        verify(jwtService, times(1)).deleteToken(token);
    }
}
