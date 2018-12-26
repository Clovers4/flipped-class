package online.templab.flippedclass.common.websocket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import online.templab.flippedclass.entity.*;
import online.templab.flippedclass.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wk
 */
@Service
public class WebSocketService {

    @Autowired
    private TeamService teamService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private SeminarService seminarService;

    @Autowired
    private KlassService klassService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * K klassSeminarId
     * V SeminarMonitor
     */
    public static final Map<Long, SeminarMonitor> SEMINAR_MONITOR_MAP = new HashMap<>();

    public SeminarMonitor getMonitor(Long klassSeminarId) {
        SeminarMonitor seminarMonitor = SEMINAR_MONITOR_MAP.getOrDefault(klassSeminarId,
                new SeminarMonitor(klassSeminarId, seminarService.getEnrollListByKlassSeminarId(klassSeminarId)));
        SEMINAR_MONITOR_MAP.putIfAbsent(klassSeminarId, seminarMonitor);
        return seminarMonitor;
    }

    public Team getTeamByStudentNum(Long klassSeminarId, String studentNum) {
        KlassSeminar klassSeminar = seminarService.getKlassSeminarById(klassSeminarId);
        Klass klass = klassService.get(klassSeminar.getKlassId());
        Course course = courseService.get(klass.getCourseId());
        Student student = studentService.getByStudentNum(studentNum);
        return teamService.get(course.getId(), student.getId());
    }

    public RawMessage handleMessage(Long ksId, RawMessage message) {
        System.out.println(">>> " + message);
        RawMessage newMessage = new RawMessage();

        switch (message.getType()) {
            case "SeminarStateRequest":
                newMessage.setType("SeminarStateResponse")
                        .setContent(handleSeminarStateRequest(ksId, message));
                break;
            case "SwitchTeamRequest":
                newMessage.setType("SwitchTeamResponse")
                        .setContent(handleSwitchTeamRequest(ksId, message));
                break;
            default:
                throw new RuntimeException();
        }

        System.out.println("<<< " + newMessage);
        return newMessage;
    }

    private String handleSeminarStateRequest(Long ksId, RawMessage message) {
        try {
            JsonNode jsonContent = objectMapper.readTree(message.getContent());
            SeminarMonitor monitor = SEMINAR_MONITOR_MAP.get(ksId);
            SeminarState state = monitor.getState()
                    .setProgressState(jsonContent.get("request").asText().toString())
                    .setTimeStamp(Long.valueOf(jsonContent.get("timeStamp").asText().toString()));
            if ("START".equals(state.getProgressState())) {
                state.setProgressState("PROCESSING");
            }

            Map<String, Object> newContent = new HashMap<>();
            newContent.put("state", state);

            return objectMapper.writeValueAsString(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    private String handleSwitchTeamRequest(Long ksId, RawMessage message) {
        try {
            JsonNode jsonContent = objectMapper.readTree(message.getContent());
            SeminarMonitor monitor = SEMINAR_MONITOR_MAP.get(ksId);
            monitor.setOnPreAttendanceIndex(monitor.getOnPreAttendanceIndex() + 1);
            SeminarState state = monitor.getState()
                    .setProgressState("PAUSE")
                    .setTimeStamp(0L);

            if (monitor.getOnPreAttendanceIndex() < monitor.getEnrollList().size()) {
                monitor.setOnPreAttendance(monitor.getEnrollList().get(monitor.getOnPreAttendanceIndex()));
            } else {
                state.setProgressState("TERMINATE");
            }
            Map<String, Object> newContent = new HashMap<>();
            newContent.put("attendanceIndex", monitor.getOnPreAttendanceIndex());
            newContent.put("state", monitor.getState());
            return objectMapper.writeValueAsString(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
