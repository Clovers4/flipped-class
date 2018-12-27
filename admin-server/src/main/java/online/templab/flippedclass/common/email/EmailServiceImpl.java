package online.templab.flippedclass.common.email;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private JavaMailSender mailSender;

    /**
     * 发送者
     */
    @Value("${spring.mail.username}")
    private String username;

    private static final String SYSTEM_NAME = "翻转课堂系统 - ";

    @Override
    public void send(Email email) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(email.getToAddress());
        message.setSubject(email.getSubject());
        message.setText(email.getContent());

        mailSender.send(message);
        log.info("简单邮件发送成功!  " + message);
    }

    @Override
    public void sendCaptcha(String toAddress, String captcha) {
        Email email = new Email()
                .setToAddress(toAddress)
                .setSubject(SYSTEM_NAME + "验证码")
                .setContent("验证码 : " + captcha);
        send(email);
    }

    @Override
    public void sendMessage(String toAddress, String subject, String content) {
        Email email = new Email()
                .setToAddress(toAddress)
                .setSubject(SYSTEM_NAME + subject)
                .setContent(content);
        send(email);
    }
}
