package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.FlippedClassApplicationTest;
import online.templab.flippedclass.entity.*;
import online.templab.flippedclass.mapper.AttendanceMapper;
import online.templab.flippedclass.mapper.KlassSeminarMapper;
import online.templab.flippedclass.service.CourseService;
import online.templab.flippedclass.service.KlassService;
import online.templab.flippedclass.service.SeminarService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.Action;
import java.util.Date;

/**
 * @author fj
 * @author jh
 */
@Transactional
public class SeminarServiceImplTest extends FlippedClassApplicationTest {

    @Autowired
    SeminarService seminarService;

    @Autowired
    KlassService klassService;

    @Autowired
    CourseService courseService;

    @Autowired
    AttendanceMapper attendanceMapper;

    @Autowired
    KlassSeminarMapper klassSeminarMapper;

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
        seminarService.insert(new Seminar()
                .setContent("")
                .setCourseId((long) 123)
                .setEnrollEndDate(new Date())
                .setId((long) 12321)
                .setMaxTeam(6)
                .setEnrollStartDate(new Date())
                .setRoundId((long) 123)
                .setSerial(3)
                .setVisible(true)
                .setTheme("主题"));
    }

    private Seminar createSeminar() {
        return new Seminar()
                .setCourseId((long) random.nextInt(10))
                .setContent("testContent")
                .setEnrollEndDate(new Date())
                .setEnrollStartDate(new Date())
                .setMaxTeam(random.nextInt(10))
                .setRoundId((long) random.nextInt(10))
                .setSerial(random.nextInt(20))
                .setTheme("testTheme")
                .setVisible(true);
    }

    @Test
    public void testInsert() throws Exception {
        Seminar seminar = createSeminar();
        logger.info(seminar.toString());
        Boolean success = seminarService.insert(seminar);
        logger.info(seminar.toString());
        Assert.assertEquals(true, success);
    }

    @Test
    public void testUpdate() throws Exception {
        Seminar seminar = createSeminar();
        seminarService.insert(seminar);
        logger.info(seminar.toString());
        seminar.setTheme("test");
        seminar.setSerial(99);
        seminar.setMaxTeam(99);
        seminar.setEnrollStartDate(new Date());
        Boolean success = seminarService.update(seminar);
        logger.info(seminar.toString());
        logger.info(success.toString());
        Assert.assertEquals(true, success);
    }

    @Test
    public void testDelete() throws Exception {
        Seminar seminar = createSeminar();
        seminarService.insert(seminar);
        logger.info(seminar.toString());
        KlassSeminar klassSeminar = new KlassSeminar()
                .setSeminarId(seminar.getId())
                .setKlassId((long) random.nextInt(3))
                .setState(1)
                .setReportDeadline(new Date());
        seminarService.insertKlassSeminar(klassSeminar);
        logger.info(klassSeminar.toString());
        Boolean success = seminarService.delete(seminar.getId());
        logger.info(success.toString());
        Assert.assertEquals(true, success);
    }

    @Test
    public void testGet() throws Exception {
        Seminar seminar = createSeminar();
        seminarService.insert(seminar);
        logger.info(seminar.toString());
        Seminar recordSeminar = seminarService.get(seminar.getId());
        logger.info(recordSeminar.toString());
        Assert.assertEquals(recordSeminar.getId(), seminar.getId());
    }

    @Test
    public void testGetMaxSeminarSerialByCourseId() throws Exception {
        Seminar seminars[] = new Seminar[5];
        for (int i = 0; i < 5; i++) {
            seminars[i] = createSeminar();
            seminars[i].setCourseId((long) 2);
            seminarService.insert(seminars[i]);
        }
        logger.info(seminars.toString());
        Integer maxSeminarSerial = seminarService.getMaxSeminarSerialByCourseId((long) 2);
        logger.info(maxSeminarSerial.toString());
        Assert.assertNotNull(maxSeminarSerial);
    }

    @Test
    public void testGetKlassSeminar() {
        createKlassSeminarTable();
        attendanceMapper.insertSelective(new Attendance()
                .setKlassSeminarId((long) klassSeminarMapper.selectOneByKlassIdSeminarId((long) 213, (long) 12321).getId())
                .setTeamId((long) 1)
                .setPresent(true)
                .setPptName("pptname")
                .setPreFile("ppturl")
                .setReportName("reportname")
                .setReportFile("reporturl")
                .setSn(3));
        KlassSeminar klassSeminar = seminarService.getKlassSeminar((long) 213, (long) 12321);
        logger.info(klassSeminar.toString());
        Assert.assertEquals((long) klassSeminar.getSeminarId(), (long) 12321);
    }

    @Test
    public void testDeleteKlassSeminar() {
        createKlassSeminarTable();
        attendanceMapper.insertSelective(new Attendance()
                .setKlassSeminarId((long) klassSeminarMapper
                        .selectOneByKlassIdSeminarId((long) 213, (long) 12321).getId())
                .setTeamId((long) 1)
                .setPresent(true)
                .setPptName("pptname")
                .setPreFile("ppturl")
                .setReportName("reportname")
                .setReportFile("reporturl")
                .setSn(3));
        seminarService.deleteKlassSeminar((long) klassSeminarMapper
                .selectOneByKlassIdSeminarId((long) 213, (long) 12321).getId());
    }
}
