package online.templab.flippedclass.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author wk
 */
@Data
@Accessors(chain = true)
public class TeacherFilter {

    private Boolean newFilter = false;

    private Integer page = 0;

    private Integer count = 0;

    private String name = null;

    private String teacherNum = null;

}
