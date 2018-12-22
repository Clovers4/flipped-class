package online.templab.flippedclass.mapper;

import online.templab.flippedclass.entity.Round;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author chenr
 */
@Component
public interface RoundMapper extends Mapper<Round> {
    /**
     * 根据 roundId 更新属性不为 null 的值
     *
     * @param round
     * @return
     */
    int updateByRoundIdSelective(Round round);

    /**
     * 通过courseID得到所有round，并注入round下所有seminar
     * @param courseId
     * @return
     */
    List<Round> selectByCourseId(Long courseId);

    /**
     * 根据 id 获得一个 round，并注入round下所有seminar
     *
     * @param id
     * @return
     */
    Round getOne(Long id);
}