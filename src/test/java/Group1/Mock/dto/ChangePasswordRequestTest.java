package Group1.Mock.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import Group1.Mock.dto.ChangePasswordRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChangePasswordRequestTest {

    private ChangePasswordRequest changePasswordRequest;

    @BeforeEach
    public void setUp() {
        changePasswordRequest = new ChangePasswordRequest();
    }

    @Test
    public void testGettersAndSetters() {
        String password = "oldPassword123";
        String newPassword = "newPassword456";

        changePasswordRequest.setPassword(password);
        changePasswordRequest.setNewPassword(newPassword);

        assertEquals(password, changePasswordRequest.getPassword());
        assertEquals(newPassword, changePasswordRequest.getNewPassword());
    }

    @Test
    public void testNoArgsConstructor() {
        ChangePasswordRequest request = new ChangePasswordRequest();
        assertNotNull(request);
    }
}
