package online.templab.flippedclass.service;

import online.templab.flippedclass.entity.Team;

import java.util.List;

/**
 * Team 业务 接口类
 *
 * @author wk
 */
public interface TeamService {

    /**
     * 根据 courseId 查找该 course 下的所有 team
     *
     * @param courseId
     * @return
     */
    List<Team> listByCourseId(Long courseId);
}
