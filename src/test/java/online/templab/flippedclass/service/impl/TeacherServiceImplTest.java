package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.FlippedClassApplicationTest;
import online.templab.flippedclass.entity.Teacher;
import online.templab.flippedclass.service.TeacherService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Repeat;

public class TeacherServiceImplTest extends FlippedClassApplicationTest {

    @Autowired
    TeacherService teacherService;

    private Teacher createTeacher() {
        return new Teacher()
                .setAccount("test" + random.nextInt(1000))
                .setPassword("test")
                .setActive(false)
                .setEmail("test" + random.nextInt(1000) + "@163.com")
                .setTeacherName("帅哥");
    }

    @Test
    @Repeat(5)
    public void testInsert() throws Exception {
        Boolean success = teacherService.insert(createTeacher());
        Assert.assertEquals(true, success);
    }

    @Test
    public void testDelete() throws Exception {
//TODO: Test goes here... 
    }

    @Test
    public void testUpdate() throws Exception {
//TODO: Test goes here... 
    }

    @Test
    public void testActivate() throws Exception {
//TODO: Test goes here... 
    }

    @Test
    public void testResetPassword() throws Exception {
        Boolean success = teacherService.resetPassword("test72");
        Assert.assertEquals(true, success);
    }

    @Test
    public void testModifyPassword() throws Exception {
//TODO: Test goes here... 
    }

    @Test
    public void testGetPage() throws Exception {
//TODO: Test goes here... 
    }


} 
