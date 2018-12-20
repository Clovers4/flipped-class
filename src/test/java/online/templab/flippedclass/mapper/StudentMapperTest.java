package online.templab.flippedclass.mapper;

import online.templab.flippedclass.FlippedClassApplicationTest;
import online.templab.flippedclass.entity.Student;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author fj
 */
public class StudentMapperTest extends FlippedClassApplicationTest {

    @Autowired
    StudentMapper studentMapper;

    @Test
    @Transactional
    public void testUpdateByStudentNumSelective()throws Exception{
        int line = studentMapper.updateByStudentNumSelective(
                new Student()
                        .setStudentNum("test394")
                        .setEmail("testemail@163.com")
                        .setActivated(true)
        );
        Assert.assertEquals(1, line);
    }

    @Test
    public void testSelectUnTeamedStudentByCourseId()throws Exception{
        List<Student> studentList = studentMapper.selectUnTeamedStudentByCourseId((long)1);
        logger.info(studentList.toString());
        Assert.assertNotNull(studentList);
    }

    @Test
    public void testSelectTeamMemberByTeamId()throws Exception{
        List<Student> studentList = studentMapper.selectTeamMemberByTeamId((long)1,(long)1);
        logger.info(studentList.toString());
        Assert.assertNotNull(studentList);
    }

    @Test
    public void testSelectByStudentName()throws Exception{
        List<Student> studentList = studentMapper.selectByStudentName("hello");
        logger.info(studentList.toString());
        Assert.assertNotNull(studentList);
    }
}
