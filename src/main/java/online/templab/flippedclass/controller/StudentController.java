package online.templab.flippedclass.controller;

import online.templab.flippedclass.entity.Student;
import online.templab.flippedclass.service.SeminarService;
import online.templab.flippedclass.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author wk
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(value = {"", "/", "/index"})
    public String index(Model model, HttpSession session, @AuthenticationPrincipal User user) {
        Student student = studentService.getByStudentNum(user.getUsername());
        session.setAttribute("studentId", student.getId());
        model.addAttribute("student", student);
        return "student/index";
    }

    @GetMapping("/activation")
    public String activation() {
        return "student/activation";
    }

    @GetMapping("/option")
    public String option(Model model, @AuthenticationPrincipal User user) {
        Student student = studentService.getByStudentNum(user.getUsername());
        model.addAttribute("student", student);

        return "student/option";
    }

    @GetMapping("/courseList")
    public String courses(Model model, HttpSession session) {
        return "courseList";
    }
}
