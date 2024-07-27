package Group1.Mock.Mapper;

import Group1.Mock.dto.UserDto;
import Group1.Mock.dto.UserProfileDto;
import Group1.Mock.entity.User;
import Group1.Mock.entity.UserProfile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserMapperTest {

    @Test
    public void testMapToUserDto() {
        // Given
        UserProfile profile = new UserProfile();
        profile.setFirstName("John");
        profile.setLastName("Doe");
        profile.setAddress("123 Main St");
        profile.setAvatar("avatar.png");
        profile.setPhoneNumber("123-456-7890");

        User user = new User();
        user.setId(1L);
        user.setUsername("johndoe");
        user.setEmail("johndoe@example.com");
        user.setProfile(profile);

        // When
        UserDto userDto = UserMapper.mapToUserDto(user);

        // Then
        assertNotNull(userDto);
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getUsername(), userDto.getUsername());
        assertEquals(user.getEmail(), userDto.getEmail());

        UserProfileDto profileDto = userDto.getProfile();
        assertNotNull(profileDto);
        assertEquals(profile.getFirstName(), profileDto.getFirstName());
        assertEquals(profile.getLastName(), profileDto.getLastName());
        assertEquals(profile.getAddress(), profileDto.getAddress());
        assertEquals(profile.getAvatar(), profileDto.getAvatar());
        assertEquals(profile.getPhoneNumber(), profileDto.getPhone_number());
    }

    @Test
    public void testMapToUserDto_NullProfile() {
        // Given
        User user = new User();
        user.setId(1L);
        user.setUsername("johndoe");
        user.setEmail("johndoe@example.com");
        user.setProfile(null);

        // When
        UserDto userDto = UserMapper.mapToUserDto(user);

        // Then
        assertNotNull(userDto);
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getUsername(), userDto.getUsername());
        assertEquals(user.getEmail(), userDto.getEmail());

        UserProfileDto profileDto = userDto.getProfile();
//        assertNull(profileDto);
    }

//    @Test
//    public void testMapToUserDto_NullUser() {
//        User user = new User();
//
//        // When
//        UserDto userDto = UserMapper.mapToUserDto(user);
//
//        // Then
//        assertNull(userDto);
//    }
}
