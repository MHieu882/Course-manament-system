package Group1.Mock.reponsitory;

import Group1.Mock.dto.CourseDto;
import Group1.Mock.entity.Category;
import Group1.Mock.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT c FROM Course  c WHERE c.courseName LIKE ?1%")
    List<Course> searchByCourseNameLike(@Param("courseName") String courseName);
//    Page<Course> findByCategoryId(Long categoryId, Pageable pageable);
}
