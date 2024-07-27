package Group1.Mock.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmailExistExceptionTest {

    @Test
    public void testEmailExistExceptionMessage() {
        // Arrange
        String expectedMessage = "Email already exists";

        // Act
        EmailExistException exception = assertThrows(EmailExistException.class, () -> {
            throw new EmailExistException(expectedMessage);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
}
