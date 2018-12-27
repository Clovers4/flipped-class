package online.templab.flippedclass.service;

import java.util.List;
import java.util.Map;

import online.templab.flippedclass.entity.Round;
import online.templab.flippedclass.entity.RoundScore;
import online.templab.flippedclass.entity.SeminarScore;
import online.templab.flippedclass.entity.Team;

/**
 * 分数计算 业务 接口类
 *
 * @author jh
 * @author fj
 */
public interface ScoreService {

    /**
     * 更新round下的总分
     *
     * @param roundId
     * @return
     */
    Boolean updateRoundScore(Long roundId, Long klassId);


    /**
     * 通过rounds和teams得到round下的队名和相应的分数
     * @param rounds
     * @param teams
     * @return
     */
    List<Map<String,Object>> getRoundScores(List<Round> rounds, List<Team> teams);

    /**
     * 通过学生id 和courseId 得到所有的分数
     *
     * @param studentId
     * @param courseId
     * @return map<"teamId",SeminarScore>
     */
    List<Map<String, Object>> getByStudentIdCourseId(Long studentId, Long courseId);

    /**
     * 给一个队伍的讨论课打分
     * <p>
     * seminarScore 必须携带 KlassSeminarId 、teamId
     * 三个分数项至少有一个即可
     *
     * @param seminarScore
     * @return
     */
    Boolean markerScore(SeminarScore seminarScore);

    /**
     * 更新一个队伍的讨论课打分
     * seminarScore 必须携带 teamId 三个分数项至少有一个即可
     *
     * @param seminarScore
     * @param seminarId
     * @param klassId
     * @return
     */

    Boolean update(SeminarScore seminarScore, Long seminarId, Long klassId);

    /**
     * 得到该组该轮的成绩
     * @param teamId
     * @param roundId
     * @return
     */
    RoundScore getScoreOfRound(Long teamId,Long roundId);

    /**
     * 根据 klassSeminerId 和 teamId 获取 SeminarScore
     *
     * @param klassSeminerId
     * @param teamId
     * @return
     */
    SeminarScore getSeminarScore(Long klassSeminerId, Long teamId);
}
