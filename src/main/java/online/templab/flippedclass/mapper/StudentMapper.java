package online.templab.flippedclass.mapper;

import online.templab.flippedclass.entity.Student;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author wk
 */
@Component
public interface StudentMapper extends Mapper<Student> {
}