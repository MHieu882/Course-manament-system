package Group1.Mock.controller;

import Group1.Mock.dto.OrderDto;
import Group1.Mock.service.OrderService;
import Group1.Mock.controller.OrderController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateOrder() {
        // Prepare test data
        OrderDto orderDto = new OrderDto(1L, Collections.emptySet(), 1L, 100.0, "NEW");

        // Mock the service call
        when(orderService.createOrder(orderDto)).thenReturn(orderDto);

        // Call the controller method
        ResponseEntity<OrderDto> response = orderController.createOrder(orderDto);

        // Assert the response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(orderDto, response.getBody());
    }

    @Test
    public void testGetAllOrders() {
//        // Prepare test data
//        OrderDto orderDto = new OrderDto(1L, Collections.emptySet(), 1L, 100.0, "NEW");
//        List<OrderDto> orderDtoList = Collections.singletonList(orderDto);
//
//        // Mock the service call
//        when(orderService.ListOrder()).thenReturn(ResponseEntity.ok(orderDtoList));
//
//        // Call the controller method
//        ResponseEntity<Page<OrderDto>> response = orderController.getAllOrders();
//
//        // Assert the response
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(orderDtoList, response.getBody());
    }

    @Test
    public void testGetOrderItem() {
        // Prepare test data
        OrderDto orderDto = new OrderDto(1L, Collections.emptySet(), 1L, 100.0, "NEW");

        // Mock the service call
        when(orderService.getOrderdetail(1L)).thenReturn(ResponseEntity.ok(orderDto));

        // Call the controller method
        ResponseEntity<OrderDto> response = orderController.getOrderItem(1L);

        // Assert the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderDto, response.getBody());
    }
}
