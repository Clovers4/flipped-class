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
        Student student = null;
        List<Long> studentIds = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            student = createStudent();
            studentMapper.insert(student);
            logger.info(student.toString());
            studentIds.add(student.getId());
        }
        teamMapper.insert(new Team()
                .setKlassId((long) random.nextInt(5))
                .setCourseId((long) 1)
                .setSerial(33)
                .setStatus(1)
                .setTeamName("test")
                .setLeaderId(student.getId()));
        Team team = teamMapper.selectOne(new Team()
                .setLeaderId(student.getId())
                .setCourseId((long) 1));
        logger.info(team.toString());
        for (int i = 0; i < studentIds.size(); i++) {
            KlassStudent klassStudent = new KlassStudent()
                    .setStudentId(studentIds.get(i))
                    .setKlassId((long) random.nextInt(5))
                    .setCourseId((long) 1)
                    .setTeamId(team.getId());
            logger.info(klassStudent.toString());
            klassStudentMapper.insert(klassStudent);
        }
        // 上为数据模拟 下为测试
        Team recordTeam = teamService.get((long) 1, student.getId());
        logger.info(team.toString());
        Assert.assertNotNull(team);
    }

    @Test
    public void testDeleteMember() throws Exception {
        Student student = null;
        List<Long> studentIdList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            student = createStudent();
            studentMapper.insert(student);
            logger.info(student.toString());
            studentIdList.add(student.getId());
        }
        teamMapper.insert(new Team()
                .setKlassId((long) random.nextInt(5))
                .setCourseId((long) 1)
                .setSerial(33)
                .setStatus(1)
                .setTeamName("test")
                .setLeaderId(student.getId()));
        Team team = teamMapper.selectOne(new Team()
                .setLeaderId(student.getId())
                .setCourseId((long) 1));
        logger.info(team.toString());
        for (int i = 0; i < studentIdList.size(); i++) {
            KlassStudent klassStudent = new KlassStudent()
                    .setStudentId(studentIdList.get(i))
                    .setKlassId((long) random.nextInt(5))
                    .setCourseId((long) 1)
                    .setTeamId(team.getId());
            logger.info(klassStudent.toString());
            klassStudentMapper.insert(klassStudent);
        }

        // 测试删除成员
        Boolean successMember = teamService.quitTeam(team.getId(), studentIdList.get(2));
        Assert.assertEquals(true, successMember);
        // 测试删除组长
        Boolean successLeader = teamService.quitTeam(team.getId(), team.getLeaderId());
        Assert.assertEquals(true, successLeader);
    }

    @Test
    public void testInsert() throws Exception {
        List<String> studentNums = new ArrayList<>();
        Long tmpStudentId = (long) 1;
        for (int i = 0; i < 5; i++) {
            String tmpStudentNum = "hello" + random.nextInt(1000);
            studentNums.add(tmpStudentNum);
            Student student = createStudent();
            logger.info(student.toString());
            student.setStudentNum(tmpStudentNum);
            studentMapper.insert(student);
            logger.info(student.toString());
            tmpStudentId = student.getId();
        }
        klassStudentMapper.insert(new KlassStudent()
                .setKlassId((long) 1)
                .setStudentId(tmpStudentId)
                .setCourseId((long) 1));

        Boolean success = teamService.create(tmpStudentId, (long) 77, "testInsert", studentNums);
        logger.info(studentNums.toString());
        logger.info(success.toString());
        Assert.assertEquals(true, success);

        Team team = teamMapper.selectOne(new Team()
                .setLeaderId(tmpStudentId)
                .setKlassId((long) 1)
                .setTeamName("testInsert"));
        logger.info(team.toString());
        Assert.assertNotNull(team);
    }

    @Test
    public void testDeleteByStudentNum() throws Exception {
        Student student = null;
        List<Long> studentIdList = new ArrayList<>();
        String tmpStudentNum = null;
        for (int i = 0; i < 4; i++) {
            if (i == 1) {
                tmpStudentNum = student.getStudentNum();
            }
            student = createStudent();
            studentMapper.insert(student);
            logger.info(student.toString());
            studentIdList.add(student.getId());
        }
        teamMapper.insert(new Team()
                .setKlassId((long) random.nextInt(5))
                .setCourseId((long) 1)
                .setSerial(33)
                .setStatus(1)
                .setTeamName("test")
                .setLeaderId(student.getId()));
        Team team = teamMapper.selectOne(new Team()
                .setLeaderId(student.getId())
                .setCourseId((long) 1));
        logger.info(team.toString());
        for (int i = 0; i < studentIdList.size(); i++) {
            KlassStudent klassStudent = new KlassStudent()
                    .setStudentId(studentIdList.get(i))
                    .setKlassId((long) random.nextInt(5))
                    .setCourseId((long) 1)
                    .setTeamId(team.getId());
            logger.info(klassStudent.toString());
            klassStudentMapper.insert(klassStudent);
        }

        Boolean success = teamService.removeMember(team.getId(), tmpStudentNum);
        logger.info(success.toString());
        Assert.assertEquals(true, success);
    }

    @Test
    public void testAddMember() throws Exception {
        Student student = null;
        List<Long> studentIdList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            student = createStudent();
            studentMapper.insert(student);
            logger.info(student.toString());
            studentIdList.add(student.getId());
        }
        teamMapper.insert(new Team()
                .setKlassId((long) random.nextInt(5))
                .setCourseId((long) 1)
                .setSerial(33)
                .setStatus(1)
                .setTeamName("test")
                .setLeaderId(student.getId()));
        Team team = teamMapper.selectOne(new Team()
                .setLeaderId(student.getId())
                .setCourseId((long) 1));
        logger.info(team.toString());
        for (int i = 0; i < studentIdList.size(); i++) {
            KlassStudent klassStudent = new KlassStudent()
                    .setStudentId(studentIdList.get(i))
                    .setKlassId((long) random.nextInt(5))
                    .setCourseId((long) 1)
                    .setTeamId(team.getId());
            logger.info(klassStudent.toString());
            klassStudentMapper.insert(klassStudent);
        }

        List<String> studentNumList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Student addStudent = createStudent();
            studentMapper.insert(addStudent);
            logger.info(addStudent.toString());
            studentNumList.add(addStudent.getStudentNum());
        }
        logger.info(studentNumList.toString());
        Boolean success = teamService.addMember(team.getId(), studentNumList);
        logger.info(studentNumList.toString());
        logger.info(success.toString());
        Assert.assertEquals(true, success);
    }

    @Test
    public void testDissolve() throws Exception {
        Student student = null;
        List<Long> studentIdList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            student = createStudent();
            studentMapper.insert(student);
            logger.info(student.toString());
            studentIdList.add(student.getId());
        }
        teamMapper.insert(new Team()
                .setKlassId((long) random.nextInt(5))
                .setCourseId((long) 1)
                .setSerial(33)
                .setStatus(1)
                .setTeamName("test")
                .setLeaderId(student.getId()));
        Team team = teamMapper.selectOne(new Team()
                .setLeaderId(student.getId())
                .setCourseId((long) 1));
        logger.info(team.toString());
        for (int i = 0; i < studentIdList.size(); i++) {
            KlassStudent klassStudent = new KlassStudent()
                    .setStudentId(studentIdList.get(i))
                    .setKlassId((long) random.nextInt(5))
                    .setCourseId((long) 1)
                    .setTeamId(team.getId());
            logger.info(klassStudent.toString());
            klassStudentMapper.insert(klassStudent);
        }
        Boolean success = teamService.dissolve(team.getId(), team.getLeaderId());
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
    public void testGetTeamByKlassIdAndStudentId()throws Exception{
        Long tempStudentId = (long)20;
        for(int i=0;i<3;i++){
            Team team = new Team()
                    .setStatus(1)
                    .setCourseId((long)20)
                    .setTeamName("test")
                    .setKlassId((long)20)
                    .setLeaderId((long)20)
                    .setSerial(20);
            teamMapper.insert(team);
            logger.info(team.toString());
            KlassStudent klassStudent = new KlassStudent()
                    .setCourseId((long)20)
                    .setKlassId((long)20)
                    .setStudentId(tempStudentId+(long)i)
                    .setTeamId(team.getId());
            klassStudentMapper.insert(klassStudent);
            logger.info(klassStudent.toString());
        }
        Long result = teamService.getTeamByKlassIdAndStudentId((long)20,(long)21);
        logger.info(result.toString());
        Assert.assertNotNull(result);
    }

}
