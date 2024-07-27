package Group1.Mock.bootstrap;

import Group1.Mock.entity.Role;
import Group1.Mock.entity.RoleEnum;
import Group1.Mock.entity.User;
import Group1.Mock.reponsitory.RoleRepository;
import Group1.Mock.reponsitory.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class AdminSeeder {

    private PasswordEncoder encoder;
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private static Logger logger = LoggerFactory.getLogger(AdminSeeder.class);


    @EventListener(ApplicationReadyEvent.class)
    public void applicationReady(ApplicationReadyEvent event) {
        logger.info("Adding admin account");
        loadAdmin();
    }

    private void loadAdmin() {
        Role role;
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.ADMIN);
        role = optionalRole.orElse(null);

        Optional<User> userOptional = userRepository.findByUsername("admin");

        userOptional.ifPresentOrElse(System.out::println, () -> {
            User user = new User();
            user.setUsername("admin");
            user.setPassword(encoder.encode("MockProject@123"));
            user.setEmail("admin-mock@mockxyz.net");
            user.setRole(role);
            user.setEnabled(true);
            userRepository.save(user);
        });
    }
}
