package online.templab.flippedclass.common.email;

import online.templab.flippedclass.FlippedClassApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wk
 */
public class EmailServiceImplTest extends FlippedClassApplicationTest {

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
    public void testSend() throws Exception {
        Email email = createEmail();
        emailService.send(email);
    }


    @Test
    public void testSendCaptcha() throws Exception {
        emailService.sendCaptcha("wkay2016@163.com", "AS12");
    }

    @Test
    public void testSendMessage() {
        emailService.sendMessage("wkay2016@163.com", "测试", "这是一个测试邮件");
    }
} 
