package online.templab.flippedclass.service;

import online.templab.flippedclass.entity.Round;

import java.util.List;

/**
 * Round 业务 接口类
 *
 * @author wk
 */
public interface RoundService {

    /**
     * 插入一个 Round。
     *
     * @param round
     * @return 成功则返回 true ,失败则返回 false
     */
    Boolean insert(Round round);


    /**
     * 根据 courseId 查找该 course 的所有 round
     *
     * @param courseId
     * @return
     */
    List<Round> listByCourseId(Long courseId);

}
