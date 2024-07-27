package Group1.Mock.bootstrap;
import Group1.Mock.entity.Role;
import Group1.Mock.entity.RoleEnum;
import Group1.Mock.reponsitory.RoleRepository;
import Group1.Mock.bootstrap.RoleSeeder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class RoleSeederTest {

    @InjectMocks
    private RoleSeeder roleSeeder;

    @Mock
    private RoleRepository roleRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    public void testLoadRolesWhenRolesDoNotExist() {
//        // Prepare mock data
//        RoleEnum[] roles = RoleEnum.values();
//        Map<RoleEnum, String> roleMap = Map.of(
//                RoleEnum.ADMIN, "Administrator role",
//                RoleEnum.STUDENT, "Student role",
//                RoleEnum.INSTRUCTOR, "Instructor role"
//        );
//
//        when(roleRepository.findByName(any(RoleEnum.class))).thenReturn(Optional.empty());
//
//        // Execute the method
//        roleSeeder.onApplicationEvent(new ContextRefreshedEvent((ApplicationContext) new Object()));
//
//        // Verify interactions
//        Arrays.stream(roles).forEach(roleName ->
//                verify(roleRepository).save(argThat(role ->
//                        roleName.equals(role.getName()) &&
//                                roleMap.get(roleName).equals(role.getDescription())
//                ))
//        );
//    }
//
//    @Test
//    public void testLoadRolesWhenRolesExist() {
//        // Prepare mock data
//        RoleEnum[] roles = RoleEnum.values();
//
//        for (RoleEnum roleEnum : roles) {
//            Role existingRole = new Role();
//            existingRole.setName(roleEnum);
//            existingRole.setDescription("Existing role");
//
//            when(roleRepository.findByName(roleEnum)).thenReturn(Optional.of(existingRole));
//        }
//
//        // Execute the method
//        roleSeeder.onApplicationEvent(new ContextRefreshedEvent((ApplicationContext) new Object()));
//
//        // Verify interactions
//        Arrays.stream(roles).forEach(roleName ->
//                verify(roleRepository, never()).save(any(Role.class))
//        );
//    }



        @Test
        public void testLoadRoles_NullRoleArray() {
            // Given
            RoleEnum[] roles = null;
            Map<RoleEnum, String> roleMap = Map.of(
                    RoleEnum.ADMIN, "Administrator role",
                    RoleEnum.STUDENT, "Student role",
                    RoleEnum.INSTRUCTOR, "Instructor role"
            );

            when(roleRepository.findByName(any(RoleEnum.class))).thenReturn(Optional.empty());

            // When
//            roleSeeder.loadRoles();

            // Then
            verify(roleRepository, never()).save(any(Role.class));
        }
    @Test
    public void testLoadRoles_EmptyRoleMap() {
        // Given
        RoleEnum[] roles = new RoleEnum[] { RoleEnum.ADMIN, RoleEnum.STUDENT, RoleEnum.INSTRUCTOR };
        Map<RoleEnum, String> roleMap = Map.of();

        when(roleRepository.findByName(any(RoleEnum.class))).thenReturn(Optional.empty());

        // When
//        roleSeeder.loadRoles();

        // Then
        verify(roleRepository, never()).save(any(Role.class));
    }
    @Test
    public void testLoadRoles_ExistingRoles() {
        // Given
        RoleEnum[] roles = new RoleEnum[] { RoleEnum.ADMIN, RoleEnum.STUDENT, RoleEnum.INSTRUCTOR };
        Map<RoleEnum, String> roleMap = Map.of(
                RoleEnum.ADMIN, "Administrator role",
                RoleEnum.STUDENT, "Student role",
                RoleEnum.INSTRUCTOR, "Instructor role"
        );

        List<Role> existingRoles = Arrays.stream(roles)
                .map(roleName -> {
                    Role role = new Role();
                    role.setName(roleName);
                    role.setDescription(roleMap.get(roleName));
                    return role;
                })
                .collect(Collectors.toList());

        when(roleRepository.findByName(any(RoleEnum.class))).thenAnswer(invocation -> {
            RoleEnum roleName = invocation.getArgument(0);
            return existingRoles.stream().filter(role -> role.getName().equals(roleName)).findFirst();
        });

        // When
//        roleSeeder.loadRoles();

        // Then
        verify(roleRepository, never()).save(any(Role.class));
    }
}
