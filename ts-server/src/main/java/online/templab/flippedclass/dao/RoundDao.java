package online.templab.flippedclass.dao;

import online.templab.flippedclass.entity.KlassRound;
import online.templab.flippedclass.entity.Round;

import java.util.List;

/**
 * Round Dao 接口类
 *
 * @author chenr
 */

public interface RoundDao {
    /**
     * 插入一个 Round 并插入主从课程的 klass_seminar 表
     *
     * @param round
     * @return
     */
    int insert(Round round);

    /**
     * 根据 roundId 删除 round
     *
     * @param roundId
     * @return
     */
    int delete(Long roundId);

    /**
     * 根据 id 更新一个 Round, 并更新其 klass_round 表
     *
     * @param round
     * @return
     */
    int updateByRoundIdSelective(Round round);

    /**
     * 更新 KlassRound 的 enrollLimit
     *
     * @param klassRound
     * @return
     */
    int updateKlassRoundByPrimaryKey(KlassRound klassRound);

    /**
     * 根据 courseId 查找该 course 的所有 round
     * 注入 round下所有 seminar
     *
     * @param courseId
     * @return
     */
    List<Round> selectByCourseId(Long courseId);

    /**
     * 根据 courseId和 klassId 查找该 course 的所有 round
     * 注入 round下所有 seminar和其班级的 klassRounds
     * klassRounds注入班级
     * @param courseId
     * @param klassId
     * @return
     */
    List<Round> selectByCourseIdKlassId(Long courseId, Long klassId);

    /**
     * 通过 courseId 获得其课程下 round 数目
     *
     * @param couseId
     * @return
     */
    int selectCount(Long couseId);

    /**
     * 根据 roundId 和 courseId 获得一个 round
     * 注入 round下所有 seminar 和 klassRounds
     * klassRounds 注入 seminar
     *
     * @param roundId
     * @param courseId
     * @return
     */
    Round getOne(Long roundId, Long courseId);

    /**
     * 通过 klassId 和 roundId 获得 klassRound
     *
     * @param klassId
     * @param roundId
     * @return
     */
    KlassRound getKlassRound(Long klassId, Long roundId);

    /**
     * 根据 klassSeminarId 获取 roundId
     *
     * @param klassSeminarId
     * @return
     */
    Long getRoundIdByKlassSeminarId(Long klassSeminarId);

}
