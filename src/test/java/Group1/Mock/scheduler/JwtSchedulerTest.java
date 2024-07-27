package Group1.Mock.scheduler;

import Group1.Mock.entity.JwtToken;
import Group1.Mock.service.JWTService;
import Group1.Mock.scheduler.JwtScheduler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class JwtSchedulerTest {

    @InjectMocks
    private JwtScheduler jwtScheduler;

    @Mock
    private JWTService jwtService;

    @Mock
    private Logger logger;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testOnScheduled() {
        // Run the scheduled method
        jwtScheduler.onScheduled();

        // Verify deleteOldToken is called
        verify(jwtService, times(1)).getAll();
        verify(jwtService, never()).deleteToken(any());
    }

    @Test
    public void testDeleteOldToken() {
        // Create a token that is expired
        JwtToken expiredToken = new JwtToken();
        expiredToken.setToken("expired-token");
        expiredToken.setExpiryDate(new Date(System.currentTimeMillis() - 10000)); // 10 seconds ago

        // Create a token that is not expired
        JwtToken validToken = new JwtToken();
        validToken.setToken("valid-token");
        validToken.setExpiryDate(new Date(System.currentTimeMillis() + 10000)); // 10 seconds from now

        // Mock JWTService methods
        when(jwtService.getAll()).thenReturn(List.of(expiredToken, validToken));

        // Call the method to test
        jwtScheduler.deleteOldToken();

        // Verify that deleteToken is called for the expired token
        verify(jwtService, times(1)).deleteToken("expired-token");

        // Verify deleteToken is not called for the valid token
        verify(jwtService, never()).deleteToken("valid-token");
    }
}
