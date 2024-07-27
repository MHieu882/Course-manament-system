package Group1.Mock.dto;

import Group1.Mock.dto.BasicResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BasicResponseTest {

    private BasicResponse basicResponse;

    @BeforeEach
    public void setUp() {
        basicResponse = new BasicResponse();
    }

    @Test
    public void testNoArgsConstructor() {
        BasicResponse response = new BasicResponse();
        assertNotNull(response);
        assertNull(response.getMessage());
    }

    @Test
    public void testAllArgsConstructor() {
        String message = "Success";
        BasicResponse response = new BasicResponse(message);

        assertNotNull(response);
        assertEquals(message, response.getMessage());
    }

    @Test
    public void testGettersAndSetters() {
        String message = "Error";

        basicResponse.setMessage(message);

        assertEquals(message, basicResponse.getMessage());
    }
}
