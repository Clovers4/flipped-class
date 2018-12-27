package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.FlippedClassApplicationTest;
import online.templab.flippedclass.service.CaptchaService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;

public class CaptchaServiceImplTest extends FlippedClassApplicationTest {

    @Autowired
    private CaptchaService captchaService;

    @Test
    public void testGenerate() throws Exception {
        String captcha = captchaService.generate();
        Assert.assertNotNull(captcha);
        logger.info(captcha);
    }

} 
