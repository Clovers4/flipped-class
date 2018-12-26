package online.templab.flippedclass.common.websocket;

import lombok.Data;
import lombok.experimental.Accessors;
import online.templab.flippedclass.entity.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wk
 */
@Data
@Accessors(chain = true)
public class SeminarMonitor {

    /**
     * klassSeminarId
     */
    Long klassSeminarId;

    /**
     * 展示组
     */
    List<Attendance> enrollList;

    /**
     * K attendanceId
     * V preScore
     */
    Map<Long, Integer> preScoreMap;

    /**
     * 当前的展示到的Index
     */
    Integer onPreAttendanceIndex;

    /**
     * 当前正在进行的展示
     */
    Attendance onPreAttendance;

    /**
     * 当前提问数量
     */
    Integer raisedQuestionsCount;

    /**
     * K attendanceId
     * V preScore
     */
    Map<Long, List<Question>> askedQuestion;

    /**
     * PAUSE / PROCESSING / TERMINATE
     */
    String progressState;

    public SeminarMonitor(Long klassSeminarId, List<Attendance> enrollList) {
        this.klassSeminarId = klassSeminarId;
        this.enrollList = enrollList;
        this.preScoreMap = new HashMap<>();
        for (Attendance enroll : enrollList) {
            preScoreMap.put(enroll.getId(), -1);
        }
        this.onPreAttendanceIndex = 0;
        this.onPreAttendance = enrollList.get(0);
        this.raisedQuestionsCount = 0;
        this.askedQuestion = new HashMap<>();
        this.progressState = "PAUSE";
    }

}
