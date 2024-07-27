package Group1.Mock.reponsitory;

import Group1.Mock.entity.Order;
import Group1.Mock.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findByStudentId(Long studentId, Pageable pageable);
    List<Order> findByStudentId(Long studentId);

}

