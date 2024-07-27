package Group1.Mock.reponsitory;

import Group1.Mock.entity.Orderitem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<Orderitem, Long> {
    List<Orderitem> findByOrderId(Long id);
}

