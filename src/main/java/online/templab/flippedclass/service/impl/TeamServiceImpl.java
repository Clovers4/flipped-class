package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.dao.TeamDao;
import online.templab.flippedclass.entity.Student;
import online.templab.flippedclass.entity.Team;
import online.templab.flippedclass.entity.TeamStrategy;
import online.templab.flippedclass.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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

    @Override
    public int validOneTeamState(Long teamId) {
        Team team = teamDao.selectTeamValid(teamId);
        List<Student> studentList= team.getAllStudents();
        List<TeamStrategy> teamStrategyList = team.getTeamStrategyList();
        int valid = 1;

        for(int i = 0 ;  i < teamStrategyList.size() ; ++i){
            if(!teamStrategyList.get(i).isValid(studentList)){
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
        for(int i = 0; i < teamListInfoLimit.size();++i){
            validOneTeamState(teamListInfoLimit.get(i).getId());
        }
    }

    @Override
    public List<Team> listByCourseId(Long courseId) {
        List<Team> teamList = teamDao.selectByCourseId(courseId);
        for (int i = 0; i < teamList.size(); i++) {
            Team tmp = get(courseId, teamList.get(i).getLeaderId());
            if (tmp.getId() != 0) {
                teamList.set(i, tmp);
            }
        }
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
        return teamDao.insert(studentId, klassId, teamName, studentNum);
    }

    @Override
    public Boolean removeMember(Long teamId, String studentNum) {
        return teamDao.deleteByStudentNum(teamId, studentNum);
    }

    @Override
    public Boolean addMember(Long teamId,List<String> studentNum) {
        return teamDao.updateByStudentNum(teamId,studentNum);
    }

    @Override
    public Boolean dissolve(Long teamId, Long studentId) {
        return teamDao.delete(teamId, studentId);
    }

    @Override
    public Long getTeamByKlassIdAndStudentId(Long klassId, Long studentId) {
        return teamDao.selectByKlassIdAndStudentId(klassId,studentId);
    }
}
