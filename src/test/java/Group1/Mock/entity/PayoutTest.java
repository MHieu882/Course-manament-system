package Group1.Mock.entity;

import Group1.Mock.entity.Payout;
import Group1.Mock.entity.User;
import Group1.Mock.reponsitory.PayoutRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


public class PayoutTest {


    private PayoutRepository payoutRepository;
    private Payout payout;

    @BeforeEach
    public void setUp() {
        payout = new Payout();
    }

//    @Test
//    public void testIdGeneration() {
//        assertNull(payout.getId());
//
//        // Mock the behavior of saving the payout to the database
//        when(payoutRepository.save(payout)).thenReturn(payout);
//
//        // Perform database operations to save the payout
//        payout = payoutRepository.save(payout);
//
//        assertNotNull(payout.getId());
//    }


    @Test
    public void testUserAssociation() {
        User user = new User();
        user.setId(1L); // Set user ID
        payout.setUser(user);
        assertEquals(user, payout.getUser());
    }

    @Test
    public void testAmountValidation() {
        payout.setAmount(100.0);
        assertEquals(100.0, payout.getAmount());

        assertThrows(IllegalArgumentException.class, () -> {
            payout.setAmount(null);
        }, "Expected an IllegalArgumentException for null amount");
    }

    @Test
    public void testCreatedAtTimestamp() {
        Date createdAt = new Date();
        payout.setCreatedAt(createdAt);
        assertEquals(createdAt, payout.getCreatedAt());
    }

    @Test
    public void testPayoutStatusValidation() {
        payout.setStatus(Payout.PayoutStatus.PENDING);
        assertEquals(Payout.PayoutStatus.PENDING, payout.getStatus());

        assertThrows(IllegalArgumentException.class, () -> {
            payout.setStatus(null);
        }, "Status must not be null");
    }
}