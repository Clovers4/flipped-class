package online.templab.flippedclass.mapper;

import online.templab.flippedclass.entity.KlassSeminar;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author wk
 * @author jh
 */
@Component
public interface KlassSeminarMapper extends Mapper<KlassSeminar> {
    /**
     * 通过klassId和seminarId得到klassSeminar
     *
     * @param klassId
     * @param seminarId
     * @return
     */
    KlassSeminar selectOneByKlassIdSeminarId(@Param("klassId")Long klassId,@Param("seminarId") Long seminarId);
}