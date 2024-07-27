package Group1.Mock.reponsitory;

import Group1.Mock.entity.User;
import Group1.Mock.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByUser(User user);

    List<UserProfile> findAllByFirstName(String firstName);

    List<UserProfile> findAllByLastName(String lastName);

    @Query("SELECT up FROM UserProfile up WHERE up.firstName LIKE %?1% OR up.lastName LIKE %?1%")
    List<UserProfile> search(String keyword);
}
