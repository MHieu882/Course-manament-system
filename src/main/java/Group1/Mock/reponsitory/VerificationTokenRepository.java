package Group1.Mock.reponsitory;

import Group1.Mock.entity.User;
import Group1.Mock.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
    Optional<VerificationToken> findByUser(User user);

    void deleteByToken(String token);
}
