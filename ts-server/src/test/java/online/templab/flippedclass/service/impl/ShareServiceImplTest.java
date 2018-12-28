package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.FlippedClassApplicationTest;
import online.templab.flippedclass.entity.Course;
import online.templab.flippedclass.entity.ShareSeminarApplication;
import online.templab.flippedclass.entity.ShareTeamApplication;
import online.templab.flippedclass.mapper.CourseMapper;
import online.templab.flippedclass.mapper.ShareSeminarApplicationMapper;
import online.templab.flippedclass.mapper.ShareTeamApplicationMapper;
import online.templab.flippedclass.service.CourseService;
import online.templab.flippedclass.service.ShareService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author jh
 */
@Transactional
public class ShareServiceImplTest extends FlippedClassApplicationTest {

    @Autowired
    ShareService shareService;

    @Autowired
    CourseService courseService;

    @Autowired
    ShareTeamApplicationMapper shareTeamApplicationMapper;

    @Autowired
    ShareSeminarApplicationMapper shareSeminarApplicationMapper;

    @Autowired
    CourseMapper courseMapper;

    private Course createCourse() {
        return new Course()
                .setCourseName("test" + random.nextInt(1000))
                .setIntroduction("test")
                .setTeacherId((long) random.nextInt(100))
                .setPrePercentage(30)
                .setQuesPercentage(30)
                .setReportPercentage(40)
                .setTeamStartDate(new Date())
                .setTeamEndDate(new Date());
    }

    @Test
    public void testSelectShareTeamMainCourseByPrimaryKey() throws Exception {
        // 主课程
        Course mainCourse = createCourse();
        courseService.insert(mainCourse);
        logger.info(mainCourse.toString());
        // 从课程
        Course subCourse = createCourse();
        subCourse.setTeamMainCourseId(mainCourse.getId());
        courseService.insert(subCourse);
        logger.info(subCourse.toString());
        // 测试(id 为从课程id 时候)
        Course testCourse = shareService.getShareTeamMainCourse(subCourse.getId());
        logger.info(testCourse.toString());
        Assert.assertEquals(testCourse.getId(), mainCourse.getId());
        // 测试(id 为主课程id 的时候)
        testCourse = shareService.getShareTeamMainCourse(mainCourse.getId());
        logger.info(testCourse.toString());
        Assert.assertEquals(testCourse.getId(), mainCourse.getId());
    }

    @Test
    public void testSelectShareSeminarMainCourseByPrimaryKey() throws Exception {
        // 主课程
        Course mainCourse = createCourse();
        courseService.insert(mainCourse);
        logger.info(mainCourse.toString());
        // 从课程
        Course subCourse = createCourse();
        subCourse.setSeminarMainCourseId(mainCourse.getId());
        courseService.insert(subCourse);
        logger.info(subCourse.toString());
        // 测试(id 为从课程id 时候)
        Course testCourse = shareService.getShareSeminarMainCourse(subCourse.getId());
        logger.info(testCourse.toString());
        Assert.assertEquals(testCourse.getId(), mainCourse.getId());
        // 测试(id 为主课程id 的时候)
        testCourse = shareService.getShareTeamMainCourse(mainCourse.getId());
        logger.info(testCourse.toString());
        Assert.assertEquals(testCourse.getId(), mainCourse.getId());
    }

    @Test
    public void testListShareTeamSubCourse() throws Exception {
        Course mainCourse = createCourse();
        courseService.insert(mainCourse);
        logger.info(mainCourse.toString());
        Long tmpSubCourseId = null;
        for (int i = 0; i < 5; i++) {
            Course subCourse = createCourse();
            subCourse.setTeamMainCourseId(mainCourse.getId());
            courseService.insert(subCourse);
            logger.info(subCourse.toString());
            if (i == 3) {
                tmpSubCourseId = subCourse.getId();
            }
        }
        // 插入一个额外的主课程
        courseService.insert(createCourse());
        // 测试 id是主课程id
        List<Course> courseList = shareService.listShareTeamSubCourse(mainCourse.getId());
        logger.info(courseList.toString());
        Assert.assertNotNull(courseList);
        // 测试 id是从课程id
        courseList = shareService.listShareTeamSubCourse(tmpSubCourseId);
        logger.info(courseList.toString());
        Assert.assertEquals(1, courseList.size());
    }

    @Test
    public void testListShareSeminarSubCourse() throws Exception {
        Course mainCourse = createCourse();
        courseService.insert(mainCourse);
        logger.info(mainCourse.toString());
        Long tmpSubCourseId = null;
        for (int i = 0; i < 5; i++) {
            Course subCourse = createCourse();
            subCourse.setSeminarMainCourseId(mainCourse.getId());
            courseService.insert(subCourse);
            logger.info(subCourse.toString());
            if (i == 3) {
                tmpSubCourseId = subCourse.getId();
            }
        }
        // 插入一个额外的主课程
        courseService.insert(createCourse());
        // 测试 id是主课程id
        List<Course> courseList = shareService.listShareSeminarSubCourse(mainCourse.getId());
        logger.info(courseList.toString());
        Assert.assertNotNull(courseList);
        // 测试 id是从课程id
        courseList = shareService.listShareTeamSubCourse(tmpSubCourseId);
        logger.info(courseList.toString());
        Assert.assertEquals(1, courseList.size());
    }

    @Test
    public void testSendShareTeamApplication() {
        shareService.sendShareTeamApplication(new ShareTeamApplication()
                .setMainCourseId(123L)
                .setSubCourseId(222L)
                .setTeacherId(111L));
        logger.info(shareTeamApplicationMapper.select(new ShareTeamApplication().setTeacherId(111L)).toString());
    }

    @Test
    public void testSendShareSeminarApplication() {
        shareService.sendShareSeminarApplication(new ShareSeminarApplication()
                .setMainCourseId(321L)
                .setSubCourseId(222L)
                .setTeacherId(111L));
        logger.info(shareSeminarApplicationMapper.select(new ShareSeminarApplication().setTeacherId(111L)).toString());
    }

    @Test
    public void testUpdateShareTeamApplication() {
        shareService.sendShareSeminarApplication(new ShareSeminarApplication()
                .setMainCourseId(16L)
                .setSubCourseId(17L)
                .setTeacherId(3L)
                .setId(11L));
        shareService.processShareSeminarApplication(11L, true);
        logger.info(shareSeminarApplicationMapper.select(new ShareSeminarApplication().setTeacherId(3L)).toString());
    }

    @Test
    public void testCancelShareSeminarApplication() {
        shareService.sendShareSeminarApplication(new ShareSeminarApplication()
                .setMainCourseId(16L)
                .setSubCourseId(17L)
                .setTeacherId(3L)
                .setId(13L));
        shareService.cancelShareSeminarApplication(11L);
        logger.info(shareSeminarApplicationMapper.select(new ShareSeminarApplication().setTeacherId(3L)).toString());
    }

    @Test
    public void testCancelShareTeamApplication(){
        shareService.cancelShareTeamApplication(4L);
    }

    @Test
    public void testListCanShareCourses() throws Exception {
        Course shareCourse = createCourse();
        courseMapper.insert(shareCourse);
        logger.info(shareCourse.toString());
        for (int i = 0; i < 5; i++) {
            Course course = createCourse();
            if (i == 1) {
                course.setSeminarMainCourseId(shareCourse.getId());
            }
            if (i == 2) {
                course.setTeamMainCourseId(shareCourse.getId());
            }
            courseMapper.insert(course);
            logger.info(course.toString());
        }
        // 测试共享讨论课
        List<Course> courseList = shareService.listCanShareCourses(shareCourse.getId(), 0);
        logger.info(courseList.toString());
        Assert.assertNotNull(courseList.size());
        // 测试共享组队
        courseList = shareService.listCanShareCourses(shareCourse.getId(), 1);
        logger.info(courseList.toString());
        Assert.assertNotNull(courseList.size());
    }

    @Rollback(false)
    @Test
    public void testProcessShareTeamApplication(){
        shareService.cancelShareTeamApplication(2L);
        shareService.processShareTeamApplication(2L,true);
    }
}
