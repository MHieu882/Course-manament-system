package Group1.Mock.entity;

import Group1.Mock.entity.User;
import Group1.Mock.entity.VerificationToken;
import Group1.Mock.enums.TokenType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class VerificationTokenTest {

    private VerificationToken verificationToken;
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUsername("testUser");
        user.setEmail("testEmail");
        user.setPassword("testPassword");
        verificationToken = new VerificationToken("testToken", user);
    }

    @Test
    public void testConstructor_withUserAndToken() {
//        assertNotNull(verificationToken.getId());
        assertEquals("testToken", verificationToken.getToken());
        assertEquals(user, verificationToken.getUser());
        assertNotNull(verificationToken.getExpiryDate());
        assertNull(verificationToken.getType());
    }

    @Test
    public void testConstructor_withUserTokenAndType() {
        VerificationToken tokenWithType = new VerificationToken("testTokenWithType", user, TokenType.EMAIL_VERIFICATION);
//        assertNotNull(tokenWithType.getId());
        assertEquals("testTokenWithType", tokenWithType.getToken());
        assertEquals(user, tokenWithType.getUser());
        assertNotNull(tokenWithType.getExpiryDate());
        assertEquals(TokenType.EMAIL_VERIFICATION, tokenWithType.getType());
    }

    @Test
    public void testCalculateExpiryDate() {
        Date currentDate = new Date();
        Date expiryDate = verificationToken.getExpiryDate();
        assertTrue(expiryDate.after(currentDate));
        long diff = expiryDate.getTime() - currentDate.getTime();
//        assertEquals(24 * 60 * 60 * 1000L, diff); // 24 hours in milliseconds
    }

    @Test
    public void testIsExpired_false() {
        assertFalse(verificationToken.isExpired());
    }

    @Test
    public void testIsExpired_true() {
        verificationToken.setExpiryDate(new Date(System.currentTimeMillis() - 1000));
        assertTrue(verificationToken.isExpired());
    }
}