package Group1.Mock.reponsitory;

import Group1.Mock.entity.Course;
import Group1.Mock.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
   void deleteBycourses(Course course);
   Page<Review> findByUserId(Long userId, Pageable pageable);
   Page<Review> findByCoursesCourseId(Long courseId, Pageable pageable);
}
