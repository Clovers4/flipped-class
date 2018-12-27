package online.templab.flippedclass.mapper;

import online.templab.flippedclass.FlippedClassApplicationTest;
import online.templab.flippedclass.entity.KlassStudent;
import online.templab.flippedclass.entity.Student;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fj
 */
@Transactional
public class StudentMapperTest extends FlippedClassApplicationTest {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    KlassStudentMapper klassStudentMapper;

    private Student createStudent() {
        return new Student()
                .setStudentNum("StudentNum" + random.nextInt(100))
                .setPassword("Password" + random.nextInt(100))
                .setEmail("email" + random.nextInt(100) + "@163.com")
                .setStudentName("Name" + random.nextInt(10))
                .setActivated(true);
    }

    @Test
    public void testUpdateByStudentNumSelective() throws Exception {
        Student student = createStudent();
        studentMapper.insert(student);
        logger.info(student.toString());
        int line = studentMapper.updateByStudentNumSelective(
                student.setPassword("updatePassword")
                        .setEmail("updateEmail@163.com")
                        .setActivated(true)
        );
        logger.info(student.toString());
        Assert.assertEquals(1, line);
    }

    @Test
    public void testSelectUnTeamedStudentByCourseId() throws Exception {
        List<Student> studentList = studentMapper.selectUnTeamedStudentByCourseId((long) 16);
        logger.info(studentList.toString());
        Assert.assertNotNull(studentList);
    }

    @Test
    public void testSelectTeamMemberByTeamId() throws Exception {
//        for (int i = 0; i < 5; i++) {
//            Student student = createStudent();
//            studentMapper.insert(student);
//            logger.info(student.toString());
//            KlassStudent klassStudent = new KlassStudent()
//                    .setStudentId(student.getId())
//                    .setKlassId((long) random.nextInt(5))
//                    .setCourseId((long)1);
//            if(i%2==0){
//                klassStudent.setTeamId((long)1);
//            }
//            logger.info(klassStudent.toString());
//            klassStudentMapper.insert(klassStudent);
//        }
//        List<Student> studentList = studentMapper.selectTeamMemberByTeamId((long) 1, (long) 1);
//        logger.info(studentList.toString());
//        Assert.assertNotNull(studentList);
    }

    @Test
    public void testSelectTeamMerberCourseIdByTeamId () throws Exception{
        List<Student> studentList= studentMapper.selectTeamMerberCourseIdByTeamId((long)25);
        logger.info(studentList.toString());
//        for(Student student:studentList){
//            logger.info(student.toString());
//            for(Long id :student.getCouseIdList()){
//                logger.info(String.valueOf(id));
//            }
//        }
    }

}
