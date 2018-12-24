package online.templab.flippedclass.dao.impl;

import online.templab.flippedclass.dao.TeamDao;
import online.templab.flippedclass.entity.Klass;
import online.templab.flippedclass.entity.KlassStudent;
import online.templab.flippedclass.entity.Student;
import online.templab.flippedclass.entity.Team;
import online.templab.flippedclass.mapper.KlassMapper;
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

    @Autowired
    KlassMapper klassMapper;

    @Override
    public List<Team> selectByCourseId(Long courseId) {
        return teamMapper.select(new Team().setCourseId(courseId));
    }

    // do now
    @Override
    public List<Student> selectUnTeamedStudentByCourseId(Long courseId) {
        return studentMapper.selectUnTeamedStudentByCourseId(courseId);
    }

    @Override
    public Team selectTeam(Long courseId, Long studentId) {
//        // 获取队伍id
//        KlassStudent klassStudent = klassStudentMapper.selectOne(new KlassStudent().setCourseId(courseId).setStudentId(studentId));
//        if (klassStudent.getTeamId() == 0) {
//            return new Team().setId((long) 0);
//        }
//        // 获取队伍成员
//        List<Student> member = studentMapper.selectTeamMemberByTeamId(courseId, klassStudent.getTeamId());
//        // 通过 teamId 获取 team
//        Team team = teamMapper.selectByPrimaryKey(klassStudent.getTeamId());
//        // 队长
//        Student leader = new Student();
//        for (int i = 0; i < member.size(); i++) {
//            if (member.get(i).getId().equals(team.getLeaderId())) {
//                leader = member.get(i);
//                member.remove(i);
//                break;
//            }
//        }
//        // 组装 team
//        team.setLeader(leader)
//                .setStudents(member)
//                .setKlass(klassMapper.selectByPrimaryKey(klassStudent.getKlassId()));
//        return team;
        // TODO
        return null;
    }

    @Override
    public Boolean deleteMemberById(Long teamId, Long studentId) {
//        Team team = teamMapper.selectByPrimaryKey(teamId);
//        KlassStudent klassStudent = klassStudentMapper.selectOne(new KlassStudent().setStudentId(studentId).setTeamId(teamId));
//        if (klassStudent == null) {
//            return false;
//        }
//        //如果是空则删掉组
//        if (team == null) {
//            int deleteRelation = klassStudentMapper.updateByPrimaryKey(new KlassStudent()
//                    .setTeamId(null)
//                    .setStudentId(studentId)
//                    .setKlassId(klassStudent.getKlassId())
//                    .setCourseId(klassStudent.getCourseId())
//            );
//            return deleteRelation > 0;
//        }
//        // 如果退组的是队长，整个队伍删除
//        if (team.getLeaderId().equals(studentId)) {
//            int deleteTeam = teamMapper.delete(team);
//            //删除组员的teamId信息
//            List<KlassStudent> klassStudents = klassStudentMapper.select(new KlassStudent().setTeamId(teamId));
//            for (KlassStudent klassStudentAll : klassStudents) {
//                klassStudentMapper.updateByPrimaryKey(klassStudentAll.setTeamId(null));
//            }
//            return deleteTeam > 0;
//        } else { // 如果是组员 删除组员就好了
//            int deleteRelation = klassStudentMapper.updateByPrimaryKey(new KlassStudent()
//                    .setTeamId(null)
//                    .setStudentId(studentId)
//                    .setKlassId(klassStudent.getKlassId())
//                    .setCourseId(klassStudent.getCourseId()));
//            return deleteRelation > 0;
//        }
        // TODO
        return null;
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
                .setStatus(1);
        // 插入队伍
        int lineTeam = teamMapper.insert(team);
        team = teamMapper.selectOne(team);
        // 通过list<Long> studentNum 获取对应学生id
        List<Long> studentPrimaryKeyList = new ArrayList<>();
        for (int i = 0; i < studentNum.size(); i++) {
            studentPrimaryKeyList.add(studentMapper.selectOne(new Student().setStudentNum(studentNum.get(i))).getId());
        }
        // 插入 klass_student 表关系
        int lineKlassStudent = klassStudentMapper.insertList(klassStudent.getCourseId(), klassId, team.getId(), studentPrimaryKeyList);
        return lineTeam * lineKlassStudent > 0;
    }

    @Override
    public Boolean deleteByStudentNum(Long teamId, String studentNum) {
//        Student student = studentMapper.selectOne(new Student().setStudentNum(studentNum));
//        int line = klassStudentMapper.delete(new KlassStudent().setTeamId(teamId).setStudentId(student.getId()));
//        return line == 1;
        // TODO
        return null;
    }

    @Override
    public Boolean updateByStudentNum(Long teamId, Long studentId, List<String> studentNum) {
//        // 找学生所有id
//        List<Long> studentIdList = new ArrayList<>();
//        for (String studentAccount : studentNum) {
//            Student student = studentMapper.selectOne(new Student().setStudentNum(studentAccount));
//            studentIdList.add(student.getId());
//        }
//        // 获取 courseId 和 klassId
//        KlassStudent klassStudent = klassStudentMapper.selectOne(new KlassStudent().setTeamId(teamId).setStudentId(studentId));
//        int line = klassStudentMapper.insertList(klassStudent.getCourseId(), klassStudent.getKlassId(), teamId, studentIdList);
//        return line > 0;
        // TODO
        return null;
    }

    @Override
    public Boolean delete(Long teamId, Long studentId) {
//        int deleteTeam = teamMapper.deleteByPrimaryKey(teamId);
//        int deleteKlassStudent = klassStudentMapper.delete(new KlassStudent().setTeamId(teamId));
//        return (deleteTeam * deleteKlassStudent) > 0;
        // TODO
        return null;
    }
}
