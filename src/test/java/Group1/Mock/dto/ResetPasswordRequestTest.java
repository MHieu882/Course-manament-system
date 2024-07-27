package Group1.Mock.dto;//package Group1.Mock.dto;
//
//import Group1.Mock.dto.ResetPasswordRequest;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ResetPasswordRequestTest {
//
//    private ResetPasswordRequest resetPasswordRequest;
//
//
//    @BeforeEach
//    public void setUp() {
//        resetPasswordRequest = new ResetPasswordRequest();
//    }
//
//    @Test
//    public void testNoArgsConstructor() {
//        ResetPasswordRequest dto = new ResetPasswordRequest();
//        assertNotNull(dto);
//    }
//
//    @Test
//    public void testGettersAndSetters() {
//        String password = "newPassword123";
//
//        resetPasswordRequest.setPassword(password);
//
//        assertEquals(password, resetPasswordRequest.getPassword());
//    }
//
//}
import Group1.Mock.dto.ResetPasswordRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResetPasswordRequestTest {

    private ResetPasswordRequest resetPasswordRequest;

    @BeforeEach
    void setUp() {
        resetPasswordRequest = new ResetPasswordRequest("testPassword");
    }

    @Test
    void testGetPassword() {
        assertEquals("testPassword", resetPasswordRequest.getPassword());
    }

    @Test
    void testSetPassword() {
        resetPasswordRequest.setPassword("newPassword");
        assertEquals("newPassword", resetPasswordRequest.getPassword());
    }

//    @Test
//    void testToString() {
//        String expected = "ResetPasswordRequest{password='testPassword'}";
//        assertEquals(expected, resetPasswordRequest.toString());
//    }
//
//    @Test
//    void testHashCode() {
//        ResetPasswordRequest anotherRequest = new ResetPasswordRequest("testPassword");
//        assertEquals(resetPasswordRequest.hashCode(), anotherRequest.hashCode());
//    }
//
//    @Test
//    void testEquals() {
//        ResetPasswordRequest anotherRequest = new ResetPasswordRequest("testPassword");
//        assertTrue(resetPasswordRequest.equals(anotherRequest));
//        assertFalse(resetPasswordRequest.equals(null));
//        assertFalse(resetPasswordRequest.equals("Not a ResetPasswordRequest"));
//    }
}