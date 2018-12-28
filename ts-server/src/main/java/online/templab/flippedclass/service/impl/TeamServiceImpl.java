package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.dao.CourseDao;
import online.templab.flippedclass.dao.TeamDao;
import online.templab.flippedclass.entity.*;
import online.templab.flippedclass.mapper.TeamValidApplicationMapper;
import online.templab.flippedclass.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private TeamValidApplicationMapper teamValidApplicationMapper;

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
    public List<TeamStrategy> listTeamStategy(Long courseId) {
        List<TeamStrategy> teamStrategyList = teamDao.selectTeamValidByCourseId(courseId);
        return teamStrategyList;
    }

    @Override
    public Team get(Long courseId, Long studentId) {
        return teamDao.selectTeam(courseId, studentId);
    }

    @Override
    public Boolean quitTeam(Long teamId, Long studentId) throws ParseException {
        int compare = 0;
        try {
            //设置日期格式
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // new Date()为获取当前系统时间
            String nowDate = df.format(new Date());
            Date date = df.parse(nowDate);
            // 获取组队截止日期
            Course course = courseDao.selectDateByTeamId(teamId);
            compare = date.compareTo(course.getTeamEndDate());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        // 如果当前时间过了截止时间，不允许退组
        if (compare == 1) {
            return false;
        }
        return teamDao.deleteMemberById(teamId, studentId);
    }

    @Override
    public Boolean create(Team team){
        // 创建一个队伍 成功返回teamId
        Long teamId = teamDao.insert(team);
        // 合法性判断
        int statue = validOneTeamState(teamId);
        // 如果状态合法
        if (statue == 1) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean removeMember(Long teamId, String studentNum) throws ParseException {
        int compare = 0;
        try {
            //设置日期格式
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // new Date()为获取当前系统时间
            String nowDate = df.format(new Date());
            Date date = df.parse(nowDate);
            // 获取组队截止日期
            Course course = courseDao.selectDateByTeamId(teamId);
            compare = date.compareTo(course.getTeamEndDate());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        // 如果当前时间过了截止时间，允许删除组员但是队伍状态为不合法
        if (compare == 1) {
            teamDao.deleteByStudentNum(teamId, studentNum);
            teamDao.update(new Team().setId(teamId).setStatus(0));
            return false;
        }
        // 如果没有超过组队截止时间
        // 判断队伍状态
        int statueForm = validOneTeamState(teamId);
        // 如果小组当前在审核中 不可以删除组员
        if (statueForm == 2) {
            return false;
        }
        // 删除组员
        Boolean deleteSuccess = teamDao.deleteByStudentNum(teamId, studentNum);
        // 如果删除组员成功
        if (deleteSuccess == true) {
            // 判断删除组员后队伍状态
            int statueLater = validOneTeamState(teamId);
            // 如果增加组员后组队合法
            if (statueLater == 1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean addMember(Long teamId, List<String> studentNum) throws ParseException {
        int compare = 0;
        try {
            //设置日期格式
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // new Date()为获取当前系统时间
            String nowDate = df.format(new Date());
            Date date = df.parse(nowDate);
            // 获取组队截止日期
            Course course = courseDao.selectDateByTeamId(teamId);
            // 比较
            compare = date.compareTo(course.getTeamEndDate());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        // 如果超过截止时间
        if (compare == 1) {
            teamDao.updateByStudentNum(teamId, studentNum);
            teamDao.update(new Team().setId(teamId).setStatus(0));
            return false;
        }
        // 如果没有超过截止时间
        // 判断队伍状态
        int statueForm = validOneTeamState(teamId);
        // 如果当前小组正在审核 不可以增加组员
        if (statueForm == 2) {
            return false;
        }
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
        return false;
    }

    @Override
    public Boolean dissolve(Long teamId, Long studentId) throws ParseException {
        int compare = 0;
        try {
            //设置日期格式
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // new Date()为获取当前系统时间
            String nowDate = df.format(new Date());
            Date date = df.parse(nowDate);
            // 获取组队截止日期
            Course course = courseDao.selectDateByTeamId(teamId);
            compare = date.compareTo(course.getTeamEndDate());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        // 如果当前时间过了截止时间，不允许解散小组
        if (compare == 1) {
            return false;
        }
        return teamDao.delete(teamId, studentId);
    }

    @Override
    public Long getTeamByKlassIdAndStudentId(Long klassId, Long studentId) {
        return teamDao.selectByKlassIdAndStudentId(klassId, studentId);
    }

    @Override
    public Team getByPrimaryKey(Long id) {
        // 根据id 获取 一个 team（未组装）
        Team team = teamDao.selectByPrimaryKey(id);
        // 如果组长非空 ，根据组长id 和 courseId 去获取一个组装好的team
        if (team.getLeaderId() != null || team.getLeaderId() != 0) {
            return get(team.getCourseId(), team.getLeaderId());
        } else { // 如果组长是空的 根据组员id 和 courseId 去获取一个组装team
            List<TeamStudent> teamStudentList = teamDao.selectTeamStudentByTeamId(id);
            return get(team.getCourseId(), teamStudentList.get(0).getStudentId());
        }
    }

    @Override
    public Boolean insertTeamStratgyList(List<TeamStrategy> teamStrategyList) {
        return teamDao.insertTeamStratgyList(teamStrategyList) == 1;
    }

    @Override
    public Boolean sendTeamValidApplication(TeamValidApplication teamValidApplication) {
        return teamDao.insertTeamValidApplication(teamValidApplication);
    }

    @Override
    public List<TeamValidApplication> getTeamApplicationByTeacherId(Long teacherId) {
        return teamDao.selectTeamApplicationByTeacherId(teacherId);
    }

    @Override
    public Boolean processTeamValidApplication(Long teamValidApplicationId, Long teamId, Boolean accept) {
        return teamDao.updateTeamValidApplication(teamValidApplicationId, teamId, accept);
    }
}
