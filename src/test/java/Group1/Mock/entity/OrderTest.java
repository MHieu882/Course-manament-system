package Group1.Mock.entity;

import Group1.Mock.entity.Order;
import Group1.Mock.entity.Orderitem;
import Group1.Mock.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    private Order order;
    private User user;
    private Orderitem orderitem;

    @BeforeEach
    public void setUp() {
        order = new Order();
        user = new User();
        orderitem = new Orderitem();
    }

    @Test
    public void testOrderId() {
        Long orderId = 1L;
        order.setId(orderId);
        assertEquals(orderId, order.getId());
    }

    @Test
    public void testOrderItems() {
        Set<Orderitem> orderitems = new HashSet<>();
        orderitems.add(orderitem);
        order.setOrderitems(orderitems);
        assertEquals(orderitems, order.getOrderitems());
    }

    @Test
    public void testStudent() {
        order.setStudent(user);
        assertEquals(user, order.getStudent());
    }

    @Test
    public void testTotal() {
        Double total = 100.0;
        order.setTotal(total);
        assertEquals(total, order.getTotal());
    }

    @Test
    public void testStatus() {
        String status = "Pending";
        order.setStatus(status);
        assertEquals(status, order.getStatus());
    }
}