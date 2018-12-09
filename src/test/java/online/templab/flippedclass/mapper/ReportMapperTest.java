package online.templab.flippedclass.mapper;

import online.templab.flippedclass.FlippedClassApplicationTest;
import online.templab.flippedclass.entity.Report;
import org.apache.ibatis.session.RowBounds;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Repeat;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class ReportMapperTest extends FlippedClassApplicationTest {

    @Autowired
    ReportMapper reportMapper;

    private Report createReport() {
        Report report = new Report()
                .setPresentationId(1)
                .setFilename("test_filename_0")
                .setScore((long) 3)
                .setUrl("test_url");
        return report;
    }

    private Report createRandomReport() {
        Report report = new Report()
                .setPresentationId(random.nextInt(10))
                .setFilename("test_filename_" + random.nextInt(100))
                .setScore((long) random.nextInt(5))
                .setUrl("test_url_" + random.nextInt(100));
        return report;
    }

    @Test
    @Repeat(3)
    public void insert() {
        Report report = createRandomReport();
        reportMapper.insert(report);
    }

    @Test
    public void deleteByPrimaryKey() {
        Report report = new Report()
                .setId(113);
        reportMapper.deleteByPrimaryKey(report);
    }

    @Test
    public void delete() {
        Report report = new Report()
                .setId(128);
        reportMapper.delete(report);
    }

    @Test
    public void updateWithoutId() {
        Report report = new Report()
                .setScore((long) 1)
                .setPresentationId(1);
        int line = reportMapper.updateByPrimaryKeySelective(report);
        Assert.assertEquals(0, line);
    }

    @Test
    public void update() {
        Report report = new Report()
                .setId(130)
                .setScore((long) 0)
                .setPresentationId(0)
                .setFilename("");
        int line = reportMapper.updateByPrimaryKeySelective(report);
        Assert.assertEquals(1, line);
    }

    @Test
    public void list() {
        Report report = new Report()
                .setScore((long) 3);
        List<Report> records = reportMapper.select(report);
        logger.info(records.toString());
        for (Report record : records) {
            logger.info(record.toString());
        }
    }

    @Test
    public void getPage() {
        List<Report> records = reportMapper.selectByRowBounds(new Report(), new RowBounds(2, 10));
        logger.info(records.toString());
        for (Report record : records) {
            logger.info(record.toString());
        }
    }

    @Test
    public void get() {
        Report report = new Report()
                .setId(150);
        Report record = reportMapper.selectByPrimaryKey(report);
        logger.info(record.toString());
    }
}
