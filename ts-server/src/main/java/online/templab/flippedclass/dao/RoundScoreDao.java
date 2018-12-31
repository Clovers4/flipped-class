package online.templab.flippedclass.dao;

import online.templab.flippedclass.entity.Round;
import online.templab.flippedclass.entity.RoundScore;
import online.templab.flippedclass.entity.Team;

import java.util.List;
import java.util.Map;

/**
 * @author jh
 */
public interface RoundScoreDao {

    /**
     * 通过roundId和klassId更新round分数
     * @param roundId
     * @param klassId
     * @return
     */
    Boolean updateByRoundIdKlassId(Long roundId,Long klassId);

    /**
     * 通过rounds和teams得到round下的队名和相应的分数
     * @param rounds
     * @param teams
     * @return
     */
    List<Map<String,Object>> listRoundScores(List<Round> rounds, List<Team> teams);

    /**
     * 通过学生id和courseId得到所有的分数
     * @param studentId
     * @param courseId
     * @return
     * map<"roundName",String>
     * map<"seminars",List<SeminarScore>>
     */
    List<Map<String,Object>> listByStudentId(Long studentId,Long courseId);

    /**
     * 通过teamid和roundid得到该组该轮分数
     *
     * @param teamId
     * @param roundId
     * @return
     */
    RoundScore selectScoreOfRound(Long teamId,Long roundId);
}
