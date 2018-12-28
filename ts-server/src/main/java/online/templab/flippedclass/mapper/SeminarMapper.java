package online.templab.flippedclass.mapper;

import online.templab.flippedclass.entity.Seminar;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author wk
 */
@Component
public interface SeminarMapper extends Mapper<Seminar> {

    /**
     * 根据 courseId 查找该课程的所有讨论课中最大的序号(seminarSerial)
     *
     * @param courseId
     * @return
     */
    Integer selectMaxSeminarSerialByCourseId(Long courseId);

    /**
     * 根据 klassSeminarId 获取 round id
     *
     * @param klassSeminarId
     * @return
     */
    Seminar selectRoundIdByKlassSeminarId(Long klassSeminarId);
}