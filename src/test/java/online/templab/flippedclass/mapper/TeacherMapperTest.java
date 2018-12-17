package online.templab.flippedclass.mapper;

import online.templab.flippedclass.FlippedClassApplicationTest;
import online.templab.flippedclass.entity.Teacher;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TeacherMapperTest extends FlippedClassApplicationTest {

    @Autowired
    TeacherMapper teacherMapper;

    @Test
    public void testUpdateByAccountSelective() {
        int line = teacherMapper.updateByAccountSelective(
                new Teacher()
                        .setAccount("teacher123")
                        .setEmail("testemail@163.com")
        );
        Assert.assertEquals(1, line);
    }

}
