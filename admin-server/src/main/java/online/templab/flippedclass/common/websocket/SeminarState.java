package online.templab.flippedclass.common.websocket;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author wk
 */
@Data
@Accessors(chain = true)
public class SeminarState {

    /**
     * PAUSE / PROCESSING / TERMINATE
     */
    private String progressState ;

    private Long timeStamp;
}
