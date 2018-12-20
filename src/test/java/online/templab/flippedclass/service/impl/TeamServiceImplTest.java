package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.FlippedClassApplicationTest;
import online.templab.flippedclass.entity.Student;
import online.templab.flippedclass.entity.Team;
import online.templab.flippedclass.service.TeamService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fj
 */
@Transactional
public class TeamServiceImplTest extends FlippedClassApplicationTest {

    @Autowired
    TeamService teamService;

    @Test
    public void listUnTeamedStudentByCourseId() throws Exception {
        List<Student> studentList = teamService.listUnTeamedStudentByCourseId((long) 1);
        logger.info(studentList.toString());
        int size = studentList.size();
        Boolean success = size > 0 ? true : false;
        Assert.assertEquals(true, success);
    }

    @Test
    public void testSelectTeam() throws Exception {
        Team team = teamService.get((long) 1, (long) 1);
        logger.info(team.toString());
        Assert.assertNotNull(team);
    }

    @Test
    public void testDeleteMember() throws Exception {
        Boolean successMember = teamService.quitTeam((long) 1, (long) 1);
        Assert.assertEquals(true, successMember);

        Boolean successLeader = teamService.quitTeam((long) 1, (long) 7);
        Assert.assertEquals(true, successLeader);
    }

    @Test
    public void testInsert() throws Exception {
        List<Long> studentNum = new ArrayList<>();
        studentNum.add((long) 12);
        studentNum.add((long) 13);
        studentNum.add((long) 14);
        Boolean success = teamService.create((long) 2, (long) 1, "helloWorld", studentNum);
        logger.info(studentNum.toString());
        logger.info(success.toString());
        Assert.assertEquals(true, success);
    }

    @Test
    public void testDeleteByStudentNum() throws Exception {
        Boolean success = teamService.removeMember((long) 2, "9");
        logger.info(success.toString());
        Assert.assertEquals(true, success);
    }

    @Test
    public void testAddMember() throws Exception {
        List<String> studentNumList = new ArrayList<>();
        studentNumList.add("15");
        studentNumList.add("16");
        studentNumList.add("17");
        Boolean success = teamService.addMember((long) 3, (long) 6, studentNumList);
        logger.info(studentNumList.toString());
        logger.info(success.toString());
        Assert.assertEquals(true, success);
    }

    @Test
    public void testDissolve() throws Exception {
        Boolean success = teamService.dissolve((long) 1, (long) 1);
        logger.info(success.toString());
        Assert.assertEquals(true, success);
    }

    @Test
    public void testListByCourseId() throws Exception {
        List<Team> teamList = teamService.listByCourseId((long) 1);
        logger.info(teamList.toString());
        Assert.assertNotNull(teamList);
    }
}
