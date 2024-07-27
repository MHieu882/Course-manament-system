package Group1.Mock.controller;

import Group1.Mock.dto.OrderDto;
import Group1.Mock.dto.OrderItemDto;
import Group1.Mock.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor

@RestController()
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;
    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/new")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        OrderDto order = orderService.createOrder(orderDto);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/List")
    public Page<OrderDto> getAllOrders(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size) ;
        return orderService.listOrders(pageable);
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderItem(@PathVariable("id")Long id) {
        return orderService.getOrderdetail(id);
    }

}
