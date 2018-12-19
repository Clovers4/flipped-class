package online.templab.flippedclass.mapper;

import online.templab.flippedclass.entity.Teacher;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author wk
 */
@Component
public interface TeacherMapper extends Mapper<Teacher> {

    /**
     * 根据 teacherNum 更新属性不为 null 的值
     *
     * @param teacher
     * @return
     */
    int updateByTeacherNumSelective(Teacher teacher);

}