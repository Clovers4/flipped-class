package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.service.CaptchaService;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author wk
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {

    /**
     * 用于构成验证码的字符
     */
    private static final char[] CODE = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    /**
     * 验证码长度
     */
    private static final int CAPTCHA_LEN = 4;

    private final Random random = new Random();

    @Override
    public String generate() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < CAPTCHA_LEN; i++) {
            sb.append(CODE[random.nextInt(CODE.length)]);
        }
        return sb.toString();
    }
}
