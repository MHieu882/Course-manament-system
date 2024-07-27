package Group1.Mock.entity;

import Group1.Mock.entity.User;
import Group1.Mock.entity.UserProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserProfileTest {

    private UserProfile userProfile;

    @BeforeEach
    public void setUp() {
        userProfile = new UserProfile();
    }

    @Test
    public void testIdGeneration() {
        assertNull(userProfile.getId());
        userProfile.setId(1L);
        assertEquals(Long.valueOf(1), userProfile.getId());
    }

    @Test
    public void testNameFields() {
        assertNull(userProfile.getFirstName());
        assertNull(userProfile.getLastName());

        userProfile.setFirstName("John");
        userProfile.setLastName("Doe");

        assertEquals("John", userProfile.getFirstName());
        assertEquals("Doe", userProfile.getLastName());
    }

    @Test
    public void testAvatarField() {
        assertNull(userProfile.getAvatar());

        userProfile.setAvatar("https://example.com/avatar.jpg");

        assertEquals("https://example.com/avatar.jpg", userProfile.getAvatar());
    }

    @Test
    public void testAddressField() {
        assertNull(userProfile.getAddress());

        userProfile.setAddress("123 Main St, City, State, Zip");

        assertEquals("123 Main St, City, State, Zip", userProfile.getAddress());
    }

    @Test
    public void testPhoneNumberField() {
        assertNull(userProfile.getPhoneNumber());

        userProfile.setPhoneNumber("+1 123-456-7890");

        assertEquals("+1 123-456-7890", userProfile.getPhoneNumber());
    }

    @Test
    public void testUserAssociation() {
        assertNull(userProfile.getUser());

        User user = new User();
        user.setId(2L);
        userProfile.setUser(user);

        assertEquals(user, userProfile.getUser());
    }
}