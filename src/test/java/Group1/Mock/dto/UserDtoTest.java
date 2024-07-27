package Group1.Mock.dto;

import Group1.Mock.dto.UserDto;
import Group1.Mock.dto.UserProfileDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserDtoTest {

    private UserDto userDto;

    @BeforeEach
    public void setUp() {
        userDto = new UserDto();
    }

    @Test
    public void testNoArgsConstructor() {
        UserDto dto = new UserDto();
        assertNotNull(dto);
    }

    @Test
    public void testAllArgsConstructor() {
        Long id = 1L;
        String username = "user123";
        String email = "user@example.com";
        UserProfileDto profile = new UserProfileDto();
        profile.setFirstName("John");
        profile.setLastName("Doe");
        profile.setAddress("123 Main St");
        profile.setAvatar("avatar.png");
        profile.setPhone_number("555-555-5555");

        UserDto dto = new UserDto();
        dto.setId(id);
        dto.setUsername(username);
        dto.setEmail(email);
        dto.setProfile(profile);

        assertEquals(id, dto.getId());
        assertEquals(username, dto.getUsername());
        assertEquals(email, dto.getEmail());
        assertEquals(profile, dto.getProfile());
    }

    @Test
    public void testGettersAndSetters() {
        Long id = 1L;
        String username = "user123";
        String email = "user@example.com";
        UserProfileDto profile = new UserProfileDto();
        profile.setFirstName("John");
        profile.setLastName("Doe");
        profile.setAddress("123 Main St");
        profile.setAvatar("avatar.png");
        profile.setPhone_number("555-555-5555");

        userDto.setId(id);
        userDto.setUsername(username);
        userDto.setEmail(email);
        userDto.setProfile(profile);

        assertEquals(id, userDto.getId());
        assertEquals(username, userDto.getUsername());
        assertEquals(email, userDto.getEmail());
        assertEquals(profile, userDto.getProfile());
    }
}
