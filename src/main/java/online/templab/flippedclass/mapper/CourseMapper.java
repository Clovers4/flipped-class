package online.templab.flippedclass.mapper;

import online.templab.flippedclass.entity.Course;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author wk
 */
@Component
public interface CourseMapper extends Mapper<Course> {
}