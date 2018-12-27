package online.templab.flippedclass.mapper;

import online.templab.flippedclass.entity.TeamValidApplication;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author wk
 */
@Component
public interface TeamValidApplicationMapper extends Mapper<TeamValidApplication> {

    /**
     * 通过老师id得到所有组队请求
     * @param teacherId
     * @return
     */
    List<TeamValidApplication> selectTeamApplicationByTeacherId(Long teacherId);
}