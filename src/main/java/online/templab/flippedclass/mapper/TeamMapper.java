package online.templab.flippedclass.mapper;

import online.templab.flippedclass.entity.Team;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author wk
 */
@Component
public interface TeamMapper extends Mapper<Team> {
}