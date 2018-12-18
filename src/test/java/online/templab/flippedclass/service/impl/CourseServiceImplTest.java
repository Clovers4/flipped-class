package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.FlippedClassApplicationTest;
import online.templab.flippedclass.entity.Course;
import online.templab.flippedclass.entity.KlassStudent;
import online.templab.flippedclass.service.CourseService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author fj
 */
public class CourseServiceImplTest extends FlippedClassApplicationTest {

    @Autowired
    CourseService courseService;

    private Course createCourse(){
        return new Course()
                .setCourseName("test"+random.nextInt(1000))
                .setIntroduction("test")
                .setTeacherId((long)1)
                .setPresentationPercentage(30)
                .setQuestionPercentage(30)
                .setReportPercentage(40)
                .setTeamStartTime(new Date())
                .setTeamEndTime(new Date())
                .setTeamMainCourseId((long)1)
                .setSeminarMainCourseId((long)1);
    }

    @Test
    public void insert(){
        Course course = createCourse();
        logger.info(course.toString());
        Boolean success = courseService.insert(course);
        Assert.assertEquals(true,success);
    }

    @Test
    public void testlistByStudentId()throws Exception{
        /**
         * 使用简单的本地测试
         */
        List<Course> courseList = courseService.listByStudentId((long)1);
        logger.info(courseList.toString());
        Assert.assertNotNull(courseList);
    }

    @Test
    public void testUpdate()throws Exception{
        Course course = createCourse();
        course.setId((long)1);
        Boolean success = courseService.update(course);
        logger.info(course.toString());
        Assert.assertEquals(true,success);
    }
}
