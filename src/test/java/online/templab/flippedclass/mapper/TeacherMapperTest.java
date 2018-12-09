package online.templab.flippedclass.mapper;

import online.templab.flippedclass.FlippedClassApplicationTest;
import online.templab.flippedclass.entity.Teacher;
import org.apache.ibatis.session.RowBounds;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.annotation.Repeat;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class TeacherMapperTest extends FlippedClassApplicationTest {

    @Autowired
    TeacherMapper teacherMapper;

    private Teacher createTeacher() {
        Teacher teacher = new Teacher()
                .setAccount("test")
                .setActivative(false)
                .setName("超人")
                .setPassword("sss");
        return teacher;
    }

    private Teacher createRandomTeacher() {
        Teacher teacher = new Teacher()
                .setAccount("test_" + random.nextInt(10000))
                .setActivative(false)
                .setName("随机_" + random.nextInt(100))
                .setPassword("random");
        return teacher;
    }

    @Test
    @Repeat(13)
    public void insertSelective() {
        Teacher teacher = createRandomTeacher();
        try {
            teacherMapper.insertSelective(teacher);
        } catch (DuplicateKeyException e) {
            logger.warn(e.toString());
        }
    }

    @Test
    public void getPassword() {
        String passwordDB = teacherMapper.getPassword("test_2246");
        logger.info(passwordDB);
        Assert.assertEquals("random", passwordDB);
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
