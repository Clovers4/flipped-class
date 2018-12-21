package online.templab.flippedclass.dao;

import online.templab.flippedclass.entity.Round;

import java.util.List;

/**
 * Round Dao 接口类
 *
 * @author chenr
 */

public interface RoundDao {
    /**
     * 插入一个 Round
     *
     * @param round
     * @return
     */
    int insert(Round round);

    /**
     * 根据 id 更新一个 Round, 并更新其讨论课下的班级klass_round表
     *
     * @param round
     * @return
     */
    int updateByRoundIdSelective(Round round);

    /**
     * 根据 courseId 查找该 course 的所有 round, 并注入round下所有seminar
     *
     * @param courseId
     * @return
     */
    List<Round> selectByCourseId(Long courseId);

    /**
     * 根据 courseId和klassId 查找该 course 的所有 round, 并注入round下所有seminar和其班级的klassRounds
     * klassRounds注入班级
     * @param courseId
     * @param klassId
     * @return
     */
    List<Round> selectByCourseIdKlassId(Long courseId, Long klassId);

    /**
     * 根据 id 获得一个 round，并注入round下所有seminar和klassRounds,klassRounds注入seminar
     *
     * @param id
     * @return
     */
    Round getOne(Long id);
}
