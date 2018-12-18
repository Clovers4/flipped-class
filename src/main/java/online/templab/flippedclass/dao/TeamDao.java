package online.templab.flippedclass.dao;

import online.templab.flippedclass.entity.Team;

import java.util.List;

/**
 * Team dao层接口
 * @author jh
 */
public interface TeamDao {

    /**
     * 根据 courseId 查找该 course 下的所有 team
     *
     * @param courseId
     * @return
     */
    List<Team> selectByCourseId(Long courseId);
}
