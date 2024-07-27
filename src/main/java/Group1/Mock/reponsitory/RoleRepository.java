package Group1.Mock.reponsitory;

import Group1.Mock.entity.Role;
import Group1.Mock.entity.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleEnum name);
}
