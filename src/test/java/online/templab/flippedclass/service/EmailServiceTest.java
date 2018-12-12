package online.templab.flippedclass.service;

import online.templab.flippedclass.FlippedClassApplicationTest;
import online.templab.flippedclass.entity.Email;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wk
 */
public class EmailServiceTest extends FlippedClassApplicationTest {

    @Autowired
    private EmailService emailService;

    private Email createEmail() {
        Email email = new Email()
                .setToAddress("wkay2016@163.com")
                .setSubject("测试邮件")
                .setContent("这是一个内容");
        return email;
    }

    @Test
    public void send() throws Exception {
        Email email = createEmail();
        emailService.send(email);
    }

} 
