package Group1.Mock.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    private static Logger logger = LoggerFactory.getLogger(EmailService.class);

    public String createMessageWithTemplate(String message, String email) {
        String html = "<p>Dear " + email + "</p>" +
                message +
                "<p>Group 1</p>";
        return html;
    }

    public void sendMimeEmail(String email, String subject, String content) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            message.setFrom(new InternetAddress("noreply@jimis.me"));
            message.setRecipients(MimeMessage.RecipientType.TO, email);
            message.setSubject(subject);
            message.setContent(content, "text/html; charset=utf-8");
            mailSender.send(message);
        } catch (MessagingException ex) {
            logger.error(ex.getMessage());
        }
    }
}
