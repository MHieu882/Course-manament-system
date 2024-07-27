package Group1.Mock.service;

import Group1.Mock.Mapper.OrderMapper;
import Group1.Mock.dto.OrderDto;
import Group1.Mock.dto.OrderItemDto;
import Group1.Mock.entity.*;
import Group1.Mock.exception.ResourceNotFoundException;
import Group1.Mock.reponsitory.CourseRepository;
import Group1.Mock.reponsitory.OrderItemRepository;
import Group1.Mock.reponsitory.OrderRepository;
import Group1.Mock.reponsitory.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private OrderService orderService;

    private User student;
    private Order order;
    private Course course;
    private Role role;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        role = new Role();
        role.setId(1L);
        role.setName(RoleEnum.STUDENT);

        student = new User();
        student.setId(1L);
        student.setRole(role);




        course = new Course();
        course.setCourseId(1L);
        course.setCourseName("Test Course");
        course.setPrice(100.0);

        order = new Order();
        order.setId(1L);
        order.setStudent(student);

        Set<Orderitem> orderitems = new HashSet<>();
        Orderitem orderitem = new Orderitem();
        orderitem.setId(1L);
        orderitem.setName("Test Course");
        orderitem.setPrice(100.0);
        orderitem.setOrder(order);
        orderitems.add(orderitem);
        order.setOrderitems(orderitems);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(student, null));
    }

    @Test
    void testCreateOrder() {
        OrderDto orderDto = new OrderDto();
        Set<Long> courseIds = new HashSet<>();
        courseIds.add(1L);
        orderDto.setCourseId(courseIds);

        when(courseRepository.findById(anyLong())).thenReturn(Optional.of(course));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        OrderDto createdOrder = orderService.createOrder(orderDto);

        assertThat(createdOrder).isNotNull();
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testCreateOrderWithInvalidCourse() {
        OrderDto orderDto = new OrderDto();
        Set<Long> courseIds = new HashSet<>();
        courseIds.add(1L);
        orderDto.setCourseId(courseIds);

        when(courseRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            orderService.createOrder(orderDto);
        });

        verify(courseRepository, times(1)).findById(anyLong());
        verify(orderRepository, never()).save(any(Order.class));
    }

    @Test
    void testListOrder() {
//        List<Order> orders = new ArrayList<>();
//        orders.add(order);
//
//        when(orderRepository.findByStudentId(anyLong())).thenReturn(orders);
//
//        ResponseEntity<List<OrderDto>> response = orderService.ListOrder();
//
//        assertThat(response.getBody()).isNotEmpty();
//        verify(orderRepository, times(1)).findByStudentId(anyLong());
    }

    @Test
    void testGetOrderDetail() {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));
        when(orderRepository.findByStudentId(anyLong())).thenReturn(Collections.singletonList(order));

        ResponseEntity<List<OrderItemDto>> response = orderService.getOrderdetail(1L);

        assertThat(response.getBody()).isNotEmpty();
        assertThat(response.getBody().get(0).getName()).isEqualTo("Test Course");

        verify(orderRepository, times(1)).findById(anyLong());
        verify(orderRepository, times(1)).findByStudentId(anyLong());
    }

//    @Test
//    void testGetOrderDetailNotFound() {
//        when(orderRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//        assertThrows(ResourceNotFoundException.class, () -> {
//            orderService.getOrderdetail(1L);
//        });
//
//        verify(orderRepository, times(1)).findById(anyLong());
//    }

    @Test
    void testGetOrderDetailAccessDenied() {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));
        when(orderRepository.findByStudentId(anyLong())).thenReturn(Collections.emptyList());

        assertThrows(ResourceNotFoundException.class, () -> {
            orderService.getOrderdetail(1L);
        });

        verify(orderRepository, times(1)).findById(anyLong());
        verify(orderRepository, times(1)).findByStudentId(anyLong());
    }
}
