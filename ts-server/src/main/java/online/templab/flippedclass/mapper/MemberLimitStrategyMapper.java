package online.templab.flippedclass.mapper;

import online.templab.flippedclass.entity.MemberLimitStrategy;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
/**
 * @author chenr
 */
@Component
public interface MemberLimitStrategyMapper extends Mapper<MemberLimitStrategy> {

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
    Integer myInsert(MemberLimitStrategy memberLimitStrategy);
}