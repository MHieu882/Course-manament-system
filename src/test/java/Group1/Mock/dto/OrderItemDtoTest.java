package Group1.Mock.dto;

import Group1.Mock.dto.OrderItemDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderItemDtoTest {

    private OrderItemDto orderItemDto;

    @BeforeEach
    public void setUp() {
        orderItemDto = new OrderItemDto();
    }

    @Test
    public void testNoArgsConstructor() {
        OrderItemDto dto = new OrderItemDto();
        assertNotNull(dto);
    }

    @Test
    public void testAllArgsConstructor() {
        Long id = 1L;
        String name = "Course Item";
        double price = 99.99;

        OrderItemDto dto = new OrderItemDto(id, name, price);

        assertEquals(id, dto.getId());
        assertEquals(name, dto.getName());
        assertEquals(price, dto.getPrice());
    }

    @Test
    public void testGettersAndSetters() {
        Long id = 1L;
        String name = "Course Item";
        double price = 99.99;

        orderItemDto.setId(id);
        orderItemDto.setName(name);
        orderItemDto.setPrice(price);

        assertEquals(id, orderItemDto.getId());
        assertEquals(name, orderItemDto.getName());
        assertEquals(price, orderItemDto.getPrice());
    }
}
