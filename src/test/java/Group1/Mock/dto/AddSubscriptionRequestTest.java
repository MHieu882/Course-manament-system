package Group1.Mock.dto;

import Group1.Mock.dto.AddSubscriptionRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddSubscriptionRequestTest {

    private AddSubscriptionRequest addSubscriptionRequest;

    @BeforeEach
    public void setUp() {
        addSubscriptionRequest = new AddSubscriptionRequest();
    }

    @Test
    public void testGettersAndSetters() {
        Long instructorId = 123L;

        addSubscriptionRequest.setInstructorId(instructorId);

        assertEquals(instructorId, addSubscriptionRequest.getInstructorId());
    }

    @Test
    public void testNoArgsConstructor() {
        AddSubscriptionRequest request = new AddSubscriptionRequest();
        assertNotNull(request);
    }
}
