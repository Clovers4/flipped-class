package online.templab.flippedclass;

import online.templab.flippedclass.common.multipart.StorageProperties;
import online.templab.flippedclass.common.multipart.FileService;
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
@EnableConfigurationProperties(StorageProperties.class)
public class FlippedClassApplication {

    /**
     * 这里使用这个 deprecated类是因为security需要passwordEncoder,而本项目密码不需要加密。
     * 因此使用NoOpPasswordEncoder加密,在以后即使要替换成BCypt等加密器时过渡也会更加简单。
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    @Bean()
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    CommandLineRunner init(FileService fileService) {
        return (args) -> {
            //    storageService.deleteAll();
            fileService.init();
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(FlippedClassApplication.class, args);
    }
}
