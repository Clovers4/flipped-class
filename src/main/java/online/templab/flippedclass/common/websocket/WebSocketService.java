package online.templab.flippedclass.common.websocket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import online.templab.flippedclass.entity.*;
import online.templab.flippedclass.mapper.AttendanceMapper;
import online.templab.flippedclass.mapper.QuestionMapper;
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
@Slf4j
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
        log.info(">>>>>> {}", message);
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
            case "EndQuestionRequest":
                newMessage.setType("EndQuestionResponse")
                        .setContent(handleEndQuestionRequest(ksId, message));
                break;
            default:
                throw new RuntimeException();
        }

        log.info("<<<<<< {}", newMessage);
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
            // 更新当前正在进行的展示Index
            monitor.setOnPreAttendanceIndex(monitor.getOnPreAttendanceIndex() + 1);
            // 更新讨论课状态
            SeminarState state = monitor.getState()
                    .setProgressState("PAUSE")
                    .setTimeStamp(0L);

            // 更新当前提问数量
            monitor.setRaisedQuestionsCount(0);
            // 更新当前正在进行的展示
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
            log.info(question.toString());
            questionPool.put(onPreAttendanceId, question);
            // 更新当前提问数量
            SeminarMonitor monitor = getMonitor(ksId);
            monitor.setRaisedQuestionsCount(questionPool.size(onPreAttendanceId));

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

            log.info("onPreAttendanceId : {}", onPreAttendanceId);
            // 更新当前提问数量
            monitor.setRaisedQuestionsCount(questionPool.size(onPreAttendanceId));

            // 返回给前端的 json
            Map<String, Object> newContent = new HashMap<>(4);
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

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Autowired
    private QuestionMapper questionMapper;

    private String handleScoreRequest(Long ksId, RawMessage message) {
        try {
            JsonNode jsonContent = objectMapper.readTree(message.getContent());

            SeminarMonitor monitor = getMonitor(ksId);
            String type = jsonContent.get("type").asText();
            Integer score = jsonContent.get("score").asInt();
            if ("Attendance".equals(type)) {
                // 获得当前指定的 attendance
                Integer attendanceIdx = jsonContent.get("attendanceIdx").asInt();
                Attendance attendance = monitor.getEnrollList().get(attendanceIdx);
                // 更新 monitor 中的分数表
                monitor.markPreScore(attendance.getId(), score);
                // 更新 Attendance 的分数，即更新到 SeminarScore 表
                KlassSeminar klassSeminar = seminarService.getKlassSeminarById(ksId);
                scoreService.markerScore(
                        new SeminarScore().setPresentationScore(BigDecimal.valueOf(score)).setKlassSeminarId(ksId).setTeamId(attendance.getTeamId()),
                        klassSeminar.getSeminarId(),
                        klassSeminar.getKlassId()
                );
            } else if ("Question".equals(type)) {
                Integer questionIdx = jsonContent.get("questionIdx").asInt();
                Integer attendanceIdx = jsonContent.get("attendanceIdx").asInt();
                Attendance attendance = monitor.getEnrollList().get(attendanceIdx);
                // 获得 抽取提问
                List<Question> askedQuestions = monitor.getAskedQuestion(monitor.getEnrollList().get(attendanceIdx).getId());
                log.info("questionIdx : {}", questionIdx);
                for (Question question : askedQuestions) {
                    log.info("被提问的: {}", question.toString());
                }
                // 获得 question
                Question question = askedQuestions.get(questionIdx);
                log.info(question.toString());
                // 将 question 的打分状况 更新/插入 到 question表
                if (questionMapper.select(
                        new Question()
                                .setKlassSeminarId(question.getKlassSeminarId())
                                .setAttendanceId(question.getAttendanceId())
                                .setStudentId(question.getStudentId())
                                .setTeamId(question.getTeamId())
                ).size() == 0) {
                    questionMapper.insert(question.setScore(BigDecimal.valueOf(score)));
                } else {
                    questionMapper.updateByUniqueKey(
                            question.getKlassSeminarId(),
                            question.getAttendanceId(),
                            question.getTeamId(),
                            question.getStudentId(),
                            BigDecimal.valueOf(score));
                }
                // 将 question 的打分状况更新到 seminarScore表
                // FIXME
                KlassSeminar klassSeminar = seminarService.getKlassSeminarById(ksId);
                scoreService.markerScore(
                        new SeminarScore().setQuestionScore(BigDecimal.valueOf(score)).setKlassSeminarId(ksId).setTeamId(question.getTeamId()),
                        klassSeminar.getSeminarId(),
                        klassSeminar.getKlassId()
                );
            }

            Map<String, Object> newContent = new HashMap<>();
            return objectMapper.writeValueAsString(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String handleEndQuestionRequest(Long ksId, RawMessage message) {
        try {
            // 这个讨论课结束时，更新 roundScore表
            // FIXME
            KlassSeminar klassSeminar = seminarService.getKlassSeminarById(ksId);
            Seminar seminar = seminarService.get(klassSeminar.getSeminarId());
            scoreService.updateRoundScore(seminar.getRoundId(), klassSeminar.getKlassId());

            // 返回给前端的 JSON 信息
            Map<String, Object> newContent = new HashMap<>();
            return objectMapper.writeValueAsString(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
