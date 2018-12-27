package online.templab.flippedclass.mapper;

import online.templab.flippedclass.entity.Klass;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author wk
 */
@Component
public interface KlassMapper extends Mapper<Klass> {
    /**
     * 通过courseId找到所有班级id
     * @param courseId
     * @return
     */
    public List<Long> selectIdByCourseId(Long courseId);

//    /**
//     * 通过从课程KlassId和主课程courseId找到与从课程班级对应的主课程班级
//     * @param courseId
//     * @param subKlassId
//     * @return
//     */
//    Klass selectBySubKlassIdCourseId(@Param("courseId") Long courseId, @Param("subKlassId")Long subKlassId);

    /**
     * 通过studentID查找所有klass
     *
     * @param studentId
     * @return
     */
    List<Klass> listByStudentId(Long studentId);
}