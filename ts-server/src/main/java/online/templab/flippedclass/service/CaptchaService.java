package online.templab.flippedclass.service;


/**
 * Captcha 业务 接口类
 *
 * @author wk
 */
public interface CaptchaService {

    /**
     * 随机生成一个验证码
     *
     * @return
     */
    String generate();
}
