package online.templab.flippedclass.mapper;

import com.github.pagehelper.Page;
import online.templab.flippedclass.FlippedClassApplicationTest;
import online.templab.flippedclass.entity.Attendance;
import org.apache.ibatis.session.RowBounds;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Repeat;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class AttendanceMapperTest extends FlippedClassApplicationTest {

    @Autowired
    AttendanceMapper attendanceMapper;

    private Attendance createAttendance() {
        Attendance attendance = new Attendance()
                .setKlassSeminarId((long) 1)
                .setTeamId((long) 1)
                .setPresent(true)
                .setPptName("pptname")
                .setPptUrl("ppturl")
                .setReportName("reportname")
                .setReportUrl("reporturl")
                .setTeamOrder(3);
        return attendance;
    }

    @Test
    @Repeat(4)
    public void testInsert() {
        int line = attendanceMapper.insert(createAttendance());
        Assert.assertEquals(1, line);
    }

    @Test
    public void testUpdate() {
        int line = attendanceMapper.updateByPrimaryKeySelective(new Attendance().setId((long) 3).setPresent(false));
        Assert.assertEquals(1, line);
    }

    @Test
    public void testGetPage() {
        Page<Attendance> records = (Page<Attendance>) attendanceMapper.selectByRowBounds(new Attendance(), new RowBounds(1, 5));
        Assert.assertNotNull(records);
        logger.info(records.toString());
        for (Attendance record : records) {
            logger.info(record.toString());
        }
    }

}
