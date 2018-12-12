package online.templab.flippedclass.mapper;

import online.templab.flippedclass.FlippedClassApplicationTest;
import online.templab.flippedclass.entity.Student;
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
public class StudentMapperTest extends FlippedClassApplicationTest {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    private Student createStudent() {
        Student student = new Student()
                .setAccount("student_test")
                .setActivative(false)
                .setName("恶人")
                .setPassword(passwordEncoder.encode("sss"));
        return student;
    }

    private Student createRandomStudent() {
        Student student = new Student()
                .setAccount("student" + random.nextInt(10000))
                .setActivative(false)
                .setName("随机_" + random.nextInt(100))
                .setPassword(passwordEncoder.encode("123456"));
        return student;
    }


    @Test
    @Repeat(4)
    public void insertSelective() {
        Student student = createRandomStudent();
        try {
            studentMapper.insertSelective(student);
        } catch (DuplicateKeyException e) {
            logger.warn(e.toString());
        }
    }

}
