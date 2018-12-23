package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.FlippedClassApplicationTest;
import online.templab.flippedclass.entity.*;
import online.templab.flippedclass.mapper.*;
import online.templab.flippedclass.service.*;
import online.templab.flippedclass.entity.KlassSeminar;
import online.templab.flippedclass.entity.SeminarScore;
import online.templab.flippedclass.mapper.KlassSeminarMapper;
import online.templab.flippedclass.mapper.SeminarScoreMapper;
import online.templab.flippedclass.service.ScoreService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author fj
 * @author jh
 */
@Transactional
public class ScoreServiceImplTest extends FlippedClassApplicationTest {

    @Autowired
    private CourseService courseService;

    @Autowired
    private KlassService klassService;

    @Autowired
    private SeminarService seminarService;

    @Autowired
    private RoundService roundService;

    @Autowired
    private SeminarScoreMapper seminarScoreMapper;

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private RoundScoreMapper roundScoreMapper;

    @Autowired
    private KlassStudentMapper klassStudentMapper;

    @Autowired
    KlassSeminarMapper klassSeminarMapper;

    private void creatScore(){
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
        roundService.insert(new Round()
                .setCourseId(123L)
                .setPreScoreType(0)
                .setQuesScoreType(1)
                .setReportScoreType(0)
                .setRoundNum(2)
                .setId(111L));
        seminarService.insert(new Seminar()
                .setContent("")
                .setCourseId((long) 123)
                .setEnrollEndDate(new Date())
                .setId((long) 12321)
                .setMaxTeam(6)
                .setEnrollStartDate(new Date())
                .setRoundId((long) 111)
                .setSerial(3)
                .setVisible(true)
                .setTheme("主题1"));
        seminarService.insert(new Seminar()
                .setContent("")
                .setCourseId((long) 123)
                .setEnrollEndDate(new Date())
                .setId((long) 12322)
                .setMaxTeam(6)
                .setEnrollStartDate(new Date())
                .setRoundId((long) 111)
                .setSerial(4)
                .setVisible(true)
                .setTheme("主题2"));
        seminarScoreMapper.insert(new SeminarScore()
                .setTeamId(1L)
                .setPresentationScore(new BigDecimal(3.5))
                .setQuestionScore(new BigDecimal(4.5))
                .setReportScore(new BigDecimal(5))
                .setKlassSeminarId(seminarService.getKlassSeminar(213L,12321L).getId()));
        seminarScoreMapper.insert(new SeminarScore()
                .setTeamId(1L)
                .setPresentationScore(new BigDecimal(5))
                .setQuestionScore(new BigDecimal(5))
                .setReportScore(new BigDecimal(5))
                .setKlassSeminarId(seminarService.getKlassSeminar(213L,12322L).getId()));
        seminarScoreMapper.insert(new SeminarScore()
                .setTeamId(2L)
                .setPresentationScore(new BigDecimal(5))
                .setQuestionScore(new BigDecimal(5))
                .setReportScore(new BigDecimal(5))
                .setKlassSeminarId(seminarService.getKlassSeminar(213L,12321L).getId()));
        teamMapper.insert(new Team()
                .setKlassId(213L)
                .setCourseId(123L)
                .setLeaderId(12L)
                .setStatus(1)
                .setId(1L)
                .setTeamName("1")
                .setSerial(1));
        teamMapper.insert(new Team()
                .setKlassId(213L)
                .setCourseId(123L)
                .setLeaderId(123L)
                .setStatus(1)
                .setId(2L)
                .setTeamName("1")
                .setSerial(2));
        klassStudentMapper.insert(new KlassStudent()
                .setCourseId(123L)
                .setStudentId(1L)
                .setKlassId(213L)
                .setTeamId(1L));
    }

    private SeminarScore createSeminarScore() {
        return new SeminarScore()
                .setPresentationScore(new BigDecimal(5))
                .setQuestionScore(new BigDecimal(4.5))
                .setReportScore(new BigDecimal(4.7))
                .setTeamId((long) random.nextInt(100));
    }

    private KlassSeminar createKlassSeminar() {
        return new KlassSeminar()
                .setSeminarId((long) random.nextInt(100))
                .setKlassId((long) random.nextInt(10))
                .setState(1)
                .setReportDeadline(new Date());
    }

    @Test
    public void testUpdateRoundScoure(){
        creatScore();
        scoreService.updateRoundScore(111L,213L);
        logger.info(roundScoreMapper.select(new RoundScore().setRoundId(111L).setTeamId(1L)).toString());
        logger.info(roundScoreMapper.select(new RoundScore().setRoundId(111L).setTeamId(2L)).toString());
    }

    @Test
    public void testGetByStudentIdCourseId() {
        creatScore();
        logger.info(scoreService.getByStudentIdCourseId(1L, 123L).toString());
    }

    @Test
    public void testInsert() throws Exception {
        SeminarScore seminarScore = createSeminarScore();
        logger.info(seminarScore.toString());
        KlassSeminar klassSeminar = createKlassSeminar();
        klassSeminarMapper.insert(klassSeminar);
        logger.info(klassSeminar.toString());
        Boolean success = scoreService.markerScore(seminarScore, klassSeminar.getSeminarId());
        logger.info(success.toString());
        Assert.assertEquals(true, success);
    }

    @Test
    public void testUpdate() throws Exception {
        SeminarScore seminarScore = createSeminarScore();
        logger.info(seminarScore.toString());
        KlassSeminar klassSeminar = createKlassSeminar();
        klassSeminarMapper.insert(klassSeminar);
        logger.info(klassSeminar.toString());
        scoreService.markerScore(seminarScore, klassSeminar.getSeminarId());

        seminarScore.setReportScore(new BigDecimal(3.14))
                .setPresentationScore(new BigDecimal(3.14));
        Boolean success = scoreService.update(seminarScore, klassSeminar.getSeminarId());
        logger.info(seminarScore.toString());
        Assert.assertEquals(true, success);
    }

}
