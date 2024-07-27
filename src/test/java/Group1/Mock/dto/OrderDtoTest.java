package Group1.Mock.dto;

import Group1.Mock.dto.OrderDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class OrderDtoTest {

    private OrderDto orderDto;

    @BeforeEach
    public void setUp() {
        orderDto = new OrderDto();
    }

    @Test
    public void testNoArgsConstructor() {
        OrderDto dto = new OrderDto();
        assertNotNull(dto);
    }

    @Test
    public void testAllArgsConstructor() {
        Long id = 1L;
        Set<Long> courseIds = new HashSet<>();
        courseIds.add(101L);
        courseIds.add(102L);
        Long userId = 2L;
        Double total = 250.75;
        String status = "Completed";

        OrderDto dto = new OrderDto(id, courseIds, userId, total, status);

        assertEquals(id, dto.getId());
        assertEquals(courseIds, dto.getCourseId());
        assertEquals(userId, dto.getUserId());
        assertEquals(total, dto.getTotal());
        assertEquals(status, dto.getStatus());
    }

    @Test
    public void testGettersAndSetters() {
        Long id = 1L;
        Set<Long> courseIds = new HashSet<>();
        courseIds.add(101L);
        courseIds.add(102L);
        Long userId = 2L;
        Double total = 250.75;
        String status = "Completed";

        orderDto.setId(id);
        orderDto.setCourseId(courseIds);
        orderDto.setUserId(userId);
        orderDto.setTotal(total);
        orderDto.setStatus(status);

        assertEquals(id, orderDto.getId());
        assertEquals(courseIds, orderDto.getCourseId());
        assertEquals(userId, orderDto.getUserId());
        assertEquals(total, orderDto.getTotal());
        assertEquals(status, orderDto.getStatus());
    }
}
