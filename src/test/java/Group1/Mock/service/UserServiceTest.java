package Group1.Mock.service;

import Group1.Mock.dto.UserDto;
import Group1.Mock.dto.UserProfileDto;
import Group1.Mock.entity.*;
import Group1.Mock.reponsitory.UserProfileRepository;
import Group1.Mock.reponsitory.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserProfileRepository userProfileRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void allUsers() {
        // Mock data
        User user1 = new User();
        User user2 = new User();
        List<User> users = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(users);

        // Call the method
        List<User> result = userService.allUsers();

        // Verify results
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(userRepository).findAll();
    }

    @Test
    void listUserByRole() {
        // Mock data
        RoleEnum roleEnum = RoleEnum.ADMIN;
        User user = new User();
        Role role = new Role();
        role.setName(roleEnum);
        user.setRole(role);
        List<User> allUsers = Arrays.asList(user);

        when(userRepository.findAll()).thenReturn(allUsers);

        // Call the method
        List<User> result = userService.listUserByRole(roleEnum);

        // Verify results
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(roleEnum, result.get(0).getRole().getName());
        verify(userRepository).findAll();
    }

    @Test
    void setNewPassword() {
        // Mock data
        User user = new User();
        String newPassword = "newPassword";
        String encodedPassword = "encodedPassword";

        // Cấu hình mock để trả về giá trị mã hóa giả
        when(passwordEncoder.encode(newPassword)).thenReturn(encodedPassword);

        // Call the method
        userService.setNewPassword(newPassword, user);

        // Verify results
        verify(passwordEncoder).encode(newPassword);
        assertNotNull(user.getPassword());
        assertEquals(encodedPassword, user.getPassword());
    }


    @Test
    void saveUserState() {
        // Mock data
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);

        // Call the method
        User result = userService.saveUserState(user);

        // Verify results
        assertNotNull(result);
        verify(userRepository).save(user);
    }

    @Test
    void isPasswordEqual() {
        // Mock data
        User user = new User();
        String password = "password";
        String encodedPassword = "encodedPassword";

        when(passwordEncoder.matches(password, user.getPassword())).thenReturn(true);

        // Call the method
        boolean result = userService.isPasswordEqual(password, user);

        // Verify results
        assertTrue(result);
        verify(passwordEncoder).matches(password, user.getPassword());
    }

    @Test
    void saveUserProfile() {
        // Mock data
        UserProfile userProfile = new UserProfile();

        // Call the method
        userService.saveUserProfile(userProfile);

        // Verify results
        verify(userProfileRepository).save(userProfile);
    }

    @Test
    void findProfileByUser() {
        // Mock data
        User user = new User();
        UserProfile userProfile = new UserProfile();
        when(userProfileRepository.findByUser(user)).thenReturn(Optional.of(userProfile));

        // Call the method
        Optional<UserProfile> result = userService.findProfileByUser(user);

        // Verify results
        assertTrue(result.isPresent());
        assertEquals(userProfile, result.get());
        verify(userProfileRepository).findByUser(user);
    }

//    @Test
//    void addSubscribe() {
//        // Mock data
//        User user = new User();
//        User instructor = new User();
//
//        // Call the method
//        userService.addSubscribe(user, instructor);
//
//        // Verify results
//        verify(user).addSubscription(instructor);
//        verify(userService).saveUserState(user);
//    }


//    @Test
//    void listUserSubscription() {
//        // Mock data
//        User user = mock(User.class);
//        User subscription = new User();
//        UserProfile profile = new UserProfile();
//        profile.setFirstName("John");
//        profile.setLastName("Doe");
//        profile.setAvatar("avatar.png");
//        subscription.setProfile(profile);
//
//        // Thiết lập các thuộc tính mock
//        when(user.getSubscriptions()).thenReturn(Set.of(subscription));
//        when(subscription.getId()).thenReturn(1L);
//        when(subscription.getUsername()).thenReturn("subscriptionUser");
//        when(subscription.getEmail()).thenReturn("subscription@example.com");
//
//        // Chuyển đổi đối tượng User thành UserDto
//        UserDto userDto = new UserDto();
//        userDto.setId(subscription.getId());
//        userDto.setUsername(subscription.getUsername());
//        userDto.setEmail(subscription.getEmail());
//
//        UserProfileDto profileDto = new UserProfileDto();
//        profileDto.setFirstName(profile.getFirstName());
//        profileDto.setLastName(profile.getLastName());
//        profileDto.setAvatar(profile.getAvatar());
//
//        userDto.setProfile(profileDto);
//
//        // Mock phương thức chuyển đổi
//        when(userService.listUserSubscription(user)).thenReturn(Set.of(userDto));
//
//        // Call the method
//        Set<UserDto> result = userService.listUserSubscription(user);
//
//        // Verify results
//        assertNotNull(result);
//        assertEquals(1, result.size());
//        UserDto dto = result.iterator().next();
//        assertEquals(subscription.getId(), dto.getId());
//        assertEquals(subscription.getUsername(), dto.getUsername());
//        assertEquals(profile.getFirstName(), dto.getProfile().getFirstName());
//        assertEquals(profile.getLastName(), dto.getProfile().getLastName());
//        assertEquals(profile.getAvatar(), dto.getProfile().getAvatar());
//        verify(user).getSubscriptions();
//    }


//    @Test
//    void unsubscribe() {
//        // Mock data
//        User user = mock(User.class);
//        Long id = 1L;
//
//        // Call the method
//        userService.unsubscribe(user, id);
//
//        // Verify results
//        verify(user).unsubscribe(id);
//        verify(userService).saveUserState(user);
//    }

    @Test
    void findUserId() {
        // Mock data
        Long id = 1L;
        User user = new User();
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        // Call the method
        Optional<User> result = userService.findUserId(id);

        // Verify results
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
        verify(userRepository).findById(id);
    }

    @Test
    void findByFirstName() {
        // Mock data
        String firstName = "John";
        UserProfile userProfile = new UserProfile();
        userProfile.setFirstName(firstName);
        List<UserProfile> profiles = Arrays.asList(userProfile);

        when(userProfileRepository.findAllByFirstName(firstName)).thenReturn(profiles);

        // Call the method
        List<UserProfile> result = userService.findByFirstName(firstName);

        // Verify results
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(firstName, result.get(0).getFirstName());
        verify(userProfileRepository).findAllByFirstName(firstName);
    }

    @Test
    void findByLastName() {
        // Mock data
        String lastName = "Doe";
        UserProfile userProfile = new UserProfile();
        userProfile.setLastName(lastName);
        List<UserProfile> profiles = Arrays.asList(userProfile);

        when(userProfileRepository.findAllByLastName(lastName)).thenReturn(profiles);

        // Call the method
        List<UserProfile> result = userService.findByLastName(lastName);

        // Verify results
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(lastName, result.get(0).getLastName());
        verify(userProfileRepository).findAllByLastName(lastName);
    }

    @Test
    void search() {
        // Mock data
        String keyword = "John";
        UserProfile userProfile = new UserProfile();
        List<UserProfile> profiles = Arrays.asList(userProfile);

        when(userProfileRepository.search(keyword)).thenReturn(profiles);

        // Call the method
        List<UserProfile> result = userService.search(keyword);

        // Verify results
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(userProfileRepository).search(keyword);
    }

    @Test
    void findByUsername() {
        // Mock data
        String username = "john_doe";
        User user = new User();
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        // Call the method
        User result = userService.findByUsername(username);

        // Verify results
        assertNotNull(result);
        assertEquals(user, result);
        verify(userRepository).findByUsername(username);
    }
}
