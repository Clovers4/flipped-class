package online.templab.flippedclass.dao;

import online.templab.flippedclass.entity.*;

import java.util.List;

/**
 * Team dao层接口
 *
 * @author jh
 * @author fj
 */
public interface TeamDao {

    /**
     * 通过 teamId 获得 team
     * 并注入 teamStrategyList
     *
     * @param teamId
     * @return
     */
    Team selectTeamValid(Long teamId);

    /**
     * 通过 courseId 获得 course
     * 并注入 teamStrategyList
     *
     * @param courseId
     * @return
     */
    List<TeamStrategy> selectTeamValidByCourseId(Long courseId);

    /**
     * 根据 courseId 查找该 course 下的所有 team
     *
     * @param courseId
     * @return
     */
    List<Team> selectByCourseId(Long courseId);

    /**
     * 根据 courseId 获取该课程下所有未组队学生
     *
     * @param courseId
     * @return
     */
    List<Student> selectUnTeamedStudentByCourseId(Long courseId);

    /**
     * 根据 courseId 和 studentId 获取这名学生在这门课程下的队伍成员
     * <p>
     * 还没有组装courseStrategyList ！！！
     *
     * @param courseId
     * @param studentId
     * @return
     */
    Team selectTeam(Long courseId, Long studentId);

    /**
     * 学生退组（根据 teamId 和 studentId 删除一个队伍里的学生）
     * 如果是队长需要删除整个队伍 ，如果是组员只需要删除自身即可
     *
     * @param teamId
     * @param studentId
     * @return
     */
    Boolean deleteMemberById(Long teamId, Long studentId);

    /**
     * 队伍序号也是个问题 为什么不是string类型rw
     * <p>
     * 队长创建队伍
     *
     * @param team
     * @return teamId
     */
    Long insert(Team team);

    /**
     * 将 teamStrategyList 插入各自的表
     *
     * @param teamStrategyList
     * @return
     */
    Long insertTeamStratgyList(List<TeamStrategy> teamStrategyList);

    /**
     * 将 teamStrategyList 删除
     *
     * @param courseId
     * @return
     */
    Long deleteTeamStratgyList(Long courseId);

    /**
     * 根据 account 删除组员
     *
     * @param teamId
     * @param studentNum
     * @return
     */
    Boolean deleteByStudentNum(Long teamId, String studentNum);

    /**
     * 添加组员
     *
     * @param teamId
     * @param studentNum
     * @return
     */
    Boolean updateByStudentNum(Long teamId, List<String> studentNum);

    /**
     * 更新 team
     *
     * @param team
     * @return
     */
    Boolean update(Team team);

    /**
     * 队长解散队伍
     *
     * @param teamId
     * @param studentId
     * @return
     */
    Boolean delete(Long teamId, Long studentId);

    /**
     * 根据 klassId 和 studentId 获取一个 teamId
     *
     * @param klassId
     * @param studentId
     * @return
     */
    Long selectByKlassIdAndStudentId(Long klassId, Long studentId);

    /**
     * 根据 team id 获取一个team（未组装）
     *
     * @param id
     * @return
     */
    Team selectByPrimaryKey(Long id);

    /**
     * 根据 teamid 获取teamStudent对象 list
     *
     * @param teamId
     * @return
     */
    List<TeamStudent> selectTeamStudentByTeamId(Long teamId);

    /**
     * 发送队伍有效请求
     *
     * @param teamValidApplication
     * @return
     */
    Boolean insertTeamValidApplication(TeamValidApplication teamValidApplication);

    /**
     * 通过老师ID得到所有请求
     *
     * @param teacherId
     * @return
     */
    List<TeamValidApplication> selectTeamApplicationByTeacherId(Long teacherId);

    /**
     * 老师处理队伍有效请求
     *
     * @param teamValidApplicationId
     * @param teamId
     * @param accept
     * @return
     */
    Boolean updateTeamValidApplication(Long teamValidApplicationId, Long teamId, Boolean accept);
}
