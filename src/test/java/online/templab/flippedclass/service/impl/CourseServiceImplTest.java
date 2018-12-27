package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.FlippedClassApplicationTest;
import online.templab.flippedclass.entity.Course;
import online.templab.flippedclass.entity.Klass;
import online.templab.flippedclass.entity.KlassStudent;
import online.templab.flippedclass.entity.Student;
import online.templab.flippedclass.mapper.CourseMapper;
import online.templab.flippedclass.mapper.KlassMapper;
import online.templab.flippedclass.mapper.KlassStudentMapper;
import online.templab.flippedclass.mapper.StudentMapper;
import online.templab.flippedclass.service.CourseService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author fj
 */
@Transactional
public class CourseServiceImplTest extends FlippedClassApplicationTest {

    @Autowired
    CourseService courseService;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    KlassStudentMapper klassStudentMapper;

    @Autowired
    KlassMapper klassMapper;

    private Course createCourse() {
        return new Course()
                .setCourseName("test" + random.nextInt(1000))
                .setIntroduction("test")
                .setTeacherId((long) random.nextInt(100))
                .setPrePercentage(30)
                .setQuesPercentage(30)
                .setReportPercentage(40)
                .setTeamStartDate(new Date())
                .setTeamEndDate(new Date())
                .setTeamMainCourseId((long) 0)
                .setSeminarMainCourseId((long) 0);
    }

    private void createDataset(){
        for(int i = 0 ; i < 3 ; ++i){
            courseMapper.insertSelective(new Course()
                    .setId((long)i+100)
                    .setCourseName("test" + i)
                    .setIntroduction("test")
                    .setTeacherId((long) 100+i)
                    .setPrePercentage(30)
                    .setQuesPercentage(30)
                    .setReportPercentage(40)
                    .setTeamStartDate(new Date())
                    .setTeamEndDate(new Date())
                    .setTeamMainCourseId((long) 1)
                    .setSeminarMainCourseId((long) 1)
            );
        }

        studentMapper.insertSelective(new Student()
                                    .setId((long)100)
                                    .setStudentNum("test" + random.nextInt(1000))
                                    .setPassword("test")
                                    .setActivated(false)
                                    .setEmail("test" + random.nextInt(1000) + "@163.com")
                                    .setStudentName("student")
        );

        for (int i = 0; i < 3; ++i) {
            klassStudentMapper.insertSelective(new KlassStudent()
                            .setKlassId((long)100+i)
                            .setStudentId((long)100)
                            .setCourseId((long)100+i)
                            .setTeamId((long)100+i)
            );
        }

        for (int i = 0; i < 3; ++i) {
            klassMapper.insertSelective(new Klass()
                    .setId((long) (100 + i))
                    .setCourseId((long) 100+i)
                    .setGrade(2016)
                    .setSerial(i)
                    .setTime(String.valueOf(i))
                    .setLocation(String.valueOf(i))
            );
        }

    }

    @Test
    public void insert() {
        Course course = createCourse();
        logger.info(course.toString());
        Boolean success = courseService.insert(course);
        Assert.assertEquals(true, success);
    }

    @Test
    public void testlistByStudentId() throws Exception {
        /**
         * 使用简单的本地测试
         */
        List<Course> courseList = courseService.listByStudentId((long) 1);
        logger.info(courseList.toString());
        Assert.assertNotNull(courseList);
    }

    @Test
    public void testUpdate() throws Exception {
        Course course = createCourse();
        courseService.insert(course);
        course.setCourseName("update");
        Boolean success = courseService.update(course);
        logger.info(course.toString());
        Assert.assertEquals(true, success);
    }

    @Test
    public void testDelete() throws Exception {
        courseService.insert(new Course().setCourseName("OOAD")
                .setTeacherId((long) 1)
                .setId((long) 13)
                .setIntroduction("无")
                .setPrePercentage(30)
                .setQuesPercentage(20)
                .setReportPercentage(50)
                .setTeamStartDate(new Date())
                .setTeamEndDate(new Date()));
        courseService.delete((long) 13);
    }

    @Test
    public void testListCourseKlassByStudentId() throws Exception{
        createDataset();
        List<Map<String, Object>> maps = courseService.listCourseKlassByStudentId((long)100);
        logger.info(String.valueOf(maps.size()));
        for(Map<String,Object> map : maps){
            logger.info(map.get("course").toString());
            logger.info(map.get("klass").toString());
        }
    }

    @Test
    public void testListAllCourse()throws Exception{
        List<Course> courseList = courseService.listAllCourse();
        for(int i=0;i<courseList.size();i++){
            logger.info(courseList.get(i).toString());
        }
        Assert.assertNotNull(courseList);
    }
}
