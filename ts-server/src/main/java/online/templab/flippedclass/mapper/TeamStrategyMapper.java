package online.templab.flippedclass.mapper;

import online.templab.flippedclass.entity.TeamStrategy;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
/**
 * @author chenr
 */
@Component
public interface TeamStrategyMapper extends Mapper<TeamStrategy> {

    /**
     * 获得最大 Id
     *
     * @return
     */
    Integer getMaxStrategySerial();
}