package Group1.Mock.entity;

import Group1.Mock.entity.Order;
import Group1.Mock.entity.Orderitem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderitemTest {

    private Orderitem orderitem;

    @BeforeEach
    public void setUp() {
        orderitem = new Orderitem();
    }

    @Test
    public void testIdSetterAndGetter() {
        Long expectedId = 1L;
        orderitem.setId(expectedId);
        assertEquals(expectedId, orderitem.getId());
    }

    @Test
    public void testNameSetterAndGetter() {
        String expectedName = "Java Programming";
        orderitem.setName(expectedName);
        assertEquals(expectedName, orderitem.getName());
    }

    @Test
    public void testPriceSetterAndGetter() {
        double expectedPrice = 99.99;
        orderitem.setPrice(expectedPrice);
        assertEquals(expectedPrice, orderitem.getPrice());
    }

    @Test
    public void testOrderSetterAndGetter() {
        Order expectedOrder = new Order();
        expectedOrder.setId(2L);
        orderitem.setOrder(expectedOrder);
        assertEquals(expectedOrder, orderitem.getOrder());
    }

    @Test
    public void testToString() {
        orderitem.setId(1L);
        orderitem.setName("Python Programming");
        orderitem.setPrice(129.99);
        Order order = new Order();
        order.setId(3L);
        orderitem.setOrder(order);

        String expectedToString = "Orderitem{id=1, name='Python Programming', price=129.99, order=Order{id=3, ...}}";
        assertEquals(expectedToString, orderitem.toString());
    }
}