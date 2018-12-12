package online.templab.flippedclass.mapper;


import online.templab.flippedclass.FlippedClassApplicationTest;
import online.templab.flippedclass.entity.Admin;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Repeat;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wk
 */
@Transactional
public class AdminMapperTest extends FlippedClassApplicationTest {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    private Admin createAdmin() {
        Admin admin = new Admin()
                .setAccount("admin_test")
                .setPassword(passwordEncoder.encode("sss"));
        return admin;
    }

    private Admin createRandomAdmin() {
        Admin admin = new Admin()
                .setAccount("admin" + random.nextInt(10000))
                .setPassword(passwordEncoder.encode("random"));
        return admin;
    }


    @Test
    @Repeat(4)
    public void insertSelective() {
        Admin admin = createRandomAdmin();
        try {
            adminMapper.insertSelective(admin);
        } catch (DuplicateKeyException e) {
            logger.warn(e.toString());
        }
    }
}
