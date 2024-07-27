package Group1.Mock.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ResourceNotFoundExceptionTest {

    @Test
    public void testResourceNotFoundExceptionMessage() {
        // Define the expected message
        String expectedMessage = "Resource not found";

        // Instantiate the exception with the expected message
        ResourceNotFoundException exception = new ResourceNotFoundException(expectedMessage);

        // Validate that the exception message is correctly set
        assertNotNull(exception);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testResourceNotFoundExceptionNoArgs() {
        // Instantiate the exception with no arguments
        ResourceNotFoundException exception = new ResourceNotFoundException("Some message");

        // Validate that the exception can be created and has the correct message
        assertNotNull(exception);
        assertEquals("Some message", exception.getMessage());
    }
}
