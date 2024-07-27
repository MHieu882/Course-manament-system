package Group1.Mock.controller;

import Group1.Mock.controller.PayoutController;
import Group1.Mock.dto.PayoutDto;
import Group1.Mock.entity.Payout;
import Group1.Mock.entity.User;
import Group1.Mock.service.PayoutService;
import Group1.Mock.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PayoutControllerTest {

    @Mock
    private PayoutService payoutService;

    @Mock
    private UserService userService;

    @Mock
    private Principal principal;

    @InjectMocks
    private PayoutController payoutController;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("testUser");

        when(principal.getName()).thenReturn("testUser");
        when(userService.findByUsername(anyString())).thenReturn(user);
    }

    @Test
    public void testRequestPayout() {
        when(payoutService.requestPayout(any(User.class), anyDouble())).thenReturn(new PayoutDto());

        PayoutDto payoutDto = payoutController.requestPayout(100.0, principal);

        assertNotNull(payoutDto);
    }

    @Test
    public void testGetPayouts() {
        List<PayoutDto> payoutDtoList = Collections.singletonList(new PayoutDto());
        when(payoutService.getPayoutsForUser(anyLong())).thenReturn(payoutDtoList);

        List<PayoutDto> result = payoutController.getPayouts(principal);

        assertEquals(1, result.size());
        assertEquals(payoutDtoList, result);
    }
}