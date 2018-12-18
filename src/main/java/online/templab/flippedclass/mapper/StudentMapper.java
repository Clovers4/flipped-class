package online.templab.flippedclass.mapper;

import online.templab.flippedclass.entity.Student;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author wk
 */
@Component
public interface StudentMapper extends Mapper<Student> {

    /**
     * 根据 account 更新属性不为 null 的值
     *
     * @param student
     * @return
     */
    int updateByAccountSelective(Student student);
}
