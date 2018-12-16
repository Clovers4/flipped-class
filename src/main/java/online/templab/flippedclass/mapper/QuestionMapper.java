package online.templab.flippedclass.mapper;

import online.templab.flippedclass.entity.Question;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author wk
 */
@Component
public interface QuestionMapper extends Mapper<Question> {
}