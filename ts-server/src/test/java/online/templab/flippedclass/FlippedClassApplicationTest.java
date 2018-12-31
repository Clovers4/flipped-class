package online.templab.flippedclass;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@tk.mybatis.spring.annotation.MapperScan(basePackages = "online.templab.flippedclass.mapper")
@RunWith(SpringRunner.class)
@SpringBootTest
public class FlippedClassApplicationTest {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected final Random random = new Random();

    @Test
    public void contextLoads() {

    }

}
