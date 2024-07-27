package Group1.Mock.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TransactionStatusDtoTest {

    @Test
    public void testGettersAndSetters() {
        TransactionStatusDto dto = new TransactionStatusDto();

        // Set values
        dto.setStatus("Success");
        dto.setMessage("Transaction completed");
        dto.setData("Transaction details");

        // Assert values
        assertEquals("Success", dto.getStatus(), "Status should be 'Success'");
        assertEquals("Transaction completed", dto.getMessage(), "Message should be 'Transaction completed'");
        assertEquals("Transaction details", dto.getData(), "Data should be 'Transaction details'");
    }

    @Test
    public void testDefaultConstructor() {
        TransactionStatusDto dto = new TransactionStatusDto();

        // Assert default values
        assertNull(dto.getStatus(), "Default status should be null");
        assertNull(dto.getMessage(), "Default message should be null");
        assertNull(dto.getData(), "Default data should be null");
    }

    @Test
    public void testParameterizedConstructor() {
        TransactionStatusDto dto = new TransactionStatusDto();
        dto.setStatus("Success");
        dto.setMessage("Transaction completed");
        dto.setData("Transaction details");

        // Assert values using constructor
        assertEquals("Success", dto.getStatus(), "Status should be 'Success'");
        assertEquals("Transaction completed", dto.getMessage(), "Message should be 'Transaction completed'");
        assertEquals("Transaction details", dto.getData(), "Data should be 'Transaction details'");
    }
}
