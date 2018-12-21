package online.templab.flippedclass.dao;

import online.templab.flippedclass.entity.Round;
import online.templab.flippedclass.entity.Seminar;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author fj
 */
public interface SeminarDao {

    /**
     * 插入一个 Seminar。
     * 若 roundId == null ,则默认新建一个 round
     *
     * @param seminar
     * @return
     */
    Boolean insert(Seminar seminar);

    /**
     * 通过 id  更新一个 seminar
     *
     * @param seminar
     * @return
     */
    Boolean updateByPrimaryKey(Seminar seminar);

    /**
     * 根据 id 删除一个 Seminar
     *
     * @param id
     * @return
     */
    Boolean deleteByPrimaryKey(Long id);

    /**
     * 根据 id 获取一个 seminar
     *
     * @param id
     * @return
     */
    Seminar selectByPrimaryKey(Long id);

    /**
     * 根据 courseId 查询出该课程下最大讨论课序列号
     *
     * @param courseId
     * @return
     */
    Integer selectMaxSeminarSerialByCourseId(Long courseId);
}
