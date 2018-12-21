package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.FlippedClassApplicationTest;
import online.templab.flippedclass.entity.KlassRound;
import online.templab.flippedclass.entity.Round;
import online.templab.flippedclass.entity.Seminar;
import online.templab.flippedclass.service.RoundService;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

/**
 * @author chenr
 */
public class RoundServiceImplTest extends FlippedClassApplicationTest {
    @Autowired
    RoundService roundService;

    @Test
    public void testListByCourseId() throws Exception {
        List<Round> roundList = roundService.listByCourseId((long) 1);
        logger.info(String.valueOf(roundList.size()));
        logger.info(roundList.toString());
        for(Round round:roundList){
            logger.info(round.toString());
        }
        Assert.assertNotNull(roundList);
    }

    @Test
    public void testListByCourseIdKlassId() throws Exception {
        List<Round> roundList = roundService.listByCourseIdKlassId((long) 1,(long)1);
        logger.info(String.valueOf(roundList.size()));
        logger.info(roundList.toString());
        for(Round round:roundList){
            logger.info(round.toString());
            logger.info(round.getSeminars().toString());
            logger.info(round.getKlassRounds().toString());
        }
        Assert.assertNotNull(roundList);
    }

    @Test
    public void testGet() throws Exception {

        Round round = roundService.get((long)1);
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
        Round round =new Round()
                .setId((long) 1)
                .setPreScoreType(20)
                .setQuesScoreType(50);
        List<KlassRound> klassRoundList = new LinkedList<>();
        klassRoundList.add(new KlassRound().setKlassId((long)1).setRoundId((long)1).setEnrollLimit(100));
        klassRoundList.add(new KlassRound().setKlassId((long)2).setRoundId((long)1).setEnrollLimit(100));
        klassRoundList.add(new KlassRound().setKlassId((long)3).setRoundId((long)1).setEnrollLimit(10));
        round.setKlassRounds(klassRoundList);
        Boolean success = roundService.update(round);
        Assert.assertEquals(true, success);
    }

    @Test
    public void testInsert()throws Exception{
        Round round =new Round()
                .setId((long) 3)
                .setPreScoreType(0)
                .setQuesScoreType(0)
                .setReportScoreType(0)
                .setCourseId((long)1)
                .setRoundNum(3);
        List<KlassRound> klassRoundList = new LinkedList<>();
        klassRoundList.add(new KlassRound().setKlassId((long)1).setRoundId((long)3).setEnrollLimit(1));
        klassRoundList.add(new KlassRound().setKlassId((long)2).setRoundId((long)3).setEnrollLimit(2));
        klassRoundList.add(new KlassRound().setKlassId((long)3).setRoundId((long)3).setEnrollLimit(3));
        round.setKlassRounds(klassRoundList);
        int success = roundService.insert(round);
        Assert.assertEquals(1, success);
    }

}
