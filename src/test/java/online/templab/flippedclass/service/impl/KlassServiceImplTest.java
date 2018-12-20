package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.FlippedClassApplicationTest;
import online.templab.flippedclass.entity.Student;
import online.templab.flippedclass.service.KlassService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;


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
        klassService.delete((long)1);
    }
}
