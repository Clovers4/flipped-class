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
     * 根据 courseId 查找该 course 的所有 round
     *
     * @param courseId
     * @return
     */
    List<Round> listByCourseId(Long courseId);

}
