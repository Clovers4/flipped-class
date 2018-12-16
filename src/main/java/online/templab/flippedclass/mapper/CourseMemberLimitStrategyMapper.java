package online.templab.flippedclass.mapper;

import online.templab.flippedclass.entity.CourseMemberLimitStrategy;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author wk
 */
@Component
public interface CourseMemberLimitStrategyMapper extends Mapper<CourseMemberLimitStrategy> {
}