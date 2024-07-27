package Group1.Mock.dto;//package Group1.Mock.dto;
//
//import Group1.Mock.dto.ForgotPasswordRequest;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ForgotPasswordRequestTest {
//
//    private ForgotPasswordRequest forgotPasswordRequest;
//
//    @BeforeEach
//    public void setUp() {
//        forgotPasswordRequest = new ForgotPasswordRequest();
//    }
//
//    @Test
//    public void testGettersAndSetters() {
//        String email = "user@example.com";
//
//        forgotPasswordRequest.setEmail(email);
//
//        assertEquals(email, forgotPasswordRequest.getEmail());
//    }
//
//    @Test
//    public void testNoArgsConstructor() {
//        ForgotPasswordRequest request = new ForgotPasswordRequest();
//        assertNotNull(request);
//    }
//}
import Group1.Mock.dto.ForgotPasswordRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ForgotPasswordRequestTest {

    private ForgotPasswordRequest forgotPasswordRequest;

    @BeforeEach
    public void setUp() {
        forgotPasswordRequest = new ForgotPasswordRequest("test@example.com");
    }

    @Test
    public void testGetEmail() {
        assertEquals("test@example.com", forgotPasswordRequest.getEmail());
    }

    @Test
    public void testSetEmail() {
        forgotPasswordRequest.setEmail("new@example.com");
        assertEquals("new@example.com", forgotPasswordRequest.getEmail());
    }

    @Test
    public void testConstructor() {
        ForgotPasswordRequest newRequest = new ForgotPasswordRequest("another@example.com");
        assertEquals("another@example.com", newRequest.getEmail());
    }

//    @Test
//    public void testToString() {
//        String expected = "ForgotPasswordRequest(email=test@example.com)";
//        assertEquals(expected, forgotPasswordRequest.toString());
//    }
//
//    @Test
//    public void testHashCodeAndEquals() {
//        ForgotPasswordRequest anotherRequest = new ForgotPasswordRequest("test@example.com");
//        ForgotPasswordRequest differentRequest = new ForgotPasswordRequest("different@example.com");
//
//        assertTrue(forgotPasswordRequest.equals(anotherRequest));
//        assertTrue(anotherRequest.equals(forgotPasswordRequest));
//        assertFalse(forgotPasswordRequest.equals(differentRequest));
//        assertFalse(differentRequest.equals(forgotPasswordRequest));
//
//        assertEquals(forgotPasswordRequest.hashCode(), anotherRequest.hashCode());
//        assertNotEquals(forgotPasswordRequest.hashCode(), differentRequest.hashCode());
//    }
       @Test
    public void testGettersAndSetters() {
        String email = "user@example.com";

        forgotPasswordRequest.setEmail(email);

        assertEquals(email, forgotPasswordRequest.getEmail());
    }

    @Test
    public void testNoArgsConstructor() {
        String email = "user@example.com";
        ForgotPasswordRequest request = new ForgotPasswordRequest(email);
        assertNotNull(request);
    }
}