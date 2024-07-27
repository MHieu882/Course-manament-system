package Group1.Mock.service;

import Group1.Mock.Mapper.OrderMapper;
import Group1.Mock.dto.OrderItemDto;
import Group1.Mock.dto.OrderDto;
import Group1.Mock.entity.*;
import Group1.Mock.exception.ResourceNotFoundException;
import Group1.Mock.reponsitory.CourseRepository;
import Group1.Mock.reponsitory.OrderItemRepository;
import Group1.Mock.reponsitory.OrderRepository;
import Group1.Mock.reponsitory.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {
    private final UserRepository userRepository;
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    private CourseRepository courseRepository;

    public OrderDto createOrder(OrderDto orderDto) {
        Set<Orderitem> orderitems=new HashSet<>();
        Set<Long> courseIds = orderDto.getCourseId();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        //check role
        Order order = new Order();

        if(user != null){
            if(user.getRole().getName() == RoleEnum.STUDENT){
                order.setStudent(user);
            }
            else {
                throw new AccessDeniedException("User is not a STUDENT: " + user.getId());
            }
        }
        if(courseIds!= null){
            for(Long courseId: courseIds){
                Optional<Course> course= courseRepository.findById(courseId);
                course.orElseThrow(()-> new ResourceNotFoundException("Course not found"));
                course.ifPresent(c->{
                    Orderitem orderitem=new Orderitem();
                    orderitem.setName(c.getCourseName());
                    orderitem.setPrice(c.getPrice());
                    orderitem.setOrder(order);
                    orderitems.add(orderitem);
                });
            }
            order.setOrderitems(orderitems);
        }
        orderRepository.save(order);
        orderDto.setId(order.getId()); // set id cho orderDto sau khi lưu vào db
        return orderDto;
    }
    public Page<OrderDto> listOrders(Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        // Tìm các đơn hàng của sinh viên với phân trang
        Page<Order> orderPage = orderRepository.findByStudentId(user.getId(), pageable);

        // Chuyển đổi Page<Order> thành Page<OrderDto>
        return orderPage.map(OrderMapper::mapToOrderDto);
    }
    //detail order

    public ResponseEntity getOrderdetail(Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        List<Order> orderList = orderRepository.findByStudentId(user.getId());
        List<OrderItemDto> orderItemDtos = new ArrayList<>();

        Optional<Order> order = orderRepository.findById(id);

        if(orderList.contains(order.get())){
            Set<Orderitem> orderitemList= order.get().getOrderitems();
            for (Orderitem orderitem : orderitemList) {
                OrderItemDto orderItemDto = new OrderItemDto();
                orderItemDto.setId(orderitem.getId());
                orderItemDto.setName(orderitem.getName());
                orderItemDto.setPrice(orderitem.getPrice());
                orderItemDtos.add(orderItemDto);
            }
            return ResponseEntity.ok(orderItemDtos);
        }
        else{
            throw new ResourceNotFoundException("Order not found for this id: " + id);

        }

    }
}

