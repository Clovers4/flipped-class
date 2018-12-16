package online.templab.flippedclass.mapper;

import online.templab.flippedclass.entity.Attendance;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author wk
 */
@Component
public interface AttendanceMapper extends Mapper<Attendance> {
}