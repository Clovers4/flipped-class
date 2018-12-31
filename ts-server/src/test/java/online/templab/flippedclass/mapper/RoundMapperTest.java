package online.templab.flippedclass.mapper;

import online.templab.flippedclass.FlippedClassApplicationTest;
import online.templab.flippedclass.entity.Course;
import online.templab.flippedclass.entity.Klass;
import online.templab.flippedclass.entity.KlassRound;
import online.templab.flippedclass.entity.Round;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
public class RoundMapperTest extends FlippedClassApplicationTest {
    @Autowired
    CourseMapper courseMapper;

    @Autowired
    KlassMapper klassMapper;

    private void createCourseKlass() {
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
                .setSeminarMainCourseId((long)100)
        );
        for(int i = 0 ; i < 3 ;++i){
            klassMapper.insertSelective(new Klass()
                                    .setId((long)(100+i))
                                    .setCourseId((long)100)
                                    .setGrade(2016)
                                    .setSerial(i)
                                    .setTime(String.valueOf(i))
                                    .setLocation(String.valueOf(i))
            );
        }
        for(int i = 0 ; i < 2 ;++i){
            klassMapper.insertSelective(new Klass()
                    .setId((long)(200+i))
                    .setCourseId((long)101)
                    .setGrade(2017)
                    .setSerial(i)
                    .setTime(String.valueOf(i))
                    .setLocation(String.valueOf(i))
            );
        }
    }

    @Test
    public void testSelectByCourseId() throws Exception {
        createCourseKlass();
        Round round = new Round().setId((long)1).setCourseId((long)100);
        List<Course> courseList = courseMapper.selcetCourseSubCourseByCourseId(round.getCourseId());

        for(Course course: courseList){
            logger.info(course.toString());
            List<Klass> klassList = course.getKlassList();
            if(klassList != null){
                for(Klass klass : klassList){
                    logger.info(klass.toString());
                }
            }
        }

        Assert.assertNotNull(courseList);
    }

}
