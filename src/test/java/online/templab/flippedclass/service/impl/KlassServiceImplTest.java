package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.FlippedClassApplicationTest;
import online.templab.flippedclass.entity.*;
import online.templab.flippedclass.mapper.KlassStudentMapper;
import online.templab.flippedclass.mapper.TeamMapper;
import online.templab.flippedclass.service.CourseService;
import online.templab.flippedclass.service.KlassService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Transactional
public class KlassServiceImplTest extends FlippedClassApplicationTest {
    @Autowired
    KlassService klassService;

    @Autowired
    CourseService courseService;

    @Autowired
    TeamMapper teamMapper;

    @Autowired
    KlassStudentMapper klassStudentMapper;

    private void createKlassSeminarTable() {
        courseService.insert(new Course()
                .setId((long) 123)
                .setTeamEndDate(new Date())
                .setTeamStartDate(new Date())
                .setReportPercentage(30)
                .setQuesPercentage(20)
                .setPrePercentage(50)
                .setTeacherId((long) 1)
                .setIntroduction("1")
                .setCourseName("1"));
        klassService.insert(new Klass()
                .setCourseId((long) 123)
                .setId((long) 213)
                .setGrade(3)
                .setLocation("")
                .setSerial(1)
                .setTime(""));
    }


    @Test
    public void testResetStudentList() {
        createKlassSeminarTable();
        List<Student> students = new LinkedList<>();
        students.add(new Student().setStudentNum("213").setActivated(true).setStudentName("1").setPassword("123"));
        students.add(new Student().setStudentNum("23").setActivated(true).setStudentName("1").setPassword("123"));
        students.add(new Student().setStudentNum("13").setActivated(true).setStudentName("1").setPassword("123"));
        students.add(new Student().setStudentNum("121322").setActivated(true).setStudentName("1").setPassword("123"));
        logger.info(klassStudentMapper.select(new KlassStudent().setKlassId(213L)).toString());
        Assert.assertEquals(true,klassService.resetStudentList(213L, students));
        logger.info(klassStudentMapper.select(new KlassStudent().setKlassId(213L)).toString());
        students.add(new Student().setStudentNum("2222").setActivated(true).setStudentName("1").setPassword("123"));
        Assert.assertEquals(true,klassService.resetStudentList(213L, students));
        logger.info(klassStudentMapper.select(new KlassStudent().setKlassId(213L)).toString());
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

    @Test
    public void testListByStudentId(){
        logger.info(klassService.listByStudentId(212L).toString());
    }
}
