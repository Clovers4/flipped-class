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
     * 通过学生id 插入list
     *
     * @param teamId
     * @param studentNum
     * @return
     */
    int insertList(@Param("teamId") Long teamId, @Param("studentNum") List<Long> studentNum);

}