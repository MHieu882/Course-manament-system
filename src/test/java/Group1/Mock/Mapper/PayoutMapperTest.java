package Group1.Mock.Mapper;

import Group1.Mock.dto.PayoutDto;
import Group1.Mock.entity.Payout;
import Group1.Mock.entity.User;
import Group1.Mock.reponsitory.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PayoutMapperTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PayoutMapper payoutMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testMapToPayoutDto() {
        // Given
        User user = new User();
        user.setId(1L);

        Payout payout = new Payout();
        payout.setId(2L);
        payout.setUser(user);
        payout.setAmount(500.0);
        payout.setCreatedAt(new Date());
        payout.setStatus(Payout.PayoutStatus.PENDING);

        // When
        PayoutDto payoutDto = PayoutMapper.mapToPayoutDto(payout);

        // Then
        assertNotNull(payoutDto);
        assertEquals(payout.getId(), payoutDto.getId());
        assertEquals(payout.getUser().getId(), payoutDto.getUserId());
        assertEquals(payout.getAmount(), payoutDto.getAmount());
        assertEquals(payout.getCreatedAt(), payoutDto.getCreatedAt());
        assertEquals(payout.getStatus().name(), payoutDto.getStatus().name());
    }

    @Test
    public void testMapToPayout() {
        // Given
        PayoutDto payoutDto = new PayoutDto();
        payoutDto.setId(2L);
        payoutDto.setUserId(1L);
        payoutDto.setAmount(500.0);
        payoutDto.setCreatedAt(new Date());
        payoutDto.setStatus(PayoutDto.PayoutStatus.PENDING);

        // Mocking
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // When
        Payout payout = PayoutMapper.mapToPayout(payoutDto);

        // Then
        assertNotNull(payout);
        assertEquals(payoutDto.getId(), payout.getId());
        assertEquals(payoutDto.getAmount(), payout.getAmount());
        assertEquals(payoutDto.getStatus().name(), payout.getStatus().name());
        // Note: User entity should be set separately in the service layer, not in the mapper
        // so you may need to handle this depending on your actual implementation
    }

    @Test
    public void testMapToPayoutDto_NullInput() {
        // When
        PayoutDto payoutDto = PayoutMapper.mapToPayoutDto(null);

        // Then
        assertNull(payoutDto);
    }

    @Test
    public void testMapToPayout_NullInput() {
        // When
        Payout payout = PayoutMapper.mapToPayout(null);

        // Then
        assertNull(payout);
    }
}
