package Group1.Mock.service;

import Group1.Mock.dto.UserDto;
import Group1.Mock.dto.UserProfileDto;
import Group1.Mock.entity.*;
import Group1.Mock.reponsitory.UserProfileRepository;
import Group1.Mock.reponsitory.UserRepository;
import Group1.Mock.reponsitory.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private UserProfileRepository userProfileRepository;
    private PasswordEncoder passwordEncoder;

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        users.addAll(userRepository.findAll());

        return users;
    }

    public List<User> listUserByRole(RoleEnum type) {
        List<User> users = new ArrayList<>();
        List<User> allUsers = userRepository.findAll();

        for (User user: allUsers) {
            Role role = user.getRole();
            if (role.getName() == type) {
                users.add(user);
            }
        }
        return users;
    }

    public void setNewPassword(String newPassword, User user) {
        user.setPassword(passwordEncoder.encode(newPassword));
    }

    public User saveUserState(User user) {
        return userRepository.save(user);
    }

    public boolean isPasswordEqual(String password, User user) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    public void saveUserProfile(UserProfile u) {
        userProfileRepository.save(u);
    }

    public Optional<UserProfile> findProfileByUser(User user) {
        return userProfileRepository.findByUser(user);
    }

    public void addSubscribe(User user, User instructor) {
         user.addSubscription(instructor);
         saveUserState(user);
    }

    public Set<UserDto> listUserSubscription(User user) {
        Set<User> userSub = user.getSubscriptions();
        Set<UserDto> instructors = new HashSet<>();

        for (User instructor: userSub) {
            UserDto userDto = new UserDto();
            userDto.setId(instructor.getId());
            userDto.setUsername(instructor.getUsername());
            userDto.setEmail(instructor.getEmail());
            UserProfile profile = instructor.getProfile();
            UserProfileDto profileDto = new UserProfileDto();
            profileDto.setAddress(profile.getAddress());
            profileDto.setPhone_number(profile.getPhoneNumber());
            profileDto.setFirstName(profile.getFirstName());
            profileDto.setLastName(profile.getLastName());
            profileDto.setAvatar(profile.getAvatar());
            userDto.setProfile(profileDto);
            instructors.add(userDto);
        }
        return instructors;
    }

    public void unsubscribe(User user, Long id) {
        user.unsubscribe(id);
        saveUserState(user);
    }

    public Optional<User> findUserId(Long id) {
        return userRepository.findById(id);
    }

    public List<UserProfile> findByFirstName(String firstName) {
        return userProfileRepository.findAllByFirstName(firstName);
    }

    public List<UserProfile> findByLastName(String lastName) {
        return userProfileRepository.findAllByLastName(lastName);
    }

    public List<UserProfile> search(String keyword) {
        if (keyword != null) {
            return userProfileRepository.search(keyword);
        }

        return userProfileRepository.findAll();
    }

    public User findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
