package online.templab.flippedclass.service;

import online.templab.flippedclass.entity.Attendance;
import online.templab.flippedclass.entity.KlassSeminar;
import online.templab.flippedclass.entity.Seminar;

import java.util.List;

/**
 * Seminar 业务 接口类
 *
 * @author wk
 * @author jh
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
     * 根据 id 获得一个 Seminar
     *
     * @param id
     * @return
     */
    Seminar get(Long id);


    /**
     * 插入一个 klassSeminar
     *
     * @return
     */
    Boolean insertKlassSeminar(KlassSeminar klassSeminar);

    /**
     * 根据 klassId 和 seminarId 获得一个 KlassSeminar
     *
     * @param klassId
     * @param seminarId
     * @return
     */
    KlassSeminar getKlassSeminar(Long klassId, Long seminarId);

    /**
     * 删除klassSeminar
     * 一般是在取消共享时删除从课程的klassSeminar
     *
     * @param id
     * @return
     */
    Boolean deleteKlassSeminar(Long id);

    /**
     * 获得某个 klassSeminar 下的注册展示
     * Attention 需要包括 team ,team 需要包括 teamName
     *
     * @param klassSeminarId
     * @return
     */
    List<Attendance> getEnrollListByKlassSeminarId(Long klassSeminarId);

    /**
     * 报名（前端直接传attendance）
     * @param attendance
     * @return
     */
    Boolean enRoll(Attendance attendance);

    /**
     * 取消报名
     * @param klassSeminarId
     * @param studentId
     * @return
     */
    Boolean deleteEnroll(Long klassSeminarId,Long studentId);
}
