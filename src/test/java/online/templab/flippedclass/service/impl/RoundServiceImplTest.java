package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.FlippedClassApplicationTest;
import online.templab.flippedclass.entity.*;
import online.templab.flippedclass.mapper.*;
import online.templab.flippedclass.service.RoundService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author chenr
 */
@Transactional
public class RoundServiceImplTest extends FlippedClassApplicationTest {
    @Autowired
    RoundService roundService;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    KlassMapper klassMapper;

    @Autowired
    SeminarMapper seminarMapper;

    @Autowired
    RoundMapper roundMapper;

    @Autowired
    KlassRoundMapper klassRoundMapper;

    private void createDataset() {
        courseMapper.insertSelective(new Course()
                .setId((long) 100)
                .setTeamEndDate(new Date())
                .setTeamStartDate(new Date())
                .setReportPercentage(30)
                .setQuesPercentage(20)
                .setPrePercentage(50)
                .setTeacherId((long) 1)
                .setIntroduction("1")
                .setCourseName("1"));
        courseMapper.insertSelective(new Course()
                .setId((long) 101)
                .setTeamEndDate(new Date())
                .setTeamStartDate(new Date())
                .setReportPercentage(30)
                .setQuesPercentage(20)
                .setPrePercentage(50)
                .setTeacherId((long) 2)
                .setIntroduction("1")
                .setCourseName("1")
                .setSeminarMainCourseId((long) 100)
        );

        for (int i = 0; i < 3; ++i) {
            klassMapper.insertSelective(new Klass()
                    .setId((long) (100 + i))
                    .setCourseId((long) 100)
                    .setGrade(2016)
                    .setSerial(i)
                    .setTime(String.valueOf(i))
                    .setLocation(String.valueOf(i))
            );
        }

        for (int i = 0; i < 2; ++i) {
            klassMapper.insertSelective(new Klass()
                    .setId((long) (200 + i))
                    .setCourseId((long) 101)
                    .setGrade(2017)
                    .setSerial(i)
                    .setTime(String.valueOf(i))
                    .setLocation(String.valueOf(i))
            );
        }

        for(int i = 0 ; i < 2 ; ++i){
            roundMapper.insertSelective(new Round()
                                    .setId((long)100+i)
                                    .setCourseId((long)100)
                                    .setRoundNum(i)
                                    .setPreScoreType(1)
                                    .setQuesScoreType(1)
                                    .setReportScoreType(1)
            );
        }

        for (int i = 0; i < 2; ++i) {
            seminarMapper.insertSelective(new Seminar()
                    .setContent("")
                    .setCourseId((long) 100)
                    .setEnrollEndDate(new Date())
                    .setId((long) (100+i))
                    .setMaxTeam(6)
                    .setEnrollStartDate(new Date())
                    .setRoundId((long) 100)
                    .setSerial(i)
                    .setVisible(true)
                    .setTheme("主题"));
        }

        for (int i = 0; i < 2; ++i) {
            seminarMapper.insertSelective(new Seminar()
                    .setContent("")
                    .setCourseId((long) 100)
                    .setEnrollEndDate(new Date())
                    .setId((long) (200+i))
                    .setMaxTeam(6)
                    .setEnrollStartDate(new Date())
                    .setRoundId((long) 101)
                    .setSerial(i)
                    .setVisible(true)
                    .setTheme("主题"));
        }
        for(int i = 0 ; i < 3 ;++i){
            klassRoundMapper.insertSelective(new KlassRound()
                                    .setKlassId((long)100+i)
                                    .setRoundId((long)100));
        }
    }

    private void createDataset2() {
        courseMapper.insertSelective(new Course()
                .setId((long) 100)
                .setTeamEndDate(new Date())
                .setTeamStartDate(new Date())
                .setReportPercentage(30)
                .setQuesPercentage(20)
                .setPrePercentage(50)
                .setTeacherId((long) 1)
                .setIntroduction("1")
                .setCourseName("1"));
        courseMapper.insertSelective(new Course()
                .setId((long) 101)
                .setTeamEndDate(new Date())
                .setTeamStartDate(new Date())
                .setReportPercentage(30)
                .setQuesPercentage(20)
                .setPrePercentage(50)
                .setTeacherId((long) 2)
                .setIntroduction("1")
                .setCourseName("1")
                .setSeminarMainCourseId((long) 100)
        );

        for (int i = 0; i < 3; ++i) {
            klassMapper.insertSelective(new Klass()
                    .setId((long) (100 + i))
                    .setCourseId((long) 100)
                    .setGrade(2016)
                    .setSerial(i)
                    .setTime(String.valueOf(i))
                    .setLocation(String.valueOf(i))
            );
        }

        for (int i = 0; i < 2; ++i) {
            klassMapper.insertSelective(new Klass()
                    .setId((long) (200 + i))
                    .setCourseId((long) 101)
                    .setGrade(2017)
                    .setSerial(i)
                    .setTime(String.valueOf(i))
                    .setLocation(String.valueOf(i))
            );
        }
    }

    @Test
    public void testListByCourseId() throws Exception {
        createDataset();
        logger.info("主课程");
        List<Round> roundList = roundService.listByCourseId((long) 100);
        logger.info(String.valueOf(roundList.size()));
        logger.info(roundList.toString());
        for(Round round:roundList){
            logger.info(round.toString());
            for(Seminar seminar:round.getSeminars()){
                logger.info(seminar.toString());
            }
        }
        logger.info("从课程");
        List<Round> roundList2 = roundService.listByCourseId((long) 101);
        logger.info(String.valueOf(roundList2.size()));
        logger.info(roundList2.toString());
        for(Round round:roundList){
            logger.info(round.toString());
            for(Seminar seminar:round.getSeminars()){
                logger.info(seminar.toString());
            }
        }
        Assert.assertNotNull(roundList);
    }

    @Test
    public void testListByCourseIdKlassId() throws Exception {
        createDataset();
        logger.info("主课程");
        List<Round> roundList = roundService.listByCourseIdKlassId((long) 100,(long)100);
        logger.info(String.valueOf(roundList.size()));
        logger.info(roundList.toString());
        for(Round round:roundList){
            logger.info(round.toString());
            for(Seminar seminar:round.getSeminars()){
                logger.info(seminar.toString());
            }
            logger.info(round.getKlassRounds().toString());
        }
        Assert.assertNotNull(roundList);

        logger.info("从课程");
        List<Round> roundList2 = roundService.listByCourseIdKlassId((long) 101,(long)100);
        logger.info(String.valueOf(roundList2.size()));
        logger.info(roundList2.toString());
        for(Round round:roundList2){
            logger.info(round.toString());
            for(Seminar seminar:round.getSeminars()){
                logger.info(seminar.toString());
            }
            logger.info(round.getKlassRounds().toString());
        }
        Assert.assertNotNull(roundList2);
    }

    @Test
    public void testGet() throws Exception {
        createDataset();
        Round round = roundService.get((long)100);
        logger.info(round.toString());
        for(Seminar seminar:round.getSeminars()){
            logger.info(seminar.toString());
        }
        for(KlassRound klassRound:round.getKlassRounds()){
            logger.info(klassRound.toString());
            logger.info(klassRound.getKlass().toString());
        }
        Assert.assertNotNull(round);
    }

    @Test
    public void testUpdate() throws Exception {
        createDataset2();
        Round round =new Round()
                .setId((long) 100)
                .setPreScoreType(0)
                .setQuesScoreType(0)
                .setReportScoreType(0)
                .setCourseId((long)100)
                .setRoundNum(4);
        roundService.insert(round);

        Round round1 =new Round()
                .setId((long) 100)
                .setPreScoreType(20)
                .setQuesScoreType(50);
        List<KlassRound> klassRoundList = new LinkedList<>();
        klassRoundList.add(new KlassRound().setKlassId((long)100).setRoundId((long)100).setEnrollLimit(100));
        klassRoundList.add(new KlassRound().setKlassId((long)101).setRoundId((long)100).setEnrollLimit(100));
        klassRoundList.add(new KlassRound().setKlassId((long)102).setRoundId((long)100).setEnrollLimit(10));
        round1.setKlassRounds(klassRoundList);
        Boolean success = roundService.update(round1);

        Round round2 = roundMapper.selectByPrimaryKey(new Round().setId((long)100));

        logger.info(round2.toString());
        logger.info(klassRoundMapper.select(new KlassRound().setRoundId((long)100).setKlassId((long)100)).toString());

        Assert.assertEquals(true, success);
    }

    @Test
    public void testInsert()throws Exception{
        createDataset2();
        Round round =new Round()
                .setId((long) 100)
                .setPreScoreType(0)
                .setQuesScoreType(0)
                .setReportScoreType(0)
                .setCourseId((long)100)
                .setRoundNum(4);
//        List<KlassRound> klassRoundList = new LinkedList<>();
//        klassRoundList.add(new KlassRound().setKlassId((long)1).setRoundId((long)3).setEnrollLimit(1));
//        klassRoundList.add(new KlassRound().setKlassId((long)2).setRoundId((long)3).setEnrollLimit(2));
//        klassRoundList.add(new KlassRound().setKlassId((long)3).setRoundId((long)3).setEnrollLimit(3));
//        round.setKlassRounds(klassRoundList);
        boolean success = roundService.insert(round);
        Assert.assertEquals(true, success);
    }

    @Test
    public void testUpdateKlassRound()throws Exception{
        createDataset();
        logger.info("update前");
        logger.info(klassRoundMapper.selectByPrimaryKey(new KlassRound().setKlassId((long)100).setRoundId((long)100)).toString());
        roundService.updateKlassRound(new KlassRound().setKlassId((long)100).setRoundId((long)100).setEnrollLimit(3));
        logger.info("update后");
        logger.info(klassRoundMapper.selectByPrimaryKey(new KlassRound().setKlassId((long)100).setRoundId((long)100)).toString());
    }


}
