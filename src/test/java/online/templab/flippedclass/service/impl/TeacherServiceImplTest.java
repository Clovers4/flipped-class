package online.templab.flippedclass.service.impl;

import com.github.pagehelper.Page;
import online.templab.flippedclass.FlippedClassApplicationTest;
import online.templab.flippedclass.entity.Teacher;
import online.templab.flippedclass.service.TeacherService;
import org.apache.ibatis.session.RowBounds;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Repeat;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class TeacherServiceImplTest extends FlippedClassApplicationTest {

    @Autowired
    TeacherService teacherService;

    private Teacher createTeacher() {
        return new Teacher()
                .setTeacherNum("test" + random.nextInt(1000))
                .setPassword("test")
                .setActivated(false)
                .setEmail("test" + random.nextInt(1000) + "@163.com")
                .setTeacherName("大天才");
    }

    @Test
    public void testInsert() throws Exception {
       teacherService.insert(createTeacher());
    }

    @Test
    public void testDeleteByAccount() throws Exception {
        Teacher teacher = createTeacher();
        teacherService.insert(teacher);
        Boolean success = teacherService.delete(teacher.getTeacherNum());
        Assert.assertEquals(true, success);
    }

    @Test
    public void testUpdateById() throws Exception {
        Teacher teacher = createTeacher();
        teacherService.insert(teacher);
        Boolean success = teacherService.updateById(new Teacher().setId(teacher.getId()).setPassword("abc"));
        Assert.assertEquals(true, success);
    }

    @Test
    public void testActivate() throws Exception {
        Teacher teacher = createTeacher();
        teacherService.insert(teacher);
        Boolean success = teacherService.activate(teacher.getId(), "abc", "123@163.com");
        Assert.assertEquals(true, success);
    }

    @Test
    public void testResetPassword() throws Exception {
        Teacher teacher = createTeacher();
        teacherService.insert(teacher);
        Boolean success = teacherService.resetPassword(teacher.getTeacherNum());
        Assert.assertEquals(true, success);
    }

    @Test
    public void testModifyPassword() throws Exception {
        Teacher teacher = createTeacher();
        teacherService.insert(teacher);
        Boolean success = teacherService.modifyPassword(teacher.getTeacherNum(), "eee");
        Assert.assertEquals(true, success);
    }

    @Test
    public void testGetPage() throws Exception {
        Page<Teacher> page = teacherService.getPage(new Teacher(), new RowBounds(1, 5));
        Assert.assertNotNull(page);
    }

    @Test
    public void testGetByTeacherNum() {
        Teacher teacher = createTeacher();
        teacherService.insert(teacher);
        Teacher record = teacherService.getByTeacherNum(teacher.getTeacherNum());
        Assert.assertNotNull(teacher);
        logger.info(teacher.toString());
    }

} 
