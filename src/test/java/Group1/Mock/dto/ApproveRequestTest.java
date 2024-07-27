package Group1.Mock.dto;

import Group1.Mock.dto.ApproveRequest;
import Group1.Mock.enums.ApproveType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ApproveRequestTest {

    private ApproveRequest approveRequest;

    @BeforeEach
    public void setUp() {
        approveRequest = new ApproveRequest();
    }

    @Test
    public void testGettersAndSetters() {
        Long id = 123L;
        ApproveType status = ApproveType.APPROVED;

        approveRequest.setId(id);
        approveRequest.setStatus(status);

        assertEquals(id, approveRequest.getId());
        assertEquals(status, approveRequest.getStatus());
    }

    @Test
    public void testNoArgsConstructor() {
        ApproveRequest request = new ApproveRequest();
        assertNotNull(request);
    }
}
