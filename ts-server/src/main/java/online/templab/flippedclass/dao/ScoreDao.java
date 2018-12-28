package online.templab.flippedclass.dao;

import online.templab.flippedclass.entity.Course;
import online.templab.flippedclass.entity.Round;
import online.templab.flippedclass.entity.SeminarScore;

/**
 * @author fj
 */
public interface ScoreDao {

    /**
     * 给一个队伍的讨论课打分
     * seminarScore 必须携带 klassSeminarId、teamId 三个分数项至少有一个即可
     *
     * @param seminarScore
     * @return
     */
    Boolean insert(SeminarScore seminarScore);

    /**
     * 更新一个队伍的讨论课打分
     * seminarScore 必须携带 teamId 三个分数项至少有一个即可
     *
     * @param seminarScore
     * @param seminarId
     * @param klassId
     * @return
     */
    Boolean update(SeminarScore seminarScore, Long seminarId,Long klassId);

    /**
     * 根据 klassSeminerId 和 teamId 获取 SeminarScore
     *
     * @param klassSeminarId
     * @param teamId
     * @return
     */
    SeminarScore selectSeminarScore(Long klassSeminarId,Long teamId);
}
