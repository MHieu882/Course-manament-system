package Group1.Mock.controller;

import Group1.Mock.Mapper.UserMapper;
import Group1.Mock.dto.*;
import Group1.Mock.entity.JwtToken;
import Group1.Mock.entity.RoleEnum;
import Group1.Mock.entity.User;
import Group1.Mock.entity.UserProfile;
import Group1.Mock.exception.SearchNotFoundException;
import Group1.Mock.reponsitory.UserProfileRepository;
import Group1.Mock.service.JWTService;
import Group1.Mock.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.InternetHeaders;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.*;


@RequestMapping("/users")
@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private JWTService jwtService;

    @GetMapping("all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDto>> allUsers() {
        List<User> allUser = userService.allUsers();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user: allUser) {
            userDtos.add(UserMapper.mapToUserDto(user));
        }
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("instructor")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDto>> listInstructor() {
        List<User> allUser = userService.listUserByRole(RoleEnum.INSTRUCTOR);
        List<UserDto> userDtos = new ArrayList<>();
        for (User user: allUser) {
            userDtos.add(UserMapper.mapToUserDto(user));
        }
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("student")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDto>> listStudent() {
        List<User> allUser = userService.listUserByRole(RoleEnum.STUDENT);
        List<UserDto> userDtos = new ArrayList<>();
        for (User user: allUser) {
            userDtos.add(UserMapper.mapToUserDto(user));
        }
        return ResponseEntity.ok(userDtos);
    }

    @PostMapping("me/change-password")
    public ResponseEntity<BasicResponse> changePassword(@RequestBody ChangePasswordRequest req) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        if (!userService.isPasswordEqual(req.getPassword(), currentUser)) {
            return new ResponseEntity<>(new BasicResponse("Sai mật khẩu"), HttpStatus.BAD_REQUEST);
        }

        userService.setNewPassword(req.getNewPassword(), currentUser);
        userService.saveUserState(currentUser);
        return ResponseEntity.ok(new BasicResponse("Thành công"));
    }

    @PostMapping("me/profile")
    public ResponseEntity<UserProfileDto> modifyUserProfile(@RequestBody UserProfileDto profile) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        UserProfile userProfile;
        Optional<UserProfile> userProfileOptional = userService.findProfileByUser(currentUser);
        if (userProfileOptional.isPresent()) userProfile = userProfileOptional.get();
        else userProfile = new UserProfile();

        userProfile.setAddress(profile.getAddress());
        userProfile.setAvatar(profile.getAvatar());
        userProfile.setFirstName(profile.getFirstName());
        userProfile.setLastName(profile.getLastName());
        userProfile.setPhoneNumber(profile.getPhone_number());
        userProfile.setUser(currentUser);
        userService.saveUserProfile(userProfile);
        return new ResponseEntity<>(profile, HttpStatus.ACCEPTED);
    }

    @GetMapping("me/profile")
    public ResponseEntity<UserProfileDto> getUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        Optional<UserProfile> optionalUserProfile = userService.findProfileByUser(currentUser);
        String phoneNumber, address, avatar, firstName, lastName;
        if (optionalUserProfile.isPresent()) {
            UserProfile userProfile = optionalUserProfile.get();
            phoneNumber = userProfile.getPhoneNumber();
            address = userProfile.getAddress();
            avatar = userProfile.getAvatar();
            firstName = userProfile.getFirstName();
            lastName = userProfile.getLastName();
        } else {
            phoneNumber = "";
            address = "";
            avatar = "";
            firstName = "";
            lastName = "";
        }

        UserProfileDto dto = new UserProfileDto();
        dto.setPhone_number(phoneNumber);
        dto.setAddress(address);
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setAvatar(avatar);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("me/subscribe")
    public ResponseEntity<BasicResponse> addSubscription(@RequestBody AddSubscriptionRequest req) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        Optional<User> optionalInstructor = userService.findUserId(req.getInstructorId());
        if (optionalInstructor.isEmpty()) {
            return new ResponseEntity<>(new BasicResponse("Không tìm thấy giảng viên này"), HttpStatus.NOT_FOUND);
        }

        User instructor = optionalInstructor.get();
        if (instructor.getRole().getName() != RoleEnum.INSTRUCTOR) {
            return new ResponseEntity<>(new BasicResponse("Tài khoản này không phải giảng viên"), HttpStatus.BAD_REQUEST);
        }

        userService.addSubscribe(currentUser, instructor);
        return ResponseEntity.ok(new BasicResponse("Thành công"));
    }

    @GetMapping("me/subscribe")
    public ResponseEntity<Set<UserDto>> getSubscription() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        Set<UserDto> subscription = userService.listUserSubscription(currentUser);
        return ResponseEntity.ok(subscription);
    }

    @DeleteMapping("me/subscribe/{id}")
    public ResponseEntity.BodyBuilder unsubscribe(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        userService.unsubscribe(currentUser, id);
        return ResponseEntity.status(200);
    }

    @PostMapping("findByName")
    public ResponseEntity<List<UserProfileDto>> findUserByName(@RequestBody SearchUserByNameRequest req) {
        List<UserProfile> profiles = userService.search(req.getName());

        List<UserProfileDto> userProfileDtos = new ArrayList<>();

        for (UserProfile profile: profiles) {
            UserProfileDto profileDto = new UserProfileDto();
            profileDto.setFirstName(profile.getFirstName());
            profileDto.setLastName(profile.getLastName());
            profileDto.setAvatar(profile.getAvatar());
            profileDto.setPhone_number(profile.getPhoneNumber());
            profileDto.setAddress(profile.getAddress());
            userProfileDtos.add(profileDto);
        }

        return ResponseEntity.ok(userProfileDtos);
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<UserProfileDto> findUserById(@PathVariable Long id) {
        Optional<User> optionalUser = userService.findUserId(id);

        if (optionalUser.isEmpty()) {
            throw new SearchNotFoundException("User is not found");
        }

        User user = optionalUser.get();
        UserProfile profile = user.getProfile();
        UserProfileDto profileDto = new UserProfileDto();
        profileDto.setFirstName(profile.getFirstName());
        profileDto.setLastName(profile.getLastName());
        profileDto.setAvatar(profile.getAvatar());
        profileDto.setPhone_number(profile.getPhoneNumber());
        profileDto.setAddress(profile.getAddress());
        return ResponseEntity.ok(profileDto);
    }

    @GetMapping("me/logout")
    public ResponseEntity<BasicResponse> logout(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        String jwtToken = bearerToken.substring(7);
        jwtService.deleteToken(jwtToken);
        return ResponseEntity.ok(new BasicResponse("Đăng xuất thành công"));
    }
}