package online.templab.flippedclass.service.impl;

import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
import online.templab.flippedclass.FlippedClassApplicationTest;
import online.templab.flippedclass.entity.*;
import online.templab.flippedclass.mapper.*;
import online.templab.flippedclass.service.TeamService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
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

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    ConflictCourseStrategyMapper conflictCourseStrategyMapper;

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
        List<Student> studentList = teamService.listUnTeamedStudentByCourseId((long) 16);
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
        Assert.assertNotNull(successMember);
        // 测试删除组长
        Boolean successLeader = teamService.quitTeam((long) 2, (long) 197);
        Assert.assertNotNull(successLeader);
    }

    @Test
    public void testCteate() throws Exception {
        List<String> studentNumList = new ArrayList<>();
        studentNumList.add("24320162202845");
        studentNumList.add("24320162202916");
        studentNumList.add("24320162202818");
        Boolean success = teamService.create(new Team());
        logger.info(success.toString());
        Assert.assertNotNull(success);
    }

    @Test
    public void testRemoveMember() throws Exception {
        Boolean success = teamService.removeMember((long) 2, "24320162202832");
        logger.info(success.toString());
        Assert.assertNotNull(success);
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
        Assert.assertNotNull(success);
    }

    @Test
    public void testDissolve() throws Exception {
        Boolean success = teamService.dissolve((long) 2, (long) 197);
        logger.info(success.toString());
        Assert.assertNotNull(success);
    }

    @Test
    public void testListByCourseId() throws Exception {
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

    @Test
    public void testValidOneTeamState(){
//        int state = teamService.validOneTeamState((long)25);
//        logger.info(String.valueOf(state));
//        Assert.assertEquals(0, state);
//
//        state = teamService.validOneTeamState((long)24);
//        logger.info(String.valueOf(state));
//        Assert.assertEquals(0, state);
//
//        state = teamService.validOneTeamState((long)22);
//        logger.info(String.valueOf(state));
//        Assert.assertEquals(1, state);
        for(int i = 2 ; i <28;++i ){
            int state = teamService.validOneTeamState((long)i);
            logger.info(String.valueOf(i));
            logger.info(String.valueOf(state));
        }
    }

    @Test
    public void testValidAllTeamByCourseId(){
        teamService.validAllTeamByCourseId((long)16);
        logger.info(teamMapper.selectByPrimaryKey((long)25).toString());
    }

    @Test
    public void testListTeamStategy(){
        List<TeamStrategy> teamStrategyList = teamService.listTeamStategy(16L);
        for( int i  = 0 ; i < teamStrategyList.size() ; ++i){
            logger.info(teamStrategyList.get(i).toString());
        }
        logger.info(teamStrategyList.get(0).getCourseStrategyList().toString());
    }

    @Test
    public void testInsertTeamStratgyList(){

        Course course =  new Course()
                .setCourseName("test" + random.nextInt(1000))
                .setIntroduction("test")
                .setTeacherId((long) random.nextInt(100))
                .setPrePercentage(30)
                .setQuesPercentage(30)
                .setReportPercentage(40)
                .setTeamStartDate(new Date())
                .setTeamEndDate(new Date())
                .setTeamMainCourseId((long) 0)
                .setSeminarMainCourseId((long) 0)
                .setId(100L);
        courseMapper.insert(course);

        List<TeamStrategy> teamStrategyList = new LinkedList<>();

        TeamStrategy teamStrategy = new TeamStrategy()
                .setCourseId(100L)
                .setStrategyName("MemberLimitStrategy")
                ;

        List<CourseStrategy> courseStrategyList1 = new LinkedList<>();
        MemberLimitStrategy memberLimitStrategy = new MemberLimitStrategy().setMax(200).setMin(100);
        courseStrategyList1.add(memberLimitStrategy);
        teamStrategy.setCourseStrategyList(courseStrategyList1);
        teamStrategyList.add(teamStrategy);


        teamStrategy = new TeamStrategy()
                .setCourseId(100L)
                .setStrategyName("TeamOrStrategy");
        TeamOrStrategy teamOrStrategy = new TeamOrStrategy().setStrategyName("CourseMemberLimitStrategy");
        CourseMemberLimitStrategy courseMemberLimitStrategy = new CourseMemberLimitStrategy().setCourseId(101L).setMin(30).setMax(null);
        courseStrategyList1 = new LinkedList<>();
        courseStrategyList1.add(courseMemberLimitStrategy);
        teamOrStrategy.setCourseStrategyList(courseStrategyList1);

        List<CourseStrategy> courseStrategyList2 = new LinkedList<>();
        courseStrategyList2.add(teamOrStrategy);
        teamStrategy.setCourseStrategyList(courseStrategyList2);

        teamOrStrategy = new TeamOrStrategy().setStrategyName("CourseMemberLimitStrategy");
        courseMemberLimitStrategy = new CourseMemberLimitStrategy().setCourseId(102L).setMin(20).setMax(50);
        courseStrategyList1 = new LinkedList<>();
        courseStrategyList1.add(courseMemberLimitStrategy);
        teamOrStrategy.setCourseStrategyList(courseStrategyList1);
        teamStrategy.getCourseStrategyList().add(teamOrStrategy);

        teamStrategyList.add(teamStrategy);

        teamStrategy = new TeamStrategy()
                .setCourseId(100L)
                .setStrategyName("ConflictCourseStrategy");

        for(int i = 0 ; i < 3 ; ++i){
            ConflictCourseStrategy conflictCourseStrategy = new ConflictCourseStrategy().setCourseId((long)105+i);
            if(i == 0){
                courseStrategyList1 = new LinkedList<>();
                courseStrategyList1.add(conflictCourseStrategy);
                teamStrategy.setCourseStrategyList(courseStrategyList1);
            }
            else{
                teamStrategy.getCourseStrategyList().add(conflictCourseStrategy);
            }

        }
        teamStrategyList.add(teamStrategy);

        teamService.insertTeamStratgyList(teamStrategyList);
        logger.info(conflictCourseStrategyMapper.select(new ConflictCourseStrategy().setId(103L)).toString());

    }

    @Test
    public void testGetByPrimaryKey()throws Exception{
        Team team = teamService.getByPrimaryKey((long)25);
        logger.info(team.toString());
        Assert.assertNotNull(team);
    }
}
