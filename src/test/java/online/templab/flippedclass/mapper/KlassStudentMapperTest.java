package online.templab.flippedclass.mapper;

import online.templab.flippedclass.FlippedClassApplicationTest;
import online.templab.flippedclass.entity.KlassStudent;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fj
 */
public class KlassStudentMapperTest extends FlippedClassApplicationTest {

    @Autowired
    KlassStudentMapper klassStudentMapper;

    @Test
    public void testSelectByCourseIdAndStudentId() throws Exception {
        KlassStudent klassStudent = klassStudentMapper.selectByCourseIdAndStudentId((long) 1, (long) 1);
        logger.info(klassStudent.toString());
        Assert.assertNotNull(klassStudent);
    }

    @Test
    @Transactional
    public void testDeleteByTeamId() throws Exception {
        int line = klassStudentMapper.deleteByTeamId((long) 1);
        logger.info(String.valueOf(line));
        Boolean success = line > 0 ? true : false;
        logger.info(success.toString());
        Assert.assertEquals(true, success);
    }

    @Test
    @Transactional
    public void testDeleteByTeamIdAndStudentId() throws Exception {
        int line = klassStudentMapper.deleteByTeamIdAndStudentId((long) 1, (long) 8);
        logger.info(String.valueOf(line));
        Boolean success = line > 0 ? true : false;
        logger.info(success.toString());
        Assert.assertEquals(true, success);
    }

    @Test
    public void testSelectByStudentIdAndKlassId()throws Exception{
        KlassStudent klassStudent = klassStudentMapper.selectByStudentIdAndKlassId((long)1,(long)1);
        logger.info(klassStudent.toString());
        Assert.assertNotNull(klassStudent.getCourseId());
    }

    @Test
    @Transactional
    public void testInsertList()throws Exception{
        List<Long> studentNum = new ArrayList<>();
        studentNum.add((long)9);
        studentNum.add((long)10);
        studentNum.add((long)11);
        int line = klassStudentMapper.insertList((long)2,(long)2,(long)2,studentNum);
        logger.info(studentNum.toString());
        logger.info(String.valueOf(line));
        Boolean success = line > 0 ? true:false;
        Assert.assertEquals(true,success);
    }

    @Test
    public void testSelectByTeamIdAndStudentId()throws Exception{
        KlassStudent klassStudent = klassStudentMapper.selectByTeamIdAndStudentId((long)1,(long)1);
        logger.info(klassStudent.toString());
        Assert.assertNotNull(klassStudent.getCourseId());
        Assert.assertNotNull(klassStudent.getKlassId());
    }
}
