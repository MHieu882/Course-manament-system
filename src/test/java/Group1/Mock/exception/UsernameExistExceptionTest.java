package Group1.Mock.exception;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsernameExistExceptionTest {

    @Test
    public void testUsernameExistException_Message() {
        // Given
        String message = "Username already exists";

        // When
        UsernameExistException exception = new UsernameExistException(message);

        // Then
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
    }
}
