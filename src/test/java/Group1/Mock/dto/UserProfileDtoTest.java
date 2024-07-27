package Group1.Mock.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import Group1.Mock.dto.UserProfileDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserProfileDtoTest {

    private UserProfileDto userProfileDto;

    @BeforeEach
    public void setUp() {
        userProfileDto = new UserProfileDto();
    }

    @Test
    public void testGettersAndSetters() {
        String firstName = "John";
        String lastName = "Doe";
        String address = "123 Main St";
        String avatar = "avatar.png";
        String phoneNumber = "1234567890";

        userProfileDto.setFirstName(firstName);
        userProfileDto.setLastName(lastName);
        userProfileDto.setAddress(address);
        userProfileDto.setAvatar(avatar);
        userProfileDto.setPhone_number(phoneNumber);

        assertEquals(firstName, userProfileDto.getFirstName());
        assertEquals(lastName, userProfileDto.getLastName());
        assertEquals(address, userProfileDto.getAddress());
        assertEquals(avatar, userProfileDto.getAvatar());
        assertEquals(phoneNumber, userProfileDto.getPhone_number());
    }

    @Test
    public void testNoArgsConstructor() {
        UserProfileDto dto = new UserProfileDto();
        assertNotNull(dto);
    }

    @Test
    public void testAllArgsConstructor() {
        UserProfileDto dto = new UserProfileDto();

        dto.setFirstName("Jane");
        dto.setLastName("Doe");
        dto.setAddress("456 Elm St");
        dto.setAvatar("avatar2.png");
        dto.setPhone_number("0987654321");

        assertEquals("Jane", dto.getFirstName());
        assertEquals("Doe", dto.getLastName());
        assertEquals("456 Elm St", dto.getAddress());
        assertEquals("avatar2.png", dto.getAvatar());
        assertEquals("0987654321", dto.getPhone_number());
    }
}
