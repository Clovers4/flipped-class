package online.templab.flippedclass.controller;

import online.templab.flippedclass.common.websocket.QuestionPool;
import online.templab.flippedclass.entity.KlassSeminar;
import online.templab.flippedclass.entity.Question;
import online.templab.flippedclass.entity.Student;
import online.templab.flippedclass.service.SeminarService;
import online.templab.flippedclass.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import java.util.concurrent.atomic.AtomicInteger;

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
    private QuestionPool questionPool;

    @GetMapping("/webs")
    public String webs() {
        return "webs";
    }

    @PostMapping("/student/course/seminar/processing")
    public String seminarProcessing(Long klassId, Long seminarId, Model model, HttpSession session){
        KlassSeminar klassSeminar = seminarService.getKlassSeminar(klassId, seminarId);
   //TODO     SeminarMonitor monitor = webSocketService.getMonitor(klassSeminar.getId());
    //    model.addAttribute("team", monitor.getTeamByStudentNum((String) session.getAttribute("studentId")));
        model.addAttribute("ksId", klassSeminar.getId());
   //     model.addAttribute("monitor", monitor);
        return "student/course/seminar/progressing";
    }

    @PostMapping("/teacher/course/seminar/progressing")
    public String seminarProgressing(String klassSeminarId, Model model) {
        model.addAttribute("ksId", klassSeminarId);
   //TODO     model.addAttribute("monitor", webSocketService.getMonitor(klassSeminarId));
        return "teacher/course/seminar/progressing";
    }

    @MessageMapping("/teacher/klassSeminar/{ksId}")
    @SendTo("/topic/client/{ksId}")
    public Question pickQuestion(@DestinationVariable Long attendanceId, String message) {
        return questionPool.pick(attendanceId);
    }

    @MessageMapping("/student/klassSeminar/{ksId}")
    @SendTo("/topic/client/{ksId}")
    public String putQuestion(@DestinationVariable Long attendanceId, Question question, @AuthenticationPrincipal User user) {
        Student student = studentService.getByStudentNum(user.getUsername());
        question.setStudentId(student.getId());
        question.setStudent(student);

        return "有新的提问";
    }
}
