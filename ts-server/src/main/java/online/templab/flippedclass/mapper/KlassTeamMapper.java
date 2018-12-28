package online.templab.flippedclass.mapper;

import online.templab.flippedclass.entity.KlassTeam;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author fj
 * @author jh
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

    /**
     * 根据 courseId 和 studentId 获取teamId
     *
     * @param courseId
     * @param studentId
     * @return
     */
    KlassTeam selectByCourseIdAndStudentId(@Param("courseId") Long courseId, @Param("studentId") Long studentId);
}