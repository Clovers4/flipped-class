package online.templab.flippedclass.mapper;

import online.templab.flippedclass.entity.KlassRound;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author chenr
 */
@Component
public interface KlassRoundMapper extends Mapper<KlassRound> {
    /**
     * 根据 roundId 和 klassId 更新属性不为 null 的值
     *
     * @param klassRound
     * @return
     */
    int updateByRoundIdKlassIdSelective(KlassRound klassRound);

    /**
     * 通过 roundID 和 courseId 得到所有当前课程下的 klassRound，并注入 klass
     * @param roundId
     * @return
     */
    List<KlassRound> selectByRoundId(@Param("roundId") Long roundId, @Param("courseId") Long courseId);

    /**
     * 通过roundID,klassId得到round，并注入klass
     * @param roundId
     * @param klassId
     * @return
     */
    List<KlassRound> selectByRoundIdKlassId(@Param("roundId")Long roundId, @Param("klassId")Long klassId);
}