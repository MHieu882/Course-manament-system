package Group1.Mock.dto;
import Group1.Mock.dto.PayoutDto;
import Group1.Mock.dto.PayoutDto.PayoutStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class PayoutDtoTest {

    private PayoutDto payoutDto;

    @BeforeEach
    public void setUp() {
        payoutDto = new PayoutDto();
    }

    @Test
    public void testNoArgsConstructor() {
        PayoutDto dto = new PayoutDto();
        assertNotNull(dto);
    }

    @Test
    public void testAllArgsConstructor() {
        Long id = 1L;
        Long userId = 2L;
        Double amount = 1500.00;
        Date createdAt = new Date();
        PayoutStatus status = PayoutStatus.COMPLETED;

        PayoutDto dto = new PayoutDto(id, userId, amount, createdAt, status);

        assertEquals(id, dto.getId());
        assertEquals(userId, dto.getUserId());
        assertEquals(amount, dto.getAmount());
        assertEquals(createdAt, dto.getCreatedAt());
        assertEquals(status, dto.getStatus());
    }

    @Test
    public void testGettersAndSetters() {
        Long id = 1L;
        Long userId = 2L;
        Double amount = 1500.00;
        Date createdAt = new Date();
        PayoutStatus status = PayoutStatus.PENDING;

        payoutDto.setId(id);
        payoutDto.setUserId(userId);
        payoutDto.setAmount(amount);
        payoutDto.setCreatedAt(createdAt);
        payoutDto.setStatus(status);

        assertEquals(id, payoutDto.getId());
        assertEquals(userId, payoutDto.getUserId());
        assertEquals(amount, payoutDto.getAmount());
        assertEquals(createdAt, payoutDto.getCreatedAt());
        assertEquals(status, payoutDto.getStatus());
    }

    @Test
    public void testPayoutStatusEnum() {
        assertEquals(PayoutStatus.PENDING, PayoutStatus.valueOf("PENDING"));
        assertEquals(PayoutStatus.COMPLETED, PayoutStatus.valueOf("COMPLETED"));
        assertEquals(PayoutStatus.FAILED, PayoutStatus.valueOf("FAILED"));
    }

    @Test
    public void testToString() {
        Long id = 1L;
        Long userId = 2L;
        Double amount = 1500.00;
        Date createdAt = new Date();
        PayoutStatus status = PayoutStatus.COMPLETED;

        PayoutDto dto = new PayoutDto(id, userId, amount, createdAt, status);
        String expectedString = "PayoutDto(id=" + id + ", userId=" + userId + ", amount=" + amount + ", createdAt=" + createdAt + ", status=" + status + ")";
        assertEquals(expectedString, dto.toString());
    }
}
