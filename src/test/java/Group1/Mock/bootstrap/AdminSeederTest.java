package Group1.Mock.bootstrap;

import Group1.Mock.entity.Role;
import Group1.Mock.entity.RoleEnum;
import Group1.Mock.entity.User;
import Group1.Mock.reponsitory.RoleRepository;
import Group1.Mock.reponsitory.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.reflect.Method;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AdminSeederTest {

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AdminSeeder adminSeeder;

    public AdminSeederTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testApplicationReady() throws Exception {
        Role role = new Role();
        role.setName(RoleEnum.ADMIN);

        User existingUser = new User();
        existingUser.setUsername("admin");

        when(roleRepository.findByName(RoleEnum.ADMIN)).thenReturn(Optional.of(role));
        when(userRepository.findByUsername("admin")).thenReturn(Optional.of(existingUser));

        ApplicationReadyEvent event = mock(ApplicationReadyEvent.class);

        adminSeeder.applicationReady(event);

        verify(roleRepository, times(1)).findByName(RoleEnum.ADMIN);
        verify(userRepository, times(1)).findByUsername("admin");
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void testLoadAdminWhenUserDoesNotExist() throws Exception {
//        Role role = new Role();
//        role.setName(RoleEnum.ADMIN);
//
//        when(roleRepository.findByName(RoleEnum.ADMIN)).thenReturn(Optional.of(role));
//        when(userRepository.findByUsername("admin")).thenReturn(Optional.empty());
//        when(encoder.encode("MockProject@123")).thenReturn("encodedPassword");
//
//        // Use reflection to access private method
//        Method method = AdminSeeder.class.getDeclaredMethod("loadAdmin");
//        method.setAccessible(true);
//        method.invoke(adminSeeder);
//
//        User newUser = new User();
//        newUser.setUsername("admin");
//        newUser.setPassword("encodedPassword");
//        newUser.setEmail("admin-mock@mockxyz.net");
//        newUser.setRole(role);
//        newUser.setEnabled(true);
//
//        verify(roleRepository, times(1)).findByName(RoleEnum.ADMIN);
//        verify(userRepository, times(1)).findByUsername("admin");
//        verify(userRepository, times(1)).save(newUser);
    }
}
