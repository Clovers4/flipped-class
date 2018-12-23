package online.templab.flippedclass.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author wk
 */
@Data
@Accessors(chain = true)
public class ShareApplicationDTO {

    private String mainCourseId;

    private String subCourseId;

    private Integer shareType;

/*
    @Override
    public String toString() {
        return DebugLogger.toJsonString(this);
    }*/

}
