package online.templab.flippedclass.dao.impl;

import online.templab.flippedclass.dao.TeamDao;
import online.templab.flippedclass.entity.*;
import online.templab.flippedclass.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
    KlassMapper klassMapper;

    @Autowired
    TeamStudentMapper teamStudentMapper;

    @Autowired
    KlassTeamMapper klassTeamMapper;

    @Override
    public List<Team> selectByCourseId(Long courseId) {
        return teamMapper.select(new Team().setCourseId(courseId));
    }

    @Override
    public List<Student> selectUnTeamedStudentByCourseId(Long courseId) {
        return studentMapper.selectUnTeamedStudentByCourseId(courseId);
    }

    @Override
    public Team selectTeam(Long courseId, Long studentId) {
        // 获取队伍id
        KlassStudent klassStudent = klassStudentMapper.selectOne(new KlassStudent().setCourseId(courseId).setStudentId(studentId));
        if (klassStudent.getTeamId() == 0) {
            return new Team().setId((long) 0);
        }
        // 获取队伍成员
        List<Student> member = studentMapper.selectTeamMemberByTeamId(courseId, klassStudent.getTeamId());
        // 通过 teamId 获取 team
        Team team = teamMapper.selectByPrimaryKey(klassStudent.getTeamId());
        // 队长
        Student leader = new Student();
        for (int i = 0; i < member.size(); i++) {
            if (member.get(i).getId().equals(team.getLeaderId())) {
                leader = member.get(i);
                member.remove(i);
                break;
            }
        }
        // 组装 team
        team.setLeader(leader)
                .setStudents(member)
                .setKlass(klassMapper.selectByPrimaryKey(klassStudent.getKlassId()));
        return team;
    }

    @Override
    public Boolean deleteMemberById(Long teamId, Long studentId) {
        Team team = teamMapper.selectByPrimaryKey(teamId);
        if(team == null){
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
                .setKlassSerial((byte)1);
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
    public Boolean delete(Long teamId, Long studentId) {
        int deleteTeam = teamMapper.deleteByPrimaryKey(teamId);
        int deleteRelation = teamStudentMapper.delete(new TeamStudent().setTeamId(teamId));
        return (deleteTeam+deleteRelation) > 0;
    }

    @Override
    public Long selectByKlassIdAndStudentId(Long klassId, Long studentId) {
        return klassTeamMapper.selectByKlassIdAndStudentId(klassId, studentId).getTeamId();
    }
}
