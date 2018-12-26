package online.templab.flippedclass.service;

import java.util.List;
import java.util.Map;

import online.templab.flippedclass.entity.SeminarScore;

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
     * 通过roundId和KlassId得到round下的队名和相应的分数
     * TODO：返回值待确定
     *
     * @param roundId
     * @param klassId
     * @return
     */
    List<Map<String, Object>> getRoundScore(Long roundId, Long klassId);

    /**
     * 通过学生id 和courseId 得到所有的分数
     *
     * @param studentId
     * @param courseId
     * @return map<"   roundName   "   ,   String>map<"seminars",List<SeminarScore>>
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
}
