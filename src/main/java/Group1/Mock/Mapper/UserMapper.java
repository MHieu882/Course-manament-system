package Group1.Mock.Mapper;

import Group1.Mock.dto.UserDto;
import Group1.Mock.dto.UserProfileDto;
import Group1.Mock.entity.User;
import Group1.Mock.entity.UserProfile;

public class UserMapper {
    public static UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());

        UserProfileDto profileDto = new UserProfileDto();
        UserProfile profile = user.getProfile();
        if (profile != null) {
            profileDto.setFirstName(profile.getFirstName());
            profileDto.setLastName(profile.getLastName());
            profileDto.setAddress(profile.getAddress());
            profileDto.setAvatar(profile.getAvatar());
            profileDto.setPhone_number(profile.getPhoneNumber());
        }
        userDto.setProfile(profileDto);
        return userDto;
    }
}
