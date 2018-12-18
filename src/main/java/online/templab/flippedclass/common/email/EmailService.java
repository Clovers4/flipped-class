package online.templab.flippedclass.common.email;

import org.springframework.mail.MailException;

public interface EmailService {

    /**
     * 发送一封 Email
     *
     * @param email
     * @throws MailException
     */
    void send(Email email) throws MailException;


    /**
     * 发送验证码
     *
     * @param toAddress
     * @param captcha
     */
    void sendCaptcha(String toAddress, String captcha);

    /**
     * 发送一个通知
     *
     * @param toAddress 目的邮箱
     * @param subject 邮件主题,主题前会自动携带系统名作为前缀,如: 翻转课堂 - 通知
     * @param content 邮件内容
     */
    void sendMessage(String toAddress,String subject,String content);
}