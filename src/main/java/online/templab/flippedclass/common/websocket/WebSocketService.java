package online.templab.flippedclass.common.websocket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import online.templab.flippedclass.entity.*;
import online.templab.flippedclass.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
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
    private ScoreService scoreService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private QuestionPool questionPool;

    /**
     * K klassSeminarId
     * V SeminarMonitor
     */
    private static final Map<Long, SeminarMonitor> SEMINAR_MONITOR_MAP = new HashMap<>();

    public SeminarMonitor getMonitor(Long klassSeminarId) {
        SeminarMonitor seminarMonitor = SEMINAR_MONITOR_MAP.getOrDefault(klassSeminarId,
                new SeminarMonitor(klassSeminarId, seminarService.getEnrollListByKlassSeminarId(klassSeminarId)));
        SEMINAR_MONITOR_MAP.putIfAbsent(klassSeminarId, seminarMonitor);
        return seminarMonitor;
    }

    public Long getOnPreAttendanceId(Long klassSeminarId) {
        return getMonitor(klassSeminarId).getOnPreAttendance().getId();
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
            case "RaiseQuestionRequest":
                newMessage.setType("RaiseQuestionResponse")
                        .setContent(handleRaiseQuestionRequest(ksId, message));
                break;
            case "PullQuestionRequest":
                newMessage.setType("PullQuestionResponse")
                        .setContent(handlePullQuestionRequest(ksId, message));
                break;
            case "ScoreRequest":
                newMessage.setType("ScoreResponse")
                        .setContent(handleScoreRequest(ksId, message));
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
            SeminarMonitor monitor = getMonitor(ksId);
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
            SeminarMonitor monitor = getMonitor(ksId);
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

    private String handleRaiseQuestionRequest(Long ksId, RawMessage message) {
        try {
            JsonNode jsonContent = objectMapper.readTree(message.getContent());
            String studentNum = jsonContent.get("studentNum").asText();
            Student student = studentService.getByStudentNum(studentNum);
            Team team = getTeamByStudentNum(ksId, studentNum);
            Long onPreAttendanceId = getOnPreAttendanceId(ksId);
            Question question = new Question()
                    .setStudentId(student.getId())
                    .setStudent(student)
                    .setKlassSeminarId(ksId)
                    .setTeamId(team.getId())
                    .setIsSelected(0)
                    .setScore(BigDecimal.valueOf(0))
                    .setAttendanceId(onPreAttendanceId)
                    .setTeam(team);
            questionPool.put(onPreAttendanceId, question);

            Map<String, Object> newContent = new HashMap<>();
            newContent.put("questionNum", questionPool.size(onPreAttendanceId));
            return objectMapper.writeValueAsString(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String handlePullQuestionRequest(Long ksId, RawMessage message) {
        try {
            JsonNode jsonContent = objectMapper.readTree(message.getContent());

            Long onPreAttendanceId = getOnPreAttendanceId(ksId);
            Question question = questionPool.pick(onPreAttendanceId);
            SeminarMonitor monitor = getMonitor(ksId);
            monitor.putQuestion(onPreAttendanceId, question);
            System.out.println("onPreAttendanceId" + onPreAttendanceId);

            Map<String, Object> newContent = new HashMap<>();
            newContent.put("studentNum", question.getStudent().getStudentNum());
            newContent.put("teamSerial", question.getTeam().getSerial());
            newContent.put("teamName", question.getTeam().getTeamName());
            newContent.put("questionCount", questionPool.size(onPreAttendanceId));
            return objectMapper.writeValueAsString(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String handleScoreRequest(Long ksId, RawMessage message) {
        try {
            JsonNode jsonContent = objectMapper.readTree(message.getContent());

            SeminarMonitor monitor = getMonitor(ksId);
            String type = jsonContent.get("type").asText();
            Integer score = jsonContent.get("score").intValue();
            if ("Attendance".equals(type)) {
                Integer attendanceIdx = jsonContent.get("attendanceIdx").intValue();
                Attendance attendance = monitor.getEnrollList().get(attendanceIdx);
                System.out.println(attendance);
                // TODO 打分
            } else if ("Question".equals(type)) {
                Integer questionIdx = jsonContent.get("questionIdx").intValue();
                Integer attendanceIdx = jsonContent.get("attendanceIdx").intValue();
                Attendance attendance = monitor.getEnrollList().get(attendanceIdx);
                System.out.println("attendance.getId :" + attendance.getId());
                List<Question> askedQuestions = monitor.getAskedQuestion(monitor.getEnrollList().get(attendanceIdx).getId());
                Question question = askedQuestions.get(questionIdx);
                System.out.println(question);
                // TODO 打分
            }

            Map<String, Object> newContent = new HashMap<>();
            return objectMapper.writeValueAsString(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


}
