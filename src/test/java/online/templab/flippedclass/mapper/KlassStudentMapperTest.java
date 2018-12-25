package online.templab.flippedclass.mapper;

import online.templab.flippedclass.FlippedClassApplicationTest;
import online.templab.flippedclass.entity.KlassStudent;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fj
 */
@Transactional
public class KlassStudentMapperTest extends FlippedClassApplicationTest {

    @Autowired
    KlassStudentMapper klassStudentMapper;

//    @Test
//    public void testInsertList() throws Exception {
//        List<Long> studentNums = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            studentNums.add((long) random.nextInt(100));
//        }
//        int line = klassStudentMapper.insertList((long) random.nextInt(5), (long) random.nextInt(5), (long) random.nextInt(5), studentNums);
//        logger.info(studentNums.toString());
//        logger.info(String.valueOf(line));
//        Boolean success = line == 5  ? true : false;
//        Assert.assertEquals(true, success);
//    }

}
