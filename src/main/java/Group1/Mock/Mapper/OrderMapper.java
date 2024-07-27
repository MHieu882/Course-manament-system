package Group1.Mock.Mapper;

import Group1.Mock.dto.OrderDto;
import Group1.Mock.entity.*;
import Group1.Mock.reponsitory.CourseRepository;
import Group1.Mock.reponsitory.OrderItemRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class OrderMapper {
    private static OrderItemRepository orderItemRepository;
    public static OrderDto mapToOrderDto(Order order) {
        Set<Long> OrderitemsIds= new HashSet<>();
        for (Orderitem orderitem: order.getOrderitems()) {
            OrderitemsIds.add(orderitem.getId());
        }
        return new OrderDto(
                order.getId(),
                OrderitemsIds,
                order.getStudent().getId(),
                order.getTotal(),
                order.getStatus()
        );
    }

    public static Order mapToOrder(OrderDto orderDto) {

        Set<Orderitem> orderitemSet= new HashSet<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        for(Long CourseId: orderDto.getCourseId()){
            Optional<Orderitem> orderitem=orderItemRepository.findById(CourseId);

            orderitem.ifPresent(orderitemSet::add);
        }
        return new Order(
                orderDto.getId(),
                orderitemSet,
                user,
                orderDto.getTotal(),
                orderDto.getStatus()
        );
    }
}
