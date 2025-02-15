package test.solution.market.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender mailSender;

    @Value("${mail.from}")
    private String from;

    @Value("${mail.admin.email}")
    private String adminEmail;

    public void sendEmailToClient(String clientEmail, String subject, String body) {
        try {
            sendEmail(clientEmail, subject, body);
        } catch (MessagingException e) {
            log.error("Ошибка отправки письма клиенту: {}", e.getMessage());
        }
    }

    public void sendEmailToAdmin(String subject, String body) {
        try {
            sendEmail(adminEmail, subject, body);
        } catch (MessagingException e) {
            log.error("Ошибка отправки письма администратору: {}", e.getMessage());
        }
    }

    @Async
    protected void sendEmail(String to, String subject, String body) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true);

        mailSender.send(message);
    }
}
