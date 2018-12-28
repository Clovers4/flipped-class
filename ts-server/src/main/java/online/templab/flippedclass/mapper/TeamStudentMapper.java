package online.templab.flippedclass.mapper;

import online.templab.flippedclass.entity.TeamStudent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;

/**
 * @author fj
 * @author jh
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

    /**
     * 统计某个team在某个klass下的学生人数
     * @param klassId
     * @param teamId
     * @return
     */
    Long selectCountByKlassIdTeamId(@Param("klassId")Long klassId, @Param("teamId") Long teamId);
}