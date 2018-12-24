package online.templab.flippedclass.mapper;

import online.templab.flippedclass.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author wk
 */
@Component
public interface StudentMapper extends Mapper<Student> {

    /**
     * 根据 studentNum 更新属性不为 null 的值
     *
     * @param student
     * @return
     */
    int updateByStudentNumSelective(Student student);

    /**
     * 根据 courseId 查出该课程下所有学生
     *
     * @param courseId
     * @return
     */
    List<Student> selectUnTeamedStudentByCourseId(Long courseId);

    /**
     * 根据 teamId 获取队伍成员
     *
     * @param courseId
     * @param teamId
     * @return
     */
    List<Student> selectTeamMemberByTeamId(@Param("courseId") Long courseId,@Param("teamId") Long teamId);
}
