package online.templab.flippedclass.service;

import online.templab.flippedclass.entity.KlassSeminar;
import online.templab.flippedclass.entity.Seminar;

import java.util.List;

/**
 * Seminar 业务 接口类
 *
 * @author wk
 */
public interface SeminarService {

    /**
     * 插入一个 Seminar。
     * 若 roundId == null ,则默认新建一个 round
     *
     * @param seminar
     * @return 成功则返回 true ,失败则返回 false
     */
    Boolean insert(Seminar seminar);

    /**
     * 根据 id 更新一个 Seminar
     *
     * @param seminar
     * @return 成功则返回 true ,失败则返回 false
     */
    Boolean update(Seminar seminar);

    /**
     * 删除某个 id 对应的 Seminar
     *
     * @param id
     * @return
     */
    Boolean delete(Long id);

    /**
     * 根据 courseId 查找该课程的所有讨论课中最大的序号(seminarSerial)
     *
     * @param courseId
     * @return
     */
    Integer getMaxSeminarSerialByCourseId(Long courseId);

    /**
     * 根据 id 获得一个 Student
     *
     * @param id
     * @return
     */
    Seminar get(Long id);

}
