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
     * 根据 courseId 查出该课程下所有未组队学生
     *
     * @param courseId
     * @return
     */
    List<Student> selectUnTeamedStudentByCourseId(Long courseId);

    /**
     * 通过 teamId 获得 team
     * team 下注入 allStudent
     * allStudent 注入 courseIdList
     *
     * @param teamId
     * @return
     */
    List<Student> selectTeamMerberCourseIdByTeamId(Long teamId);

    /**
     * 根据 team id  获取 team
     *
     * @param teamId
     * @return
     */
    List<Student> selectTeamMemberByTeamId(@Param("teamId") Long teamId);


    /**
     * 通过klass和team确定这个队伍的学生名单ID
     * @param klassId
     * @param teamId
     * @return
     */
    List<Student> selectStudentsByKlassIdTeamId(@Param("klassId") Long klassId,@Param("teamId") Long teamId);
}
