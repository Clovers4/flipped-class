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
    KlassTeamMapper klassTeamMapper;

    @Autowired
    TeamStudentMapper teamStudentMapper;

    @Autowired
    TeamMapper teamMapper;

    private Student createStudent() {
        return new Student()
                .setStudentNum("StudentNum" + random.nextInt(1000))
                .setPassword("Password")
                .setEmail("email" + random.nextInt(100) + "@163.com")
                .setStudentName("Name" + random.nextInt(10))
                .setActivated(true);
    }

    private Team createTeam() {
        return new Team()
                .setKlassId((long) 912)
                .setTeamName("testTeamName")
                .setStatus(0)
                .setSerial(20)
                .setCourseId((long) 911)
                .setLeaderId((long) random.nextInt(1000));
    }

    @Test
    public void listUnTeamedStudentByCourseId() throws Exception {
        // 模拟数据
        for (int i = 0; i < 5; i++) {
            // 学生
            Student student = createStudent();
            studentMapper.insert(student);
            logger.info(student.toString());
            // klass_student 关系
            KlassStudent klassStudent = new KlassStudent()
                    .setStudentId(student.getId())
                    .setKlassId((long) 911)
                    .setCourseId((long) 911);
            klassStudentMapper.insert(klassStudent);
            logger.info(klassStudent.toString());
            // klass_team 关系
            if (i == 0) {
                KlassTeam klassTeam = new KlassTeam()
                        .setKlassId((long) 911)
                        .setTeamId((long) 912);
                klassTeamMapper.insert(klassTeam);
                logger.info(klassTeam.toString());
            }
            if (i % 2 == 0) {
                TeamStudent teamStudent = new TeamStudent()
                        .setTeamId((long) 912)
                        .setStudentId(student.getId());
                teamStudentMapper.insert(teamStudent);
                logger.info(teamStudent.toString());
            }
        }
        // 测试
        List<Student> studentList = teamService.listUnTeamedStudentByCourseId((long) 911);
        logger.info(studentList.toString());
        int size = studentList.size();
        Boolean success = size > 0 ? true : false;
        Assert.assertEquals(true, success);
    }

    //    @Test
//    public void testSelectTeam() throws Exception {
//        Student student = null;
//        List<Long> studentIds = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            student = createStudent();
//            studentMapper.insert(student);
//            logger.info(student.toString());
//            studentIds.add(student.getId());
//        }
//        teamMapper.insert(new Team()
//                .setKlassId((long) random.nextInt(5))
//                .setCourseId((long) 1)
//                .setSerial(33)
//                .setStatus(1)
//                .setTeamName("test")
//                .setLeaderId(student.getId()));
//        Team team = teamMapper.selectOne(new Team()
//                .setLeaderId(student.getId())
//                .setCourseId((long) 1));
//        logger.info(team.toString());
//        for (int i = 0; i < studentIds.size(); i++) {
//            KlassStudent klassStudent = new KlassStudent()
//                    .setStudentId(studentIds.get(i))
//                    .setKlassId((long) random.nextInt(5))
//                    .setCourseId((long) 1)
//                    .setTeamId(team.getId());
//            logger.info(klassStudent.toString());
//            klassStudentMapper.insert(klassStudent);
//        }
//        // 上为数据模拟 下为测试
//        Team recordTeam = teamService.get((long) 1, student.getId());
//        logger.info(team.toString());
//        Assert.assertNotNull(team);
//    }
//
//    @Test
//    public void testDeleteMember() throws Exception {
//        Student student = null;
//        List<Long> studentIdList = new ArrayList<>();
//        for (int i = 0; i < 6; i++) {
//            student = createStudent();
//            studentMapper.insert(student);
//            logger.info(student.toString());
//            studentIdList.add(student.getId());
//        }
//        teamMapper.insert(new Team()
//                .setKlassId((long) random.nextInt(5))
//                .setCourseId((long) 1)
//                .setSerial(33)
//                .setStatus(1)
//                .setTeamName("test")
//                .setLeaderId(student.getId()));
//        Team team = teamMapper.selectOne(new Team()
//                .setLeaderId(student.getId())
//                .setCourseId((long) 1));
//        logger.info(team.toString());
//        for (int i = 0; i < studentIdList.size(); i++) {
//            KlassStudent klassStudent = new KlassStudent()
//                    .setStudentId(studentIdList.get(i))
//                    .setKlassId((long) random.nextInt(5))
//                    .setCourseId((long) 1)
//                    .setTeamId(team.getId());
//            logger.info(klassStudent.toString());
//            klassStudentMapper.insert(klassStudent);
//        }
//
//        // 测试删除成员
//        Boolean successMember = teamService.quitTeam(team.getId(), studentIdList.get(2));
//        Assert.assertEquals(true, successMember);
//        // 测试删除组长
//        Boolean successLeader = teamService.quitTeam(team.getId(), team.getLeaderId());
//        Assert.assertEquals(true, successLeader);
//    }
//
//    @Test
//    public void testInsert() throws Exception {
//        List<String> studentNums = new ArrayList<>();
//        Long tmpStudentId = (long) 1;
//        for (int i = 0; i < 5; i++) {
//            String tmpStudentNum = "hello" + random.nextInt(1000);
//            studentNums.add(tmpStudentNum);
//            Student student = createStudent();
//            logger.info(student.toString());
//            student.setStudentNum(tmpStudentNum);
//            studentMapper.insert(student);
//            logger.info(student.toString());
//            tmpStudentId = student.getId();
//        }
//        klassStudentMapper.insert(new KlassStudent()
//                .setKlassId((long) 1)
//                .setStudentId(tmpStudentId)
//                .setCourseId((long) 1));
//
//        Boolean success = teamService.create(tmpStudentId, (long) 1, "testInsert", studentNums);
//        logger.info(studentNums.toString());
//        logger.info(success.toString());
//        Assert.assertEquals(true, success);
//
//        Team team = teamMapper.selectOne(new Team()
//                .setLeaderId(tmpStudentId)
//                .setKlassId((long) 1)
//                .setTeamName("testInsert"));
//        logger.info(team.toString());
//        Assert.assertNotNull(team);
//    }
//
//    @Test
//    public void testDeleteByStudentNum() throws Exception {
//        Student student = null;
//        List<Long> studentIdList = new ArrayList<>();
//        String tmpStudentNum = null;
//        for (int i = 0; i < 4; i++) {
//            if (i == 1) {
//                tmpStudentNum = student.getStudentNum();
//            }
//            student = createStudent();
//            studentMapper.insert(student);
//            logger.info(student.toString());
//            studentIdList.add(student.getId());
//        }
//        teamMapper.insert(new Team()
//                .setKlassId((long) random.nextInt(5))
//                .setCourseId((long) 1)
//                .setSerial(33)
//                .setStatus(1)
//                .setTeamName("test")
//                .setLeaderId(student.getId()));
//        Team team = teamMapper.selectOne(new Team()
//                .setLeaderId(student.getId())
//                .setCourseId((long) 1));
//        logger.info(team.toString());
//        for (int i = 0; i < studentIdList.size(); i++) {
//            KlassStudent klassStudent = new KlassStudent()
//                    .setStudentId(studentIdList.get(i))
//                    .setKlassId((long) random.nextInt(5))
//                    .setCourseId((long) 1)
//                    .setTeamId(team.getId());
//            logger.info(klassStudent.toString());
//            klassStudentMapper.insert(klassStudent);
//        }
//
//        Boolean success = teamService.removeMember(team.getId(), tmpStudentNum);
//        logger.info(success.toString());
//        Assert.assertEquals(true, success);
//    }
//
    @Test
    public void testAddMember() throws Exception {
        // 模拟数据
        // 插入一个队伍
        Team team = createTeam();
        teamMapper.insert(team);
        logger.info(team.toString());
        // 添加组员的studentNum
        List<String> studentNumList = new ArrayList<>();
        for(int i=0;i<3;i++){
            studentNumList.add("studentNum"+i);
            Student student = createStudent();
            student.setStudentNum("studentNum"+i);
            studentMapper.insert(student);
            logger.info(student.toString());
        }
        logger.info(studentNumList.toString());
        Boolean success = teamService.addMember(team.getId(),studentNumList);
        Assert.assertEquals(true,success);
    }

    @Test
    public void testDissolve() throws Exception {
        // 模拟数据
        Long deleteTeamId = null;
        Long deleteLeadId = null;
        for (int i = 0; i < 5; i++) {
            // 队伍
            Team team = createTeam();
            teamMapper.insert(team);
            logger.info(team.toString());
            // klass_team 关系
            KlassTeam klassTeam = new KlassTeam()
                    .setKlassId((long) 912)
                    .setTeamId(team.getId());
            klassTeamMapper.insert(klassTeam);
            logger.info(klassTeam.toString());
            // team_student 关系
            TeamStudent teamStudent = new TeamStudent()
                    .setTeamId(team.getId())
                    .setStudentId((long) random.nextInt(1000));
            teamStudentMapper.insert(teamStudent);
            logger.info(teamStudent.toString());
            if (i == 2) {
                deleteTeamId = team.getId();
                deleteLeadId = team.getLeaderId();
                logger.info(deleteTeamId.toString());
                logger.info(deleteLeadId.toString());
            }
        }
        // 测试
        Boolean success = teamService.dissolve(deleteTeamId, deleteLeadId);
        logger.info(success.toString());
        Assert.assertEquals(true, success);
    }
//
//    @Test
//    public void testListByCourseId() throws Exception {
//        for (int i = 0; i < 3; i++) {
//            Student student = null;
//            List<Long> studentIds = new ArrayList<>();
//            for (int j = 0; j < 3; j++) {
//                student = createStudent();
//                studentMapper.insert(student);
//                logger.info(student.toString());
//                studentIds.add(student.getId());
//            }
//            teamMapper.insert(new Team()
//                    .setCourseId((long) 1)
//                    .setLeaderId(student.getId())
//                    .setKlassId((long) random.nextInt(10))
//                    .setTeamName("test")
//                    .setSerial(random.nextInt(100))
//                    .setStatus(1));
//            Team team = teamMapper.selectOne(new Team()
//                    .setLeaderId(student.getId())
//                    .setCourseId((long) 1));
//            logger.info(team.toString());
//            for (int j = 0; j < studentIds.size(); j++) {
//                KlassStudent klassStudent = new KlassStudent()
//                        .setStudentId(studentIds.get(j))
//                        .setKlassId((long) random.nextInt(5))
//                        .setCourseId((long) 1)
//                        .setTeamId(team.getId());
//                logger.info(klassStudent.toString());
//                klassStudentMapper.insert(klassStudent);
//            }
//        }
//        List<Team> teamList = teamService.listByCourseId((long) 16);
//        logger.info(String.valueOf(teamList.size()));
//        for (int i = 0; i < teamList.size(); i++) {
//            logger.info(teamList.get(i).toString());
//        }
//        Assert.assertNotNull(teamList);
//    }

}
