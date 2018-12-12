package online.templab.flippedclass.mapper;

import online.templab.flippedclass.FlippedClassApplicationTest;
import online.templab.flippedclass.entity.Teacher;
import org.apache.ibatis.session.RowBounds;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Repeat;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wk
 */
@Transactional
public class TeacherMapperTest extends FlippedClassApplicationTest {

    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    private Teacher createTeacher() {
        Teacher teacher = new Teacher()
                .setAccount("teacher_test")
                .setActivative(false)
                .setName("超人")
                .setPassword(passwordEncoder.encode("sss"));
        return teacher;
    }

    private Teacher createRandomTeacher() {
        Teacher teacher = new Teacher()
                .setAccount("teacher" + random.nextInt(10000))
                .setActivative(false)
                .setName("随机_" + random.nextInt(100))
                .setPassword(passwordEncoder.encode("123456"));
        return teacher;
    }

    @Test
    @Repeat(4)
    public void insertSelective() {
        Teacher teacher = createRandomTeacher();
        try {
            teacherMapper.insertSelective(teacher);
        } catch (DuplicateKeyException e) {
            logger.warn(e.toString());
        }
    }

    @Test
    public void getOne() {
        //  String username="" 会查出很多，要注意这个坑
        String username = "teacher1376";
        Teacher teacher = teacherMapper.selectOne(new Teacher().setAccount(username));
        Assert.assertNotNull(teacher);
        logger.info(teacher.toString());
    }


    @Test
    public void getPage() {
        List<Teacher> records = teacherMapper.selectByRowBounds(new Teacher(), new RowBounds(2, 10));
        logger.info(records.toString());
        for (Teacher record : records) {
            logger.info(record.toString());
        }
    }


}
