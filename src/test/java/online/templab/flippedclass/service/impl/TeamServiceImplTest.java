package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.FlippedClassApplicationTest;
import online.templab.flippedclass.entity.*;
import online.templab.flippedclass.mapper.*;
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

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    KlassStudentMapper klassStudentMapper;

    @Autowired
    TeamMapper teamMapper;

    private Student createStudent() {
        return new Student()
                .setStudentNum("StudentNum" + random.nextInt(100))
                .setPassword("Password" + random.nextInt(100))
                .setEmail("email" + random.nextInt(100) + "@163.com")
                .setStudentName("Name" + random.nextInt(10))
                .setActivated(true);
    }

    @Test
    public void listUnTeamedStudentByCourseId() throws Exception {
        for (int i = 0; i < 5; i++) {
            Student student = createStudent();
            studentMapper.insert(student);
            logger.info(student.toString());
            KlassStudent klassStudent = new KlassStudent()
                    .setStudentId(student.getId())
                    .setKlassId((long) random.nextInt(5))
                    .setCourseId((long) 2);
            if (i % 2 == 0) {
                klassStudent.setTeamId((long) random.nextInt(10));
            }
            logger.info(klassStudent.toString());
            klassStudentMapper.insert(klassStudent);
        }
        List<Student> studentList = teamService.listUnTeamedStudentByCourseId((long) 2);
        logger.info(studentList.toString());
        int size = studentList.size();
        Boolean success = size > 0 ? true : false;
        Assert.assertEquals(true, success);
    }

    @Test
    public void testSelectTeam() throws Exception {
        Team recordTeam = teamService.get((long) 16, (long)208);
        logger.info(recordTeam.toString());
        Assert.assertNotNull(recordTeam);
    }

    @Test
    public void testQuitTeam() throws Exception {
        // 测试删除成员
        Boolean successMember = teamService.quitTeam((long) 2, (long) 172);
        Assert.assertEquals(true, successMember);
        // 测试删除组长
        Boolean successLeader = teamService.quitTeam((long) 2, (long) 197);
        Assert.assertEquals(true, successLeader);
    }

    @Test
    public void testInsert() throws Exception {
        List<String> studentNumList = new ArrayList<>();
        studentNumList.add("24320162202845");
        studentNumList.add("24320162202916");
        studentNumList.add("24320162202874");
        Boolean success = teamService.create((long) 210, (long) 23, "test", studentNumList);
        logger.info(success.toString());
        Assert.assertEquals(true,success);
    }

    @Test
    public void testRemoveMember() throws Exception {
        Boolean success = teamService.removeMember((long) 2, "24320162202832");
        logger.info(success.toString());
        Assert.assertEquals(true, success);
    }

    @Test
    public void testAddMember() throws Exception {
        List<String> studentNumList = new ArrayList<>();
        studentNumList.add("24320162202826");
        studentNumList.add("24320162202845");
        studentNumList.add("24320162202916");
        logger.info(studentNumList.toString());
        Boolean success = teamService.addMember((long) 2, studentNumList);
        logger.info(studentNumList.toString());
        logger.info(success.toString());
        Assert.assertEquals(true, success);
    }

    @Test
    public void testDissolve() throws Exception {
        Boolean success = teamService.dissolve((long) 2, (long) 197);
        logger.info(success.toString());
        Assert.assertEquals(true, success);
    }

    @Test
    public void testListByCourseId() throws Exception {
        for (int i = 0; i < 3; i++) {
            Student student = null;
            List<Long> studentIds = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                student = createStudent();
                studentMapper.insert(student);
                logger.info(student.toString());
                studentIds.add(student.getId());
            }
            teamMapper.insert(new Team()
                    .setCourseId((long) 1)
                    .setLeaderId(student.getId())
                    .setKlassId((long) random.nextInt(10))
                    .setTeamName("test")
                    .setSerial(random.nextInt(100))
                    .setStatus(1));
            Team team = teamMapper.selectOne(new Team()
                    .setLeaderId(student.getId())
                    .setCourseId((long) 1));
            logger.info(team.toString());
            for (int j = 0; j < studentIds.size(); j++) {
                KlassStudent klassStudent = new KlassStudent()
                        .setStudentId(studentIds.get(j))
                        .setKlassId((long) random.nextInt(5))
                        .setCourseId((long) 1)
                        .setTeamId(team.getId());
                logger.info(klassStudent.toString());
                klassStudentMapper.insert(klassStudent);
            }
        }
        List<Team> teamList = teamService.listByCourseId((long) 16);
        logger.info(String.valueOf(teamList.size()));
        for (int i = 0; i < teamList.size(); i++) {
            logger.info(teamList.get(i).toString());
        }
        Assert.assertNotNull(teamList);
    }

    @Test
    public void testGetTeamByKlassIdAndStudentId() throws Exception {
        Long result = teamService.getTeamByKlassIdAndStudentId((long) 21, (long) 103);
        logger.info(result.toString());
        Assert.assertNotNull(result);
    }

}
