package online.templab.flippedclass.service;

import online.templab.flippedclass.entity.KlassRound;
import online.templab.flippedclass.entity.Round;

import java.util.List;

/**
 * Round 业务 接口类
 *
 * @author chenr
 */
public interface RoundService {

    /**
     * 在讨论课修改页面选择新的round时，创建round：
     * 插入一个 Round并插入主从课程的 klass_seminar 表
     *
     * @param round
     * @return 成功则返回 true ,失败则返回 false
     */
    Boolean insert(Round round);

    /**
     * 根据 roundId 删除 round
     *
     * @param roundId
     * @return
     */
    Boolean delete(Long roundId);

    /**
     * 老师修改轮次设置
     * 轮次修改页面，修改 round下成绩设置和各班报名数
     * 根据 id 更新一个 Round, 并更新其 klass_round 表
     *
     * @param round
     * @return 成功则返回 true ,失败则返回 false
     */
    Boolean update(Round round);

    /**
     * 更新 KlassRound 的 enrollLimit
     *
     * @param klassRound
     * @return
     */
    Boolean updateKlassRound(KlassRound klassRound);

    /**
     * 老师讨论课列表页
     * 根据 courseId 查找该 course 的所有 round
     * 注入 round下所有 seminar
     *
     * @param courseId
     * @return
     */
    List<Round> listByCourseId(Long courseId);

    /**
     * 学生讨论课页表页
     * 根据 courseId 和 klassId 查找该 course 的所有 round
     * 注入 round 下所有seminar和其班级的 klassRounds
     * klassRounds 注入班级
     *
     * @param courseId
     * @return
     */
    List<Round> listByCourseIdKlassId(Long courseId, Long klassId);

    /**
     * 老师获取该轮轮次设置
     * 根据 rounfId 和 courseId 获得一个 round
     * 注入round下所有 seminar和 klassRounds
     * klassRounds 注入班级
     *
     * @param roundId
     * @param courseId
     * @return
     */
    Round get(Long roundId, Long courseId);

    /**
     * 通过 klassId 和 roundId 获得 klassRound
     *
     * @param klassId
     * @param roundId
     * @return
     */
    KlassRound getKlassRound(Long klassId, Long roundId);
}
