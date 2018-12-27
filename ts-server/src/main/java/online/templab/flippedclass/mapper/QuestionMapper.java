package online.templab.flippedclass.mapper;

import online.templab.flippedclass.entity.Question;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.math.BigDecimal;

/**
 * @author wk
 */
@Component
public interface QuestionMapper extends Mapper<Question> {

    /**
     * 根据下列4个参数搜索进行更新
     *
     * @param teamId
     * @param attendanceId
     * @param klassSeminarId
     * @param studentId
     * @return
     */
    int updateByUniqueKey(@Param("klassSeminarId") Long klassSeminarId,
                          @Param("attendanceId") Long attendanceId,
                          @Param("teamId") Long teamId,
                          @Param("studentId") Long studentId,
                          @Param("score") BigDecimal score);
}