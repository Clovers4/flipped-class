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
    private ScoreService scoreService;

    @Autowired
    private RoundScoreMapper roundScoreMapper;

    @Autowired
    private KlassStudentMapper klassStudentMapper;

    @Autowired
    KlassSeminarMapper klassSeminarMapper;

    private SeminarScore createSeminarScore() {
        return new SeminarScore()
                .setPresentationScore(new BigDecimal(5 - random.nextDouble()))
                .setQuestionScore(new BigDecimal(5 - random.nextDouble()))
                .setReportScore(new BigDecimal(5.0 - random.nextDouble()))
                .setTeamId((long) random.nextInt(100))
                .setKlassSeminarId((long) random.nextInt(100));
    }

    private KlassSeminar createKlassSeminar() {
        return new KlassSeminar()
                .setSeminarId((long) random.nextInt(100))
                .setKlassId((long) random.nextInt(10))
                .setState(1)
                .setReportDeadline(new Date());
    }

    @Test
    public void testUpdateRoundScoure() {
        scoreService.updateRoundScore(3L, 21L);
        logger.info(roundScoreMapper.selectAll().toString());
    }

    @Test
    public void testGetByStudentIdCourseId() {
        logger.info(scoreService.getByStudentIdCourseId(212L, 16L).toString());
    }

    @Test
    public void testMarkerScore() throws Exception {
        SeminarScore seminarScore = createSeminarScore();
        logger.info(seminarScore.toString());
        Boolean success = scoreService.markerScore(seminarScore);
        logger.info(success.toString());
        Assert.assertEquals(true, success);

        seminarScore.setReportScore(new BigDecimal(4.14));
        logger.info(seminarScore.toString());
        success = scoreService.markerScore(seminarScore);
        Assert.assertEquals(true, success);
    }

    @Test
    public void testUpdate() throws Exception {
        SeminarScore seminarScore = createSeminarScore();
        logger.info(seminarScore.toString());
        KlassSeminar klassSeminar = createKlassSeminar();
        klassSeminarMapper.insert(klassSeminar);
        logger.info(klassSeminar.toString());
        scoreService.markerScore(seminarScore);

        seminarScore.setReportScore(new BigDecimal(3.14))
                .setPresentationScore(new BigDecimal(3.14));
        Boolean success = scoreService.update(seminarScore, klassSeminar.getSeminarId(), klassSeminar.getKlassId());
        logger.info(seminarScore.toString());
        Assert.assertEquals(true, success);
    }

}
