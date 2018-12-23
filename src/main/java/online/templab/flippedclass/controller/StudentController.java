package online.templab.flippedclass.controller;

import online.templab.flippedclass.common.email.EmailService;
import online.templab.flippedclass.entity.KlassSeminar;
import online.templab.flippedclass.entity.Student;
import online.templab.flippedclass.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author wk
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SeminarService seminarService;

    @Autowired
    private RoundService roundService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private KlassService klassService;

    @Autowired
    private EmailService emailService;

    private final static String STUDENT_ID_GIST = "studentId";

    @GetMapping(value = {"", "/", "/index"})
    public String index(Model model, HttpSession session, @AuthenticationPrincipal User user) {
        Student student = studentService.getByStudentNum(user.getUsername());
        session.setAttribute(STUDENT_ID_GIST, student.getId());

        if (student.getActivated()) {
            model.addAttribute("student", student);
            return "student/index";
        } else {
            return "redirect:/student/activation";
        }
    }


    @PostMapping("/captcha/{type}")
    public @ResponseBody
    ResponseEntity<Object> getCaptcha(@PathVariable String type, String email, HttpSession session) {
        String captcha = captchaService.generate();
        emailService.sendCaptcha(email, captcha);
        session.setAttribute(type + "Captcha", captcha);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/activation")
    public String activation() {
        return "student/activation";
    }

    @PostMapping("/activation")
    public @ResponseBody
    ResponseEntity<Object> activate(String password, String email, String captcha, HttpSession session) {
        String senderCaptcha = ((String) session.getAttribute("activationCaptcha"));
        if (captcha.equals(senderCaptcha)) {
            if (studentService.activate((Long) session.getAttribute(STUDENT_ID_GIST), password, email)) {
                session.removeAttribute("activationCaptcha");
                return ResponseEntity.status(HttpStatus.OK).body(null);
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("学生不存在");
            }
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("验证码错误");
        }
    }

    @GetMapping("/setting")
    public String setting(Model model, @AuthenticationPrincipal User user) {
        Student student = studentService.getByStudentNum(user.getUsername());
        model.addAttribute("student", student);

        return "student/setting";
    }


    @GetMapping("/modifyEmail")
    public String modifyEmail() {
        return "student/modifyEmail";
    }

    @PostMapping("/modifyEmail")
    public @ResponseBody
    ResponseEntity<Object> modifyEmail(String email, String captcha, HttpSession session) {
        String senderCaptcha = ((String) session.getAttribute("modifyEmailCaptcha"));
        if (captcha.equals(senderCaptcha)) {
            studentService.update(
                    new Student()
                            .setId((Long) session.getAttribute(STUDENT_ID_GIST))
                            .setEmail(email)
            );
            session.removeAttribute("modifyEmailCaptcha");
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }


    @GetMapping("/modifyPassword")
    public String modifyPassword() {
        return "student/modifyPassword";
    }

    @PostMapping("/modifyPassword")
    public @ResponseBody
    ResponseEntity<Object> modifyPassword(String password, HttpSession session) {
        studentService.update(
                new Student()
                        .setId((Long) session.getAttribute(STUDENT_ID_GIST))
                        .setPassword(password)
        );
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/courseList")
    public String courses(Model model, HttpSession session) {
        Long studentId = (Long) session.getAttribute(STUDENT_ID_GIST);
        // TODO:恢复
        //   model.addAttribute("klasses", studentService.getKlassesByStudentId(((String) session.getAttribute(STUDENT_ID_GIST))));
        return "student/courseList";
    }


    @PostMapping("/course/seminarList")
    public String seminarList(Long courseId, Model model) {
        model.addAttribute("rounds", roundService.listByCourseId(courseId));
        return "student/course/seminarList";
    }

    @PostMapping("/course/seminar/info")
    public String seminarInfo(Long klassId, Long seminarId, Model model) {
        KlassSeminar klassSeminar = seminarService.getKlassSeminar(klassId, seminarId);

        model.addAttribute("klassSeminar", klassSeminar);
        return "student/course/seminar/info";
    }

    @PostMapping("/course/seminar/enrollList")
    public String seminarEnrollList(Long klassId, Long seminarId, Model model) {
        KlassSeminar klassSeminar = seminarService.getKlassSeminar(klassId, seminarId);
        model.addAttribute("enrollList", seminarService.getEnrollListByKlassSeminarId(klassSeminar.getId()));
        return "student/course/seminar/enrollList";
    }

    @PostMapping("/course/seminar/grade")
    public String seminarGrade(String klassId, String seminarId, Model model) {
        return "student/course/seminar/grade";
    }

    @PostMapping("/course/seminar/report")
    public String seminarReport(String klassId, String seminarId, Model model) {
        return "student/course/seminar/report";
    }

    @PostMapping("/course/team")
    public String teamList(Long courseId, Model model) {
        model.addAttribute("teams", teamService.listByCourseId(courseId));
        model.addAttribute("students", teamService.listUnTeamedStudentByCourseId(courseId));
        return "student/course/teamList";
    }

    @PostMapping("/course/info")
    public String courseInfo(String courseId, Model model) {
        return "student/course/info";
    }

    @PostMapping("/course/grade")
    public String seminarGrade(String courseId, Model model) {
        return "student/course/grade";
    }
}
