package Group1.Mock.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private Logger logger;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateMessageWithTemplate() {
        String message = "This is a test message";
        String email = "test@example.com";

        String result = emailService.createMessageWithTemplate(message, email);

        String expected = "<p>Dear test@example.com</p>" +
                message +
                "<p>Group 1</p>";
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testSendMimeEmail() throws MessagingException {
        String email = "test@example.com";
        String subject = "Test Subject";
        String content = "Test Content";

        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        emailService.sendMimeEmail(email, subject, content);

        verify(mimeMessage).setFrom(new InternetAddress("noreply@jimis.me"));
        verify(mimeMessage).setRecipients(MimeMessage.RecipientType.TO, email);
        verify(mimeMessage).setSubject(subject);
        verify(mimeMessage).setContent(content, "text/html; charset=utf-8");
        verify(mailSender).send(mimeMessage);
    }

//    @Test
//    void testSendMimeEmailShouldLogErrorWhenExceptionIsThrown() throws MessagingException {
//        String email = "test@example.com";
//        String subject = "Test Subject";
//        String content = "Test Content";
//
//        when(mailSender.createMimeMessage());
//
//        emailService.sendMimeEmail(email, subject, content);
//
//        // Since Logger is not a mock object here, you would need to verify the logger call differently if it were mocked.
//        // Verify logging error message
//        // Here you might need to adapt your test strategy if you want to capture and assert logs.
//    }
}
