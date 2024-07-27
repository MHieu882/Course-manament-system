package Group1.Mock.dto;

import Group1.Mock.dto.LoginResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginResponseTest {

    private LoginResponse loginResponse;

    @BeforeEach
    public void setUp() {
        loginResponse = new LoginResponse();
    }

    @Test
    public void testNoArgsConstructor() {
        LoginResponse response = new LoginResponse();
        assertNotNull(response);
    }

    @Test
    public void testGettersAndSetters() {
        String token = "abc123token";
        Long expiresIn = 3600L;

        loginResponse.setToken(token);
        loginResponse.setExpiresIn(expiresIn);

        assertEquals(token, loginResponse.getToken());
        assertEquals(expiresIn, loginResponse.getExpiresIn());
    }
}
