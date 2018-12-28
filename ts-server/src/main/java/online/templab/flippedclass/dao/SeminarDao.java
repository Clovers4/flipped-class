package online.templab.flippedclass.dao;

import online.templab.flippedclass.entity.Round;
import online.templab.flippedclass.entity.Seminar;
import online.templab.flippedclass.entity.Student;
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

    /**
     * 插入一个问题
     *
     * @param presentationTeamId
     * @param questionTeamId
     * @param studentId
     * @return
     */
    Boolean insertQuestion(Long presentationTeamId, Long questionTeamId, Long studentId);

    /**
     * 根据 presentationTeamId 获取该组对应attendance下所有问题并抽一个
     *
     * @param presentationTeamId 正在展示组id
     * @return
     */
    Student selectOneQuestion(Long presentationTeamId);
}
