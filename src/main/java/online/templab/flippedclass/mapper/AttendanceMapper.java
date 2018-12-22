package online.templab.flippedclass.mapper;

import online.templab.flippedclass.entity.Attendance;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author wk
 * @author jh
 */
@Component
public interface AttendanceMapper extends Mapper<Attendance> {

    /**
     * 通过klassSeminarID得到讨论课下的报名情况
     * @param klassSeminarId
     * @return
     */
    List<Attendance> selectByKlassSeminarId(Long klassSeminarId);
}