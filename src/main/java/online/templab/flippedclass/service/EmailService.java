package online.templab.flippedclass.service;

import online.templab.flippedclass.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author wk
 */
@Service
public class EmailService {

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private JavaMailSender mailSender;

    /**
     * 发送者
     */
    @Value("${spring.mail.username}")
    private String username;

    public void send(Email email) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(email.getToAddress());
        message.setSubject(email.getSubject());
        message.setText(email.getContent());
        mailSender.send(message);
        // TODO: 改成logger
        System.out.println("简单邮件发送成功！");
    }
}
