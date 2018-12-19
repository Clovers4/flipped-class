package online.templab.flippedclass.mapper;

import online.templab.flippedclass.entity.Student;
import online.templab.flippedclass.entity.Team;
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
}