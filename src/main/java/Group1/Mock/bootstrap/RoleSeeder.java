package Group1.Mock.bootstrap;

import Group1.Mock.entity.Role;
import Group1.Mock.entity.RoleEnum;
import Group1.Mock.reponsitory.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Component
@AllArgsConstructor
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private RoleRepository roleRepository;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadRoles();
    }

    private void loadRoles() {
        RoleEnum[] roles = new RoleEnum[] { RoleEnum.ADMIN, RoleEnum.STUDENT, RoleEnum.INSTRUCTOR };
        Map<RoleEnum, String> roleMap = Map.of(
                RoleEnum.ADMIN, "Administrator role",
                RoleEnum.STUDENT, "Student role",
                RoleEnum.INSTRUCTOR, "Instructor role"
        );

        Arrays.stream(roles).forEach((roleName) -> {
            Optional<Role> role = roleRepository.findByName(roleName);

            role.ifPresentOrElse(System.out::println, () -> {
                Role newRole = new Role();

                newRole.setName(roleName);
                newRole.setDescription(roleMap.get(roleName));
                roleRepository.save(newRole);
            });
        });
    }
}
