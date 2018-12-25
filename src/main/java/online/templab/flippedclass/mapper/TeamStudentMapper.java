package online.templab.flippedclass.mapper;

import online.templab.flippedclass.entity.TeamStudent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author fj
 */
@Component
public interface TeamStudentMapper extends Mapper<TeamStudent> {

    /**
     * 根据list studentId 插入 team_Student 关系
     *
     * @param teamId
     * @param studentId
     * @return
     */
    int insertList(@Param("teamId") Long teamId, @Param("studentIdList") List<Long> studentId);
}