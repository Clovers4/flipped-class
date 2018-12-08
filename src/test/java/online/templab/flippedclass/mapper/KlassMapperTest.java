package online.templab.flippedclass.mapper;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import online.templab.flippedclass.FlippedClassApplicationTest;
import online.templab.flippedclass.entity.Klass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Repeat;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class KlassMapperTest extends FlippedClassApplicationTest {

    @Autowired
    KlassMapper klassMapper;

    private Klass createCourse() {
        Klass klass = new Klass();
        klass.setCourseId(1);
        klass.setName("test_name");
        klass.setTime("test_time");
        klass.setLocation("test_location");
        return klass;
    }


    @Test
    public void insert() {
        Klass klass = createCourse();
        klassMapper.insert(klass);
    }

    @Test
    public void getPage() {
        PageHelper.startPage(100, 5);
        List<Klass> klasses = klassMapper.selectAll();
        logger.info(klasses.toString());
        for(Klass klass:klasses){
            logger.info(klass.toString());
        }
        Page<Klass> klassPage= (Page<Klass>) klasses;
        logger.info(klassPage.toString());
    }


}
