package Group1.Mock.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SearchNotFoundExceptionTest {

    @Test
    public void testSearchNotFoundException_Message() {
        // Define the expected message
        String expectedMessage = "Search result not found";

        // Instantiate the exception with the expected message
        SearchNotFoundException exception = new SearchNotFoundException(expectedMessage);

        // Validate that the exception is instantiated properly
        assertNotNull(exception);

        // Verify that the message is correctly passed to the superclass
        assertEquals(expectedMessage, exception.getMessage());
    }
}
