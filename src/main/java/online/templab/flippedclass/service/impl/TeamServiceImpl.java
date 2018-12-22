package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.dao.TeamDao;
import online.templab.flippedclass.entity.Student;
import online.templab.flippedclass.entity.Team;
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
    public List<Team> listByCourseId(Long courseId) {
        List<Team> teamList = teamDao.selectByCourseId(courseId);
        for (int i = 0; i < teamList.size(); i++) {
            Team tmp = get(courseId, teamList.get(i).getLeaderId());
            teamList.set(i, tmp);
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
    public Boolean addMember(Long teamId, Long studentId, List<String> studentNum) {
        return teamDao.updateByStudentNum(teamId, studentId, studentNum);
    }

    @Override
    public Boolean dissolve(Long teamId, Long studentId) {
        return teamDao.delete(teamId, studentId);
    }
}
