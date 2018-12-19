package online.templab.flippedclass.dao.impl;

import online.templab.flippedclass.dao.TeamDao;
import online.templab.flippedclass.entity.Klass;
import online.templab.flippedclass.entity.KlassStudent;
import online.templab.flippedclass.entity.Student;
import online.templab.flippedclass.entity.Team;
import online.templab.flippedclass.mapper.KlassStudentMapper;
import online.templab.flippedclass.mapper.StudentMapper;
import online.templab.flippedclass.mapper.TeamMapper;
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
        KlassStudent klassStudent = klassStudentMapper.selectByCourseIdAndStudentId(courseId, studentId);
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
        team.setLeader(leader);
        team.setStudents(member);
        return team;
    }

    @Override
    public Boolean deleteMemberById(Long teamId, Long studentId) {
        Team team = teamMapper.selectByPrimaryKey(teamId);
        // 如果退组的是队长，整个队伍删除
        if (team.getLeaderId().equals(studentId)) {
            int deleteTeam = teamMapper.delete(team);
            int deleteRelation = klassStudentMapper.deleteByTeamId(teamId);
            return (deleteTeam * deleteRelation) > 0;
        } else { // 如果是组员 删除组员就好了
            int deleteRelation = klassStudentMapper.deleteByTeamIdAndStudentId(teamId, studentId);
            return deleteRelation > 0;
        }
    }

    @Override
    public Boolean insert(Long studentId, Long klassId, String teamName, List<Long> studentNum) {
        // 获取 courseId
        KlassStudent klassStudent = klassStudentMapper.selectByStudentIdAndKlassId(klassId, studentId);
        // 创建队伍
        Team team = new Team()
                .setLeaderId(studentId)
                .setKlassId(klassId)
                .setTeamName(teamName)
                .setCourseId(klassStudent.getCourseId())
                .setSerial(33)
                .setStatus(1);
        // 插入队伍
        int lineTeam = teamMapper.insert(team);
        team = teamMapper.selectOne(team);
        // 插入 klass_student 表关系
        int lineKlassStudent = klassStudentMapper.insertList(klassStudent.getCourseId(), klassId, team.getId(), studentNum);
        return lineTeam * lineKlassStudent > 0;
    }

    @Override
    public Boolean deleteByAccount(Long teamId, String account) {
        Student student = studentMapper.selectByAccount(account);
        int line = klassStudentMapper.deleteByTeamIdAndStudentId(teamId, student.getId());
        return line == 1;
    }

    @Override
    public Boolean updateByAccount(Long teamId, Long studentId, List<String> account) {
        // 找学生所有id
        List<Long> studentIdList = new ArrayList<>();
        for (String studentAccount : account) {
            Student student = studentMapper.selectByAccount(studentAccount);
            studentIdList.add(student.getId());
        }
        // 获取 courseId 和 klassId
        KlassStudent klassStudent = klassStudentMapper.selectByTeamIdAndStudentId(teamId, studentId);
        int line = klassStudentMapper.insertList(klassStudent.getCourseId(), klassStudent.getKlassId(), teamId, studentIdList);
        return line > 0;
    }

    @Override
    public Boolean delete(Long teamId, Long studentId) {
        int deleteTeam = teamMapper.deleteByPrimaryKey(teamId);
        int deleteKlassStudent = klassStudentMapper.deleteByTeamId(teamId);
        return (deleteTeam * deleteKlassStudent) > 0;
    }
}
