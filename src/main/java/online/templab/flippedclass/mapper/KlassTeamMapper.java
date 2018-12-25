package online.templab.flippedclass.mapper;

import online.templab.flippedclass.entity.KlassTeam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author fj
 */
@Component
public interface KlassTeamMapper extends Mapper<KlassTeam> {

    /**
     * 根据 klassId 和 studentId 获取teamId
     *
     * @param klassId
     * @param studentId
     * @return
     */
    KlassTeam selectByKlassIdAndStudentId(@Param("klassId") Long klassId, @Param("studentId") Long studentId);

}