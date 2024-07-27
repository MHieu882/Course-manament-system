//package Group1.Mock.controller;
//
//import Group1.Mock.entity.Role;
//import Group1.Mock.enums.ApproveType;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.never;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import Group1.Mock.controller.AdminController;
//import Group1.Mock.dto.ApproveRequest;
//import Group1.Mock.dto.BasicResponse;
//import Group1.Mock.entity.RoleEnum;
//import Group1.Mock.entity.User;
//import Group1.Mock.service.EmailService;
//import Group1.Mock.service.UserService;
//import Group1.Mock.service.CourseService;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Optional;
//
//@ExtendWith(MockitoExtension.class)
//public class AdminControllerTest {
//
//    @Mock
//    private UserService userService;
//
//    @Mock
//    private CourseService courseService;
//
//    @Mock
//    private EmailService emailService;
//
//    @InjectMocks
//    private AdminController adminController;
//
//    private ApproveRequest approveRequest;
//    private User instructor;
//
//    @BeforeEach
//    public void setUp() {
//
//        approveRequest = new ApproveRequest();
//        approveRequest.setId(1L);
//        approveRequest.setStatus(ApproveType.APPROVED);
//        instructor = new User();
//        instructor.setId(1L);
//        Role role = new Role();
//        role.setId(1L);
//        role.setName(RoleEnum.INSTRUCTOR);
//        instructor.setRole(role);
//        instructor.setEmail("john@example.com");
//        instructor.setUsername("john");
//        instructor.setPassword("password");
//        instructor.setEnabled(true);
//        instructor.setBlocked(false);
//    }
//    @Test
//    public void testApproveInstructor_notInstructor() {
//        Role role = new Role();
//        role.setId(2L);
//        role.setName(RoleEnum.STUDENT);
//        instructor.setRole(role);
//        when(userService.findUserId(anyLong())).thenReturn(Optional.of(instructor));
//        ResponseEntity<BasicResponse> response = adminController.approveInstructor(approveRequest);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertEquals("Tài khoản này không phải giảng viên", response.getBody().getMessage());
//        verify(userService, never()).saveUserState(any(User.class));
//        verify(emailService, never()).sendMimeEmail(anyString(), anyString(), anyString());
//    }
//}
package Group1.Mock.controller;

import Group1.Mock.dto.ApproveRequest;
import Group1.Mock.dto.BasicResponse;
import Group1.Mock.dto.BlockUserRequest;
import Group1.Mock.entity.Course;
import Group1.Mock.entity.Role;
import Group1.Mock.entity.RoleEnum;
import Group1.Mock.entity.User;
import Group1.Mock.enums.ApproveType;
import Group1.Mock.exception.SearchNotFoundException;
import Group1.Mock.service.CourseService;
import Group1.Mock.service.EmailService;
import Group1.Mock.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class AdminControllerTest {

    @InjectMocks
    private AdminController adminController;

    @Mock
    private UserService userService;

    @Mock
    private CourseService courseService;

    @Mock
    private EmailService emailService;
    @Mock
    private ApproveRequest approveRequest;
    @Mock
    private User instructor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testApproveInstructor_notInstructor() {
                    approveRequest = new ApproveRequest();
        approveRequest.setId(1L);
        approveRequest.setStatus(ApproveType.APPROVED);
        instructor = new User();
        instructor.setId(1L);
        Role role = new Role();
        role.setId(1L);
        role.setName(RoleEnum.INSTRUCTOR);
        instructor.setRole(role);
        instructor.setEmail("john@example.com");
        instructor.setUsername("john");
        instructor.setPassword("password");
        instructor.setEnabled(true);
        instructor.setBlocked(false);

        Role role1 = new Role();
        role1.setId(2L);
        role1.setName(RoleEnum.STUDENT);
        instructor.setRole(role1);
        when(userService.findUserId(anyLong())).thenReturn(Optional.of(instructor));
        ResponseEntity<BasicResponse> response = adminController.approveInstructor(approveRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Tài khoản này không phải giảng viên", response.getBody().getMessage());
        verify(userService, never()).saveUserState(any(User.class));
        verify(emailService, never()).sendMimeEmail(anyString(), anyString(), anyString());
    }
    @Test
    void approveInstructor_UserNotFound_ThrowsException() {


        ApproveRequest request = new ApproveRequest();
        request.setId(1L);
        request.setStatus(ApproveType.APPROVED);

        when(userService.findUserId(1L)).thenReturn(Optional.empty());

        SearchNotFoundException thrown =
                org.junit.jupiter.api.Assertions.assertThrows(
                        SearchNotFoundException.class,
                        () -> adminController.approveInstructor(request)
                );

        assertThat(thrown).hasMessage("Không tìm thấy tài khoản");
    }

    @Test
    void approveInstructor_NotInstructor_ReturnsBadRequest() {
        Role role = new Role();
        role.setId(2L);
        role.setName(RoleEnum.STUDENT);
        User user = new User();
        user.setRole(role); // Ensure RoleEnum is properly set up

        ApproveRequest request = new ApproveRequest();
        request.setId(1L);
        request.setStatus(ApproveType.APPROVED);

        when(userService.findUserId(1L)).thenReturn(Optional.of(user));

        ResponseEntity<BasicResponse> response = adminController.approveInstructor(request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//        assertThat(response.getBody().getMessage()).isEqualTo("Tài khoản này không phải giảng viên");
    }

    @Test
    void approveInstructor_Approved_Success() {
        Role role = new Role();
        role.setId(2L);
        role.setName(RoleEnum.ADMIN);
        User user = new User();
        user.setRole(role);
        user.getRole().setName(RoleEnum.INSTRUCTOR);
        user.setEmail("test@example.com");

        ApproveRequest request = new ApproveRequest();
        request.setId(1L);
        request.setStatus(ApproveType.APPROVED);

        when(userService.findUserId(1L)).thenReturn(Optional.of(user));
        doNothing().when(emailService).sendMimeEmail(anyString(), anyString(), anyString());

        ResponseEntity<BasicResponse> response = adminController.approveInstructor(request);

//        verify(emailService).sendMimeEmail(eq("test@example.com"), anyString(), anyString());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody().getMessage()).isEqualTo("Thành công");
        assertThat(user.isEnabled()).isTrue();
    }

    @Test
    void approveCourse_CourseNotFound_ThrowsException() {
        ApproveRequest request = new ApproveRequest();
        request.setId(1L);
        request.setStatus(ApproveType.APPROVED);

        when(courseService.searchById(1L)).thenReturn(Optional.empty());

        SearchNotFoundException thrown =
                org.junit.jupiter.api.Assertions.assertThrows(
                        SearchNotFoundException.class,
                        () -> adminController.approveCourse(request)
                );

        assertThat(thrown).hasMessage("Không tìm thấy khóa học");
    }

    @Test
    void blockUser_UserNotFound_ThrowsException() {
        BlockUserRequest request = new BlockUserRequest();
        request.setId(1L);

        when(userService.findUserId(1L)).thenReturn(Optional.empty());

        SearchNotFoundException thrown =
                org.junit.jupiter.api.Assertions.assertThrows(
                        SearchNotFoundException.class,
                        () -> adminController.blockUser(request)
                );

        assertThat(thrown).hasMessage("Không tìm thấy tài khoản này");
    }

    @Test
    void blockUser_AdminUser_ReturnsBadRequest() {
        Role role = new Role();
        role.setId(2L);
        role.setName(RoleEnum.ADMIN);
        User user = new User();
        user.setRole(role); // Ensure RoleEnum is properly set up
        user.getRole().setName(RoleEnum.ADMIN);

        BlockUserRequest request = new BlockUserRequest();
        request.setId(1L);

        when(userService.findUserId(1L)).thenReturn(Optional.of(user));

        ResponseEntity<BasicResponse> response = adminController.blockUser(request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getMessage()).isEqualTo("Không thể thực hiện tác vụ");
    }

    @Test
    void blockUser_Success() {
        Role role = new Role();
        role.setId(2L);
        role.setName(RoleEnum.STUDENT);
        User user = new User();
        user.setRole(role); // Ensure RoleEnum is properly set up

        BlockUserRequest request = new BlockUserRequest();
        request.setId(1L);

        when(userService.findUserId(1L)).thenReturn(Optional.of(user));
//        doNothing().when(userService).saveUserState(user);

        ResponseEntity<BasicResponse> response = adminController.blockUser(request);

        verify(userService).saveUserState(user);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody().getMessage()).isEqualTo("Thành công");
        assertThat(user.isBlocked()).isTrue();
    }
}
