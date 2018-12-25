package online.templab.flippedclass.service;

import online.templab.flippedclass.entity.Student;
import online.templab.flippedclass.entity.Team;

import java.util.List;

/**
 * Team 业务 接口类
 *
 * @author wk
 * @author fj
 */
public interface TeamService {

    /**
     * 更新 Id 为 teamId 的队伍状态
     *
     * @param teamId
     * @return 此 team 当前组队状态
     */
    int validOneTeamState(Long teamId);

    /**
     * 更新 Id 为 courseId 的所有队伍状态
     *
     * @param courseId
     * @return
     */
    void validAllTeamByCourseId(Long courseId);

    /**
     * 根据 courseId 查找该 course 下的所有 team
     *
     * @param courseId
     * @return
     */
    List<Team> listByCourseId(Long courseId);

    /**
     * 通过 courseId 获取该课程下所有未组队学生
     *
     * @param courseId
     * @return
     */
    List<Student> listUnTeamedStudentByCourseId(Long courseId);

    /**
     * 根据 courseId 和 studentId 获取这名学生在这门课程下的队伍成员
     *
     * @param courseId
     * @param studentId
     * @return
     */
    Team get(Long courseId, Long studentId);

    /**
     * 学生退组
     *
     * @param teamId
     * @param studentId
     * @return
     */
    Boolean quitTeam(Long teamId, Long studentId);

    /**
     * 队长创建队伍（合法性判断缺失）
     *
     * @param studentId  队长id
     * @param klassId    班级id
     * @param teamName   队伍名称
     * @param studentNum 成员学号/账号 list
     * @return
     */
    Boolean create(Long studentId, Long klassId, String teamName, List<String> studentNum);

    /**
     * 根据 studentNum 删除组员（合法性判断缺失）
     *
     * @param teamId
     * @param studentNum
     * @return
     */
    Boolean removeMember(Long teamId, String studentNum);

    /**
     * 添加组员（合法性判断缺失）
     *
     * @param teamId     队伍 id
     * @param studentNum 学生 studentNum
     * @return
     */
    Boolean addMember(Long teamId,List<String> studentNum);

    /**
     * 组长解散小组
     *
     * @param teamId
     * @param studentId
     * @return
     */
    Boolean dissolve(Long teamId, Long studentId);

    /**
     * 根据 klassId 和 studentId 获取一个teamId
     *
     * @param klassId
     * @param studentId
     * @return
     */
    Long getTeamByKlassIdAndStudentId(Long klassId,Long studentId);
}
