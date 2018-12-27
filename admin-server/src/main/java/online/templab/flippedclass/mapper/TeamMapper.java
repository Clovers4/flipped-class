package online.templab.flippedclass.mapper;

import online.templab.flippedclass.entity.Student;
import online.templab.flippedclass.entity.Team;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author wk
 */
@Component
public interface TeamMapper extends Mapper<Team> {

    /**
     * 学生退组
     *
     * @param teamId
     * @param studentId
     * @return
     */
    Boolean deleteMember(Long teamId,Long studentId);

    /**
     * 根据 courseId 获取该课程下课程最大队伍Serial
     *
     * @param courseId
     * @param klassId
     * @return
     */
    int getMaxTeamSerial(@Param("courseId") Long courseId,@Param("klassId") Long klassId);

    /**
     * 根据 teamId 得到 team
     * 并注入 teamStrategyList
     *
     * @param teamId
     * @return
     */
    Team selectByTeamId(Long teamId);

}