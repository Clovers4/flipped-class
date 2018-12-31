package online.templab.flippedclass.mapper;

import online.templab.flippedclass.entity.ShareSeminarApplication;
import online.templab.flippedclass.entity.ShareTeamApplication;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author wk
 */
@Component
public interface ShareSeminarApplicationMapper extends Mapper<ShareSeminarApplication> {

    /**
     * 得到当前老师的所有共享讨论课记录
     * @param teacherId
     * @return
     */
    List<ShareSeminarApplication> selectShareSeminarApplication(Long teacherId);
}