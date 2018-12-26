package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.dao.CourseDao;
import online.templab.flippedclass.dao.TeamDao;
import online.templab.flippedclass.entity.Student;
import online.templab.flippedclass.entity.Team;
import online.templab.flippedclass.entity.TeamStrategy;
import online.templab.flippedclass.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Team 业务 实现类
 *
 * @author jh
 * @author fj
 */
@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private CourseDao courseDao;

    @Override
    public int validOneTeamState(Long teamId) {
        Team team = teamDao.selectTeamValid(teamId);
        List<Student> studentList = team.getAllStudents();
        List<TeamStrategy> teamStrategyList = team.getTeamStrategyList();
        int valid = 1;

        for (int i = 0; i < teamStrategyList.size(); ++i) {
            if (!teamStrategyList.get(i).isValid(studentList)) {
                valid = 0;
                break;
            }
        }
        team.setStatus(valid);
        teamDao.update(team);
        return valid;
    }

    @Override
    public void validAllTeamByCourseId(Long courseId) {
        List<Team> teamListInfoLimit = teamDao.selectByCourseId(courseId);
        for (int i = 0; i < teamListInfoLimit.size(); ++i) {
            validOneTeamState(teamListInfoLimit.get(i).getId());
        }
    }

    @Override
    public List<Team> listByCourseId(Long courseId) {
        List<Team> teamList = teamDao.selectByCourseId(courseId);
//        for (int i = 0; i < teamList.size(); i++) {
//            Team tmp = get(courseId, teamList.get(i).getLeaderId());
//            if (tmp.getId() != 0) {
//                teamList.set(i, tmp);
//            }
//        }
        return teamList;
    }

    @Override
    public List<Student> listUnTeamedStudentByCourseId(Long courseId) {
        return teamDao.selectUnTeamedStudentByCourseId(courseId);
    }

    @Override
    public Team get(Long courseId, Long studentId) {
        return teamDao.selectTeam(courseId, studentId);
    }

    @Override
    public Boolean quitTeam(Long teamId, Long studentId) {
        return teamDao.deleteMemberById(teamId, studentId);
    }

    @Override
    public Boolean create(Long studentId, Long klassId, String teamName, List<String> studentNum) {
        // 创建一个队伍 成功返回teamId
        Long teamId = teamDao.insert(studentId, klassId, teamName, studentNum);
        // 合法性判断
        int statue = validOneTeamState(teamId);
        // 如果状态合法
        if (statue == 1) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean removeMember(Long teamId, String studentNum) {
        // 判断队伍状态
        int statueForm = validOneTeamState(teamId);
        // 如果此时队伍合法，才能进行删除组员
        if(statueForm == 1){
            // 删除组员
            Boolean deleteSuccess = teamDao.deleteByStudentNum(teamId, studentNum);
            // 如果删除组员成功
            if(deleteSuccess == true){
                // 判断删除组员后队伍状态
                int statueLater = validOneTeamState(teamId);
                // 如果增加组员后组队合法
                if (statueLater == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Boolean addMember(Long teamId, List<String> studentNum) {
        // 判断队伍状态
        int statueForm = validOneTeamState(teamId);
        // 如果此时队伍合法，才能进行增加组员
        if(statueForm == 1){
            // 增加组员
            Boolean addSuccess = teamDao.updateByStudentNum(teamId, studentNum);
            // 增加组员成功了
            if (addSuccess == true) {
                // 判断增加组员后队伍状态
                int statueLater = validOneTeamState(teamId);
                // 如果增加组员后组队合法
                if (statueLater == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Boolean dissolve(Long teamId, Long studentId) {
        return teamDao.delete(teamId, studentId);
    }

    @Override
    public Long getTeamByKlassIdAndStudentId(Long klassId, Long studentId) {
        return teamDao.selectByKlassIdAndStudentId(klassId, studentId);
    }
}
