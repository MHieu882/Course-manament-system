package Group1.Mock.entity;

import Group1.Mock.entity.JwtToken;
import Group1.Mock.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenTest {

    private JwtToken jwtToken;

    @BeforeEach
    void setUp() {
        jwtToken = new JwtToken();
    }

    @Test
    void testId() {
        Long expectedId = 1L;
        jwtToken.setId(expectedId);
        assertEquals(expectedId, jwtToken.getId());
    }

    @Test
    void testToken() {
        String expectedToken = "test_token";
        jwtToken.setToken(expectedToken);
        assertEquals(expectedToken, jwtToken.getToken());
    }

    @Test
    void testExpiryDate() {
        Date expectedExpiryDate = new Date();
        jwtToken.setExpiryDate(expectedExpiryDate);
        assertEquals(expectedExpiryDate, jwtToken.getExpiryDate());
    }

    @Test
    void testUser() {
        User expectedUser = new User();
        expectedUser.setId(2L);
        jwtToken.setUser(expectedUser);
        assertEquals(expectedUser, jwtToken.getUser());
    }

    @Test
    void testToString() {
        JwtToken jwtToken = new JwtToken();
        jwtToken.setId(1L);
        jwtToken.setToken("test_token");
        jwtToken.setExpiryDate(new Date());

        User user = new User();
        user.setId(2L);
        jwtToken.setUser(user);

        String expectedToString = "JwtToken{" +
                "id=1, " +
                "token='test_token', " +
                "expiryDate=" + jwtToken.getExpiryDate() + ", " +
                "user=User{id=2, username='null', email='null', password='null', roles=[]}" +
                "}";

        assertEquals(expectedToString, jwtToken.toString());
    }
}