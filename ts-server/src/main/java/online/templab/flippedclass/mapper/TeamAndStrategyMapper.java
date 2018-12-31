package online.templab.flippedclass.mapper;

import online.templab.flippedclass.entity.TeamAndStrategy;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
/**
 * @author chenr
 */
@Component
public interface TeamAndStrategyMapper extends Mapper<TeamAndStrategy>{

    /**
     * 获得最大 Id
     *
     * @return
     */
    Integer getMaxId();

    /**
     * 插入并返回 id
     *
     * @return
     */
    @Options(useGeneratedKeys=true, keyProperty = "id", keyColumn = "id")
    Integer myInsert(TeamAndStrategy teamAndStrategy);
}