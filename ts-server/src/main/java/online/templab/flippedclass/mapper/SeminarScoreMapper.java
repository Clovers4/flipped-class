package online.templab.flippedclass.mapper;

import online.templab.flippedclass.entity.SeminarScore;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author wk
 * @author fj
 */
@Component
public interface SeminarScoreMapper extends Mapper<SeminarScore> {

    /**
     * 根据非空项跟新 seminarScore
     *
     * @param seminarScore
     * @return
     */
    int updateByKlassSeminarId(SeminarScore seminarScore);

}