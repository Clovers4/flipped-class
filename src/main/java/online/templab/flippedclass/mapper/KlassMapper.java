package online.templab.flippedclass.mapper;

import online.templab.flippedclass.entity.Klass;
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
}