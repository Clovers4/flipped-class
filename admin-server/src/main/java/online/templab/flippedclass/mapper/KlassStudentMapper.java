package online.templab.flippedclass.mapper;

import online.templab.flippedclass.entity.KlassStudent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author wk
 */
@Component
public interface KlassStudentMapper extends Mapper<KlassStudent> {

    /**
     * 通过kalssId和studenNum找到klassStudent
     * @param klassId
     * @param studentNum
     * @return
     */
    KlassStudent selectOneByStudentNum(@Param("klassId")Long klassId,@Param("studentNum")String studentNum);

}