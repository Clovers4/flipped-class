package online.templab.flippedclass;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * @author wk
 */
@tk.mybatis.spring.annotation.MapperScan(basePackages = "online.templab.flippedclass.mapper")
@SpringBootApplication
public class FlippedClassApplication {

    @SuppressWarnings("deprecation")
    @Bean()
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    public static void main(String[] args) {
        SpringApplication.run(FlippedClassApplication.class, args);
    }
}
