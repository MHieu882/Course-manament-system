package Group1.Mock.dto;
import Group1.Mock.dto.SearchUserByNameRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SearchUserByNameRequestTest {

    private SearchUserByNameRequest searchUserByNameRequest;

    @BeforeEach
    public void setUp() {
        searchUserByNameRequest = new SearchUserByNameRequest();
    }

    @Test
    public void testNoArgsConstructor() {
        SearchUserByNameRequest dto = new SearchUserByNameRequest();
        assertNotNull(dto);
    }

    @Test
    public void testGettersAndSetters() {
        String name = "John Doe";

        searchUserByNameRequest.setName(name);

        assertEquals(name, searchUserByNameRequest.getName());
    }
}
