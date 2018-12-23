package online.templab.flippedclass.dao;

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
     *通过roundId和KlassId得到round下的队名和相应的分数
     * @param roundId
     * @return
     */
    List<Map<String,Object>> listRoundScore(Long roundId,Long klassId);

    /**
     * 通过学生id和courseId得到所有的分数
     * @param studentId
     * @param courseId
     * @return
     * map<"roundName",String>
     * map<"seminars",List<SeminarScore>>
     */
    List<Map<String,Object>> listByStudentId(Long studentId,Long courseId);
}
