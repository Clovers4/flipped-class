package online.templab.flippedclass.controller;

import lombok.extern.slf4j.Slf4j;
import online.templab.flippedclass.common.email.EmailService;
import online.templab.flippedclass.entity.Student;
import online.templab.flippedclass.entity.Teacher;
import online.templab.flippedclass.exception.CannotAccessResetPwdException;
import online.templab.flippedclass.service.CaptchaService;
import online.templab.flippedclass.service.StudentService;
import online.templab.flippedclass.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author wk
 */
@Slf4j
@Controller
public class IndexController {

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = {"/", "/login"})
    public String login() {
        return "login";
    }

    @GetMapping("/forgetPassword")
    public String forgetPassword() {
        return "forgetPassword";
    }

    @PostMapping("/captcha/forgetPassword")
    public @ResponseBody
    ResponseEntity<Object> getCaptcha(String account, HttpSession session) {
        String captcha = captchaService.generate();

        Teacher teacher = teacherService.getByTeacherNum(account);
        if (teacher != null) {
            if (!teacher.getActivated()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).contentType(MediaType.APPLICATION_JSON_UTF8).body("该教师尚未激活");
            }
            log.info("teacher forget password,captcha is {}", captcha);
            session.setAttribute("forgetPasswordCaptcha", captcha);
            session.setAttribute("forgetType", "teacher");
            session.setAttribute("forgetAccount", account);
            emailService.sendCaptcha(teacher.getEmail(), captcha);
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON_UTF8).body(null);
        }
        Student student = studentService.getByStudentNum(account);
        if (student != null) {
            if (!student.getActivated()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).contentType(MediaType.APPLICATION_JSON_UTF8).body("该学生尚未激活");
            }
            log.info("student forget password,captcha is {}", captcha);
            session.setAttribute("forgetPasswordCaptcha", captcha);
            session.setAttribute("forgetType", "student");
            session.setAttribute("forgetAccount", account);
            emailService.sendCaptcha(student.getEmail(), captcha);
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON_UTF8).body(null);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).contentType(MediaType.APPLICATION_JSON_UTF8).body("账户不存在");
    }

    @PostMapping("/forgetPassword")
    public @ResponseBody
    ResponseEntity<Object> forgetPassword(String account, String captcha, HttpSession session) {
        String senderCaptcha = ((String) session.getAttribute("forgetPasswordCaptcha"));
        if (captcha.equals(senderCaptcha)) {
            session.setAttribute("enableModify", true);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @GetMapping("/modifyPassword")
    public String modifyPassword(HttpSession session) {
        String enableModify = "enableModify";
        if (session.getAttribute(enableModify) != null) {
            return "modifyPassword";
        } else {
            throw new CannotAccessResetPwdException();
        }
    }

    @PostMapping("/modifyPassword")
    public @ResponseBody
    ResponseEntity<Object> modifyPassword(String password, HttpSession session) {
        String forgetType = "forgetType";
        String studentType = "student", teacherType = "teacher";
        String forgetAccount = (String) session.getAttribute("forgetAccount");
        if (studentType.equals(session.getAttribute(forgetType))) {
            if (!studentService.modifyPassword(forgetAccount, password)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            }
        } else if (teacherType.equals(session.getAttribute(forgetType))) {
            if (!teacherService.modifyPassword(forgetAccount, password)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
