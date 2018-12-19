package online.templab.flippedclass.service.impl;

import com.github.pagehelper.Page;
import online.templab.flippedclass.FlippedClassApplicationTest;
import online.templab.flippedclass.entity.Student;
import online.templab.flippedclass.service.StudentService;
import org.apache.ibatis.session.RowBounds;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author fj
 */
public class StudentServiceImplTest extends FlippedClassApplicationTest {

    @Autowired
    StudentService studentService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Student createStudent() {
        return new Student()
                .setStudentNum("test" + random.nextInt(1000))
                .setPassword("test")
                .setActivated(false)
                .setEmail("test" + random.nextInt(10) + "@163.com")
                .setStudentName("student");
    }

    @Test
    public void testInsert() throws Exception {
        Boolean success = studentService.insert(createStudent());
        Assert.assertEquals(true, success);
    }

    @Test
    public void testDelete() throws Exception {
        Student student = createStudent();
        studentService.insert(student);

        System.out.println(student);
        Boolean success = studentService.delete(student.getStudentNum());

        Assert.assertEquals(true, success);
    }

    @Test
    public void testActive() throws Exception {
        Student student = createStudent();
        studentService.insert(student);
        Boolean success = studentService.activate(student.getId(), student.getPassword(), student.getEmail());

        Assert.assertEquals(true, success);
    }

    @Test
    public void resetPassword() throws Exception {
        Student student = createStudent();
        studentService.insert(student);
        Boolean success = studentService.resetPassword(student.getStudentNum());

        Assert.assertEquals(true, success);
    }

    @Test
    public void testModifyPassword() throws Exception {
        Student student = createStudent();
        studentService.insert(student);
        student.setPassword(passwordEncoder.encode("test" + random.nextInt(1000)));
        System.out.println(student);

        Boolean success = studentService.modifyPassword(student.getId(), student.getPassword());
        Assert.assertEquals(true, success);
    }

    @Test
    public void testGetPage() throws Exception {
        Page<Student> page = studentService.getPage(new RowBounds(1, 5));
        Assert.assertNotNull(page);
    }

}
