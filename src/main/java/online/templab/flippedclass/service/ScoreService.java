package online.templab.flippedclass.service;

import java.util.List;
import java.util.Map;

/**
 * 分数计算 业务 接口类
 * @author jh
 */
public interface ScoreService {

    /**
     * 更新round下的总分
     * @param roundId
     * @return
     */
    Boolean updateRoundScore(Long roundId,Long klassId);

    /**
     *通过roundId和KlassId得到round下的队名和相应的分数
     * TODO：返回值待确定
     * @param roundId
     * @return
     */
    List<Map<String,Object>> getRoundScore(Long roundId, Long klassId);

    /**
     * 通过学生id和courseId得到所有的分数
     * @param studentId
     * @param courseId
     * @return
     * map<"roundName",String>
     * map<"seminars",List<SeminarScore>>
     */
    List<Map<String,Object>> getByStudentIdCourseId(Long studentId,Long courseId);
}
