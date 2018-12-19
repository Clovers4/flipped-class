package online.templab.flippedclass.mapper;

import online.templab.flippedclass.entity.KlassStudent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author wk
 */
@Component
public interface KlassStudentMapper extends Mapper<KlassStudent> {

    /**
     * 根据 courseId 和 studentId 查出这名学生在这门课程下组队队伍
     *
     * @param courseId
     * @param studentId
     * @return
     */
    KlassStudent selectByCourseIdAndStudentId(@Param("courseId") Long courseId, @Param("studentId") Long studentId);

    /**
     * 根据 teamId 和 studentId 删除 klass_student 联系
     *
     * @param teamId
     * @param studentId
     * @return
     */
    int deleteByTeamIdAndStudentId(@Param("teamId") Long teamId, @Param("studentId") Long studentId);

    /**
     * 根据 teamId 删除所有队伍与学生关系
     *
     * @param teamId
     * @return
     */
    int deleteByTeamId(Long teamId);

    /**
     * 根据 klassId 和 studentId 查出 courseId
     *
     * @param klassId
     * @param studentId
     * @return
     */
    KlassStudent selectByStudentIdAndKlassId(@Param("klassId") Long klassId, @Param("studentId") Long studentId);

    /**
     * 插入学生id list
     *
     * @param courseId
     * @param klassId
     * @param teamId
     * @param studentNum
     * @return
     */
    int insertList(@Param("courseId") Long courseId, @Param("klassId") Long klassId, @Param("teamId") Long teamId, @Param("studentNum") List<Long> studentNum);

    /**
     * 根据 teamId 和 studentId 查 courseId
     *
     * @param teamId
     * @param studentId
     * @return
     */
    KlassStudent selectByTeamIdAndStudentId(@Param("teamId")Long teamId,@Param("studentId")Long studentId);
}