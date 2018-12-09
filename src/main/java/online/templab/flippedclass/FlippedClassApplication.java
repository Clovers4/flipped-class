package online.templab.flippedclass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author wk
 */
@tk.mybatis.spring.annotation.MapperScan(basePackages = "online.templab.flippedclass.mapper")
@SpringBootApplication
public class FlippedClassApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlippedClassApplication.class, args);
    }
}
