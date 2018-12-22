package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.FlippedClassApplicationTest;
import online.templab.flippedclass.entity.Klass;
import online.templab.flippedclass.entity.Student;
import online.templab.flippedclass.service.KlassService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Transactional
public class KlassServiceImplTest extends FlippedClassApplicationTest {
    @Autowired
    KlassService klassService;

    @Test
    public void resetStudentList() {
        List<Student> students = new LinkedList<>();
        students.add(new Student().setId(3L));
        students.add(new Student().setId(4L));
        students.add(new Student().setId(5L));
        students.add(new Student().setId(6L));
        klassService.resetStudentList(3L, students);
    }

    @Test
    public void testDeleteKlass()throws Exception{
        klassService.insert(new Klass()
                .setId((long)12321)
                .setTime("1")
                .setSerial(1)
                .setLocation("1")
                .setGrade(100)
                .setCourseId((long)1));
        Boolean isDelete=klassService.delete((long)12321);
        Assert.assertEquals(true,isDelete);
    }
}
