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
                .setAccount("test" + random.nextInt(1000))
                .setPassword("test")
                .setActive(false)
                .setEmail("test" + random.nextInt(1000) + "@163.com")
                .setTeacherName("teacher");
    }

    @Test
    public void testInsert() throws Exception {
        Boolean success = teacherService.insert(createTeacher());
        Assert.assertEquals(true, success);
    }

    @Test
    public void testDeleteByAccount() throws Exception {
        Boolean success = teacherService.delete("test55");
        Assert.assertEquals(true, success);
    }

    @Test
    public void testUpdateById() throws Exception {
        Boolean success = teacherService.updateById(new Teacher().setId((long) 29).setPassword("abc"));
        Assert.assertEquals(true, success);
    }

    @Test
    public void testActivate() throws Exception {
        Boolean success = teacherService.activate((long) 33, "abc", "123@163.com");
        Assert.assertEquals(true, success);
    }

    @Test
    public void testResetPassword() throws Exception {
        Boolean success = teacherService.resetPassword("test70");
        Assert.assertEquals(true, success);
    }

    @Test
    public void testModifyPassword() throws Exception {
        Boolean success = teacherService.modifyPassword((long) 33, "eee");
        Assert.assertEquals(true, success);
    }

    @Test
    public void testGetPage() throws Exception {
        Page<Teacher> page = teacherService.getPage(new RowBounds(1, 5));
        Assert.assertNotNull(page);
    }

} 
