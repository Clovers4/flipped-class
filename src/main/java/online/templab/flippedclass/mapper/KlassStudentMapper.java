package online.templab.flippedclass.mapper;

import online.templab.flippedclass.entity.KlassStudent;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author wk
 */
@Component
public interface KlassStudentMapper extends Mapper<KlassStudent> {
}