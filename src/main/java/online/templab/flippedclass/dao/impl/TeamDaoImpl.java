package online.templab.flippedclass.dao.impl;

import online.templab.flippedclass.dao.TeamDao;
import online.templab.flippedclass.entity.*;
import online.templab.flippedclass.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author fj
 */
@Component
public class TeamDaoImpl implements TeamDao {


    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private KlassStudentMapper klassStudentMapper;

    @Autowired
    private KlassMapper klassMapper;

    @Autowired
    MemberLimitStrategyMapper memberLimitStrategyMapper;

    @Autowired
    ConflictCourseStrategyMapper conflictCourseStrategyMapper;

    @Autowired
    CourseMemberLimitStrategyMapper courseMemberLimitStrategyMapper;

    @Autowired
    TeamAndStrategyMapper teamAndStrategyMapper;

    @Autowired
    TeamOrStrategyMapper teamOrStrategyMapper;

    @Autowired
    TeamStrategyMapper teamStrategyMapper;

    @Autowired
    TeamStudentMapper teamStudentMapper;

    @Autowired
    KlassTeamMapper klassTeamMapper;

    @Autowired
    CourseMapper courseMapper;

    void getSubStrategy(CourseStrategy courseStrategy){

        if(courseStrategy instanceof TeamStrategy || courseStrategy instanceof TeamOrStrategy || courseStrategy instanceof TeamAndStrategy){
            String strategyName = courseStrategy.getMyStrategyName();
            CourseStrategy subCourseStrategy= null;
            List<CourseStrategy> courseStrategyList = new LinkedList<>();

            if(strategyName.equals("TeamAndStrategy")){
//               subCourseStrategy = new TeamAndStrategy()
//                                .setStrategyId(courseStrategy.getMyStrategyId());
//                courseStrategyList = teamAndStrategyMapper.select(subCourseStrategy );
                List<TeamAndStrategy> teamAndStrategyList = teamAndStrategyMapper.select(new TeamAndStrategy().setId(courseStrategy.getMyStrategyId()) );
                for(TeamAndStrategy teamAndStrategy: teamAndStrategyList){
                    courseStrategyList.add(teamAndStrategy);
                }
            }
            else if(strategyName.equals("TeamOrStrategy")){
//                subCourseStrategy = new TeamOrStrategy()
//                        .setStrategyId(courseStrategy.getMyStrategyId());
//                courseStrategyList = teamOrStrategyMapper.select(subCourseStrategy );
                List<TeamOrStrategy> teamOrStrategyList = teamOrStrategyMapper.select(new TeamOrStrategy().setId(courseStrategy.getMyStrategyId()) );
                for(TeamOrStrategy teamOrStrategy: teamOrStrategyList){
                    courseStrategyList.add(teamOrStrategy);
                }
            }
            else if(strategyName.equals("MemberLimitStrategy")){
                MemberLimitStrategy memberLimitStrategy = memberLimitStrategyMapper.selectByPrimaryKey(courseStrategy.getMyStrategyId());
                courseStrategyList.add(memberLimitStrategy);
            }
            else if(strategyName.equals("CourseMemberLimitStrategy")){
                CourseMemberLimitStrategy courseMemberLimitStrategy = courseMemberLimitStrategyMapper.selectByPrimaryKey(courseStrategy.getMyStrategyId());
                courseStrategyList.add(courseMemberLimitStrategy);
            }
            else if(strategyName.equals("ConflictCourseStrategy")){
                List<ConflictCourseStrategy> conflictCourseStrategyList = conflictCourseStrategyMapper.select(new ConflictCourseStrategy().setId(courseStrategy.getMyStrategyId()));
                for(ConflictCourseStrategy conflictCourseStrategy: conflictCourseStrategyList){
                    courseStrategyList.add(conflictCourseStrategy);
                }
            }
//                courseStrategyMapper = (CourseStrategyMapper) Class.forName(CourseStrategyMapper.class.getPackage().getName() +"." +strategyMapperName ).newInstance();
//                subCourseStrategy = (CourseStrategy) Class.forName(CourseStrategy.class.getPackage().getName() +"." +courseStrategy.getMyStrategyName() ).newInstance();
//                subCourseStrategy.setMyStrategyName(courseStrategy.getMyStrategyName());
//                subCourseStrategy.setMyStrategyId(courseStrategy.getMyStrategyId());

            for(int j = 0 ; j < courseStrategyList.size();++j){
                getSubStrategy(courseStrategyList.get(j));
            }

            courseStrategy.setCourseStrategyList(courseStrategyList);
        }
        return;
    }

    @Override
    public Team selectTeamValid(Long teamId) {

        Team team =teamMapper.selectByTeamId(teamId);
        team.setAllStudents(studentMapper.selectTeamMerberCourseIdByTeamId(teamId));
        List<TeamStrategy> teamStrategyList = team.getTeamStrategyList();
        for(int i = 0 ; i < teamStrategyList.size(); ++i){
            TeamStrategy teamStrategy = teamStrategyList.get(i);
            getSubStrategy(teamStrategy);
        }
        return team;
    }

    @Override
    public List<Team> selectByCourseId(Long courseId) {
        //找到所有klass
        List<Long>klassIds=klassMapper.selectIdByCourseId(courseId);
        List<Team>resultTeam=new LinkedList<>();
        for(Long klassId:klassIds){
            Klass klass=klassMapper.selectByPrimaryKey(klassId);
            List<KlassTeam> klassTeams=klassTeamMapper.select(new KlassTeam().setKlassId(klassId));
            for(KlassTeam klassTeam:klassTeams){
                List<Student>students=studentMapper.selectStudentsByKlassIdTeamId(klassId,klassTeam.getTeamId());
                Team team=teamMapper.selectOne(new Team().setId(klassTeam.getTeamId()));
                team.setKlass(klass);
                List<Student> memberStudent=new LinkedList<>();
                Student leader=null;
                for(Student student:students){
                    if(!student.getId().equals(team.getLeaderId())){
                        memberStudent.add(student);
                    }
                    else {
                        leader=student;
                    }
                }
                //如果组长不空直接加入
                if(leader!=null){
                    team.setLeader(leader);
                }
                else{
                    team.setLeader(new Student().setStudentName("无").setStudentNum("无"));
                }
                team.setStudents(memberStudent);
                resultTeam.add(team);
            }
        }
        return resultTeam;
    }

    @Override
    public List<Student> selectUnTeamedStudentByCourseId(Long courseId) {
        return studentMapper.selectUnTeamedStudentByCourseId(courseId);
    }

    @Override
    public Team selectTeam(Long courseId, Long studentId) {
        // 获取队伍id
        Long klassId = klassStudentMapper.selectOne(new KlassStudent().setCourseId(courseId).setStudentId(studentId)).getKlassId();
        KlassTeam klassTeam = klassTeamMapper.selectByKlassIdAndStudentId(klassId, studentId);
        System.out.print(klassTeam.getTeamId());
        // 获取队伍成员
        List<Student> member = studentMapper.selectTeamMerberCourseIdByTeamId(klassTeam.getTeamId());
        // 通过 teamId 获取 team
        Team team = teamMapper.selectByPrimaryKey(klassTeam.getTeamId());
        // 队长
        Student leader = new Student();
        for (int i = 0; i < member.size(); i++) {
            if (member.get(i).getId().equals(team.getLeaderId())) {
                leader = member.get(i);
                member.remove(i);
                break;
            }
        }
        Klass klass = klassMapper.selectByPrimaryKey(klassId);
        // 组装 team
        team.setLeader(leader)
                .setStudents(member)
                .setKlass(klass)
                .setKlassSerial((byte) 1);
        return team;
    }

    @Override
    public Boolean deleteMemberById(Long teamId, Long studentId) {
        Team team = teamMapper.selectByPrimaryKey(teamId);
        if (team == null) {
            return true;
        }
        // 如果这个人是队长 ， 那么需要删除整个组
        if (studentId.equals(team.getLeaderId())) {
            int deleteTeam = teamMapper.deleteByPrimaryKey(team.getId());
            int deleteRelation = teamStudentMapper.delete(new TeamStudent().setTeamId(teamId));
            return (deleteRelation + deleteTeam) > 0;
        } else {
            int deleteRelation = teamStudentMapper.delete(new TeamStudent().setStudentId(studentId).setTeamId(teamId));
            return deleteRelation == 1;
        }
    }

    @Override
    public Boolean insert(Long studentId, Long klassId, String teamName, List<String> studentNum) {
        // 获取 courseId
        KlassStudent klassStudent = klassStudentMapper.selectOne(new KlassStudent().setKlassId(klassId).setStudentId(studentId));
        // 创建队伍
        Team team = new Team()
                .setLeaderId(studentId)
                .setKlassId(klassId)
                .setTeamName(teamName)
                .setCourseId(klassStudent.getCourseId())
                .setSerial((int) teamMapper.getMaxTeamSerial(klassStudent.getCourseId(), klassId) + 1)
                .setStatus(1)
                .setKlassSerial((byte) 1);
        // 插入队伍
        int lineTeam = teamMapper.insert(team);
        // 通过list<Long> studentNum 获取对应学生id
        List<Long> studentPrimaryKeyList = new ArrayList<>();
        for (int i = 0; i < studentNum.size(); i++) {
            studentPrimaryKeyList.add(studentMapper.selectOne(new Student().setStudentNum(studentNum.get(i))).getId());
        }
        // 插入 klass_student 表关系
        int lineKlassStudent = teamStudentMapper.insertList(team.getId(), studentPrimaryKeyList);
        return (lineTeam + lineKlassStudent) > 0;
    }

    @Override
    public Boolean deleteByStudentNum(Long teamId, String studentNum) {
        Student student = studentMapper.selectOne(new Student().setStudentNum(studentNum));
        int line = teamStudentMapper.delete(new TeamStudent().setTeamId(teamId).setStudentId(student.getId()));
        return line == 1;
    }

    @Override
    public Boolean updateByStudentNum(Long teamId, List<String> studentNum) {
        // 找学生所有id
        List<Long> studentIdList = new ArrayList<>();
        for (String studentAccount : studentNum) {
            Student student = studentMapper.selectOne(new Student().setStudentNum(studentAccount));
            studentIdList.add(student.getId());
        }
        // 获取 courseId 和 klassId
        int line = teamStudentMapper.insertList(teamId, studentIdList);
        return line == studentIdList.size();
    }

    @Override
    public Boolean update(Team team) {
        return teamMapper.updateByPrimaryKeySelective(team) > 0;
    }

    @Override
    public Boolean delete(Long teamId, Long studentId) {
        int deleteTeam = teamMapper.deleteByPrimaryKey(teamId);
        int deleteRelation = teamStudentMapper.delete(new TeamStudent().setTeamId(teamId));
        return (deleteTeam + deleteRelation) > 0;
    }

    @Override
    public Long selectByKlassIdAndStudentId(Long klassId, Long studentId) {
        return klassTeamMapper.selectByKlassIdAndStudentId(klassId, studentId).getTeamId();
    }
}
