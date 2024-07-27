package Group1.Mock.service;

import Group1.Mock.Mapper.PayoutMapper;
import Group1.Mock.dto.PayoutDto;
import Group1.Mock.entity.Payout;
import Group1.Mock.entity.User;
import Group1.Mock.reponsitory.PayoutRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PayoutServiceTest {

    @InjectMocks
    private PayoutService payoutService;

    @Mock
    private PayoutRepository payoutRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRequestPayout() {
        // Mock data
        User user = new User();
        Double amount = 100.0;
        Payout payout = new Payout();
        payout.setUser(user);
        payout.setAmount(amount);
        payout.setStatus(Payout.PayoutStatus.PENDING);

        PayoutDto payoutDto = new PayoutDto();
        payoutDto.setAmount(amount);
        payoutDto.setStatus(PayoutDto.PayoutStatus.PENDING);

        when(payoutRepository.save(any(Payout.class))).thenReturn(payout);

        // Call the method
        PayoutDto result = payoutService.requestPayout(user, amount);

        // Verify results
        assertNotNull(result);
        assertEquals(amount, result.getAmount());
        assertEquals(PayoutDto.PayoutStatus.PENDING, result.getStatus());
        verify(payoutRepository).save(any(Payout.class));
    }

    @Test
    public void testGetPayoutsForUser() {
        // Mock data
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        Payout payout = new Payout();
        payout.setUser(user);
        payout.setStatus(Payout.PayoutStatus.PENDING); // Ensure status is not null

        List<Payout> payouts = new ArrayList<>();
        payouts.add(payout);

        PayoutDto payoutDto = PayoutMapper.mapToPayoutDto(payout);
        when(payoutRepository.findByUserId(userId)).thenReturn(payouts);

        // Call the method
        List<PayoutDto> result = payoutService.getPayoutsForUser(userId);

        // Verify results
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(payoutDto.toString(), result.get(0).toString());
        verify(payoutRepository).findByUserId(userId);
    }
}
