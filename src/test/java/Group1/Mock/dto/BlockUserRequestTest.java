package Group1.Mock.dto;

import Group1.Mock.dto.BlockUserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BlockUserRequestTest {

    private BlockUserRequest blockUserRequest;

    @BeforeEach
    public void setUp() {
        blockUserRequest = new BlockUserRequest();
    }

    @Test
    public void testGettersAndSetters() {
        Long id = 123L;

        blockUserRequest.setId(id);

        assertEquals(id, blockUserRequest.getId());
    }

    @Test
    public void testNoArgsConstructor() {
        BlockUserRequest request = new BlockUserRequest();
        assertNotNull(request);
    }
}
