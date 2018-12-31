package online.templab.flippedclass.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import online.templab.flippedclass.common.websocket.RawMessage;
import online.templab.flippedclass.common.websocket.SeminarMonitor;
import online.templab.flippedclass.common.websocket.WebSocketService;
import online.templab.flippedclass.entity.KlassSeminar;
import online.templab.flippedclass.service.SeminarService;
import online.templab.flippedclass.service.StudentService;
import online.templab.flippedclass.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.security.Principal;

/**
 * @author wk
 */
@Controller
public class WebSocketController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SeminarService seminarService;

    @Autowired
    private WebSocketService webSocketService;


    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/student/course/seminar/progressing")
    public String seminarProcessing(Long klassId, Long seminarId, Model model, Principal principal) {
        KlassSeminar klassSeminar = seminarService.getKlassSeminar(klassId, seminarId);
        SeminarMonitor monitor = webSocketService.getMonitor(klassSeminar.getId());

        Integer state = klassSeminar.getState();
        model.addAttribute("state", state);
        model.addAttribute("studentNum", principal.getName());
        model.addAttribute("ksId", klassSeminar.getId());
        model.addAttribute("team", webSocketService.getTeamByStudentNum(klassSeminar.getId(), principal.getName()));
        int end = 2;
        if (state != end) {
            model.addAttribute("monitor", monitor);
        }
        return "student/course/seminar/progressing";
    }

    @PostMapping("/teacher/course/seminar/progressing")
    public String seminarProgressing(Long klassSeminarId, Model model) {
        KlassSeminar klassSeminar = seminarService.getKlassSeminarById(klassSeminarId);
        Boolean hasEnd = klassSeminar.getState() == 2;
        model.addAttribute("ksId", klassSeminarId);
        model.addAttribute("hasEnd", hasEnd);
        if (!hasEnd) {
            model.addAttribute("monitor", webSocketService.getMonitor(klassSeminarId));
        }
        return "teacher/course/seminar/progressing";
    }

    @MessageMapping("/teacher/klassSeminar/{ksId}")
    @SendTo("/topic/client/{ksId}")
    public RawMessage teacherMessage(@DestinationVariable Long ksId, RawMessage message, Principal principal) {
        try {
            JsonNode jsonContent = objectMapper.readTree(message.getContent());
            ((ObjectNode) jsonContent).put("teacherNum", principal.getName());
            message.setContent(jsonContent.toString());
            return webSocketService.handleMessage(ksId, message);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @MessageMapping("/student/klassSeminar/{ksId}")
    @SendTo("/topic/client/{ksId}")
    public RawMessage studentMessage(@DestinationVariable Long ksId, RawMessage message, Principal principal) throws IOException {
        JsonNode jsonContent = objectMapper.readTree(message.getContent());
        ((ObjectNode) jsonContent).put("studentNum", principal.getName());
        message.setContent(jsonContent.toString());
        return webSocketService.handleMessage(ksId, message);
    }
}
