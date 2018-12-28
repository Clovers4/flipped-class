package online.templab.flippedclass.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author wk
 */
@Data
@Accessors(chain = true)
public class ApplicationHandleDTO {

    private Long appId;

    private Long mainCourseId;

    private Long subCourseId;

    private Long teamId;

    /**
     * 0 - ShareSeminarApplication
     * 1 - ShareTeamApplication
     * 2 - TeamValidApplication
     */
    private Integer appType;

    /**
     * 0 - 拒绝
     * 1 - 接收
     */
    private Integer operationType;

/*
    @Override
    public String toString() {
        return DebugLogger.toJsonString(this);
    }
*/

}
