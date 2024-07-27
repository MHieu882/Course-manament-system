package Group1.Mock.controller;

import Group1.Mock.dto.ApproveRequest;
import Group1.Mock.dto.BasicResponse;
import Group1.Mock.dto.BlockUserRequest;
import Group1.Mock.entity.Course;
import Group1.Mock.entity.RoleEnum;
import Group1.Mock.entity.User;
import Group1.Mock.enums.ApproveType;
import Group1.Mock.exception.SearchNotFoundException;
import Group1.Mock.service.CourseService;
import Group1.Mock.service.EmailService;
import Group1.Mock.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.Optional;

@RequestMapping("/admin")
@RestController
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private UserService service;
    private CourseService courseService;
    private EmailService emailService;

    @PostMapping("/approveInstructor")
    public ResponseEntity<BasicResponse> approveInstructor(@RequestBody ApproveRequest input) {
        Optional<User> optionalUser = service.findUserId(input.getId());
        if (optionalUser.isEmpty()) {
            throw new SearchNotFoundException("Không tìm thấy tài khoản");
        }

        User user = optionalUser.get();
        if (user.getRole().getName() != RoleEnum.INSTRUCTOR) {
            return new ResponseEntity<>(new BasicResponse("Tài khoản này không phải giảng viên"), HttpStatus.BAD_REQUEST);
        }

        if (input.getStatus() == ApproveType.APPROVED) {
            sendApproveMail(user.getEmail());
            user.setEnabled(true);
        }
        else {
            sendRejectMail(user.getEmail());
            user.setEnabled(false);
        }
        service.saveUserState(user);
        return ResponseEntity.ok(new BasicResponse("Thành công"));
    }

    @PostMapping("/approveCourse")
    public ResponseEntity<BasicResponse> approveCourse(@RequestBody ApproveRequest input) {
        Optional<Course> optionalCourse = courseService.searchById(input.getId());
        if (optionalCourse.isEmpty()) {
            throw new SearchNotFoundException("Không tìm thấy khóa học");
        }

        Course course = optionalCourse.get();

        if (input.getStatus() == ApproveType.APPROVED) course.setApproved(true);
        else course.setApproved(false);
        courseService.saveCourseState(course);
        return ResponseEntity.ok(new BasicResponse("Thành công"));
    }

    @PostMapping("/block")
    public ResponseEntity<BasicResponse> blockUser(@RequestBody BlockUserRequest req) {
        return setBlocked(req, true);
    }

    @PostMapping("/unblock")
    public ResponseEntity<BasicResponse> unblockUser(@RequestBody BlockUserRequest req) {
        return setBlocked(req, false);
    }

    private ResponseEntity<BasicResponse> setBlocked(BlockUserRequest req, boolean blocked) {
        Optional<User> optionalUser = service.findUserId(req.getId());
        if (optionalUser.isEmpty()) {
            throw new SearchNotFoundException("Không tìm thấy tài khoản này");
        }

        User user = optionalUser.get();

        if (user.getRole().getName() == RoleEnum.ADMIN) {
            return new ResponseEntity<>(new BasicResponse("Không thể thực hiện tác vụ"), HttpStatus.BAD_REQUEST);
        }

        user.setBlocked(blocked);
        service.saveUserState(user);
        return ResponseEntity.ok(new BasicResponse("Thành công"));
    }

    private void sendApproveMail(String email) {
        String msg = "<p>Chúc mừng bạn đã trở thành giáo viên tại Cursus. Hãy truy cập vào hệ thống để tiến hành cung cấp,\n" +
                "phát triển khóa học của bạn.</p>";
        String subject = "Chúc mừng bạn đã trở thành giáo viên tại Cursus";
        String mailContent = emailService.createMessageWithTemplate(msg, email);
        emailService.sendMimeEmail(email, subject, mailContent);
    }

    private void sendRejectMail(String email) {
        String msg = "<p>Cursus cảm ơn bạn đã chọn, chúng tôi đã xem xét và đánh giá bạn chưa phù hợp cho những sản phẩm\n" +
                "lần này.</p>";
        msg += "<span>Chúng tôi sẽ hy vọng sẽ làm việc với bạn trong tương lai sắp tới.</span>";
        String subject = "Cảm ơn bạn đã chọn Cursus";
        String mailContent = emailService.createMessageWithTemplate(msg, email);
        emailService.sendMimeEmail(email, subject, mailContent);
    }
}
