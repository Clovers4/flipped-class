package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.FlippedClassApplicationTest;
import online.templab.flippedclass.entity.KlassSeminar;
import online.templab.flippedclass.entity.Seminar;
import online.templab.flippedclass.service.KlassSeminarService;
import online.templab.flippedclass.service.SeminarService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.Action;
import java.util.Date;

/**
 * @author fj
 */
@Transactional
public class SeminarServiceImplTest extends FlippedClassApplicationTest {

    @Autowired
    SeminarService seminarService;

    @Autowired
    KlassSeminarService klassSeminarService;

    private Seminar createSeminar(){
        return new Seminar()
                .setCourseId((long)random.nextInt(10))
                .setContent("testContent")
                .setEnrollEndDate(new Date())
                .setEnrollStartDate(new Date())
                .setMaxTeam(random.nextInt(10))
                .setRoundId((long)random.nextInt(10))
                .setSerial(random.nextInt(20))
                .setTheme("testTheme")
                .setVisible(true);
    }

    @Test
    public void testInsert()throws Exception{
        Seminar seminar = createSeminar();
        logger.info(seminar.toString());
        Boolean success = seminarService.insert(seminar);
        logger.info(seminar.toString());
        Assert.assertEquals(true,success);
    }

    @Test
    public void testUpdate()throws Exception{
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
        Assert.assertEquals(true,success);
    }

    @Test
    public void testDelete()throws Exception{
        Seminar seminar = createSeminar();
        seminarService.insert(seminar);
        logger.info(seminar.toString());
        KlassSeminar klassSeminar = new KlassSeminar()
                .setSeminarId(seminar.getId())
                .setKlassId((long)random.nextInt(3))
                .setState(1)
                .setReportDeadline(new Date());
        klassSeminarService.insert(klassSeminar);
        logger.info(klassSeminar.toString());
        Boolean success = seminarService.delete(seminar.getId());
        logger.info(success.toString());
        Assert.assertEquals(true,success);
    }

    @Test
    public void testGet()throws Exception{
        Seminar seminar = createSeminar();
        seminarService.insert(seminar);
        logger.info(seminar.toString());
        Seminar recordSeminar = seminarService.get(seminar.getId());
        logger.info(recordSeminar.toString());
        Assert.assertEquals(recordSeminar.getId(),seminar.getId());
    }

    @Test
    public void testGetMaxSeminarSerialByCourseId()throws Exception{
        Seminar seminars [] = new Seminar[5];
        for(int i=0;i<5;i++){
            seminars[i] = createSeminar();
            seminars[i].setCourseId((long)2);
            seminarService.insert(seminars[i]);
        }
        logger.info(seminars.toString());
        Integer maxSeminarSerial = seminarService.getMaxSeminarSerialByCourseId((long)2);
        logger.info(maxSeminarSerial.toString());
        Assert.assertNotNull(maxSeminarSerial);
    }
}
