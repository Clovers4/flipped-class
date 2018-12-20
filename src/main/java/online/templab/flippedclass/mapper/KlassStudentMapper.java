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
     * 插入学生id list
     *
     * @param courseId
     * @param klassId
     * @param teamId
     * @param studentNum
     * @return
     */
    int insertList(@Param("courseId") Long courseId, @Param("klassId") Long klassId, @Param("teamId") Long teamId, @Param("studentNum") List<Long> studentNum);
}