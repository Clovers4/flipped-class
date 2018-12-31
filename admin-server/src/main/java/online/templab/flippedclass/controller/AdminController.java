package online.templab.flippedclass.controller;

import com.github.pagehelper.Page;
import online.templab.flippedclass.dto.StudentFilter;
import online.templab.flippedclass.dto.TeacherFilter;
import online.templab.flippedclass.entity.Student;
import online.templab.flippedclass.entity.Teacher;
import online.templab.flippedclass.service.StudentService;
import online.templab.flippedclass.service.TeacherService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wk
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    private static final String DEFAULT_PASSWORD = "123456";

    @RequestMapping(value = {"", "/", "/login"})
    public String adminLogin() {
        return "admin/login";
    }

    @GetMapping("/index")
    public String index() {
        return "admin/index";
    }

    @GetMapping("/teacherManage")
    public String teacherManage() {
        return "admin/teacherManage";
    }

    @PostMapping("/teacherList")
    public String teacherList(Model model, TeacherFilter filter) {
        Teacher target = new Teacher().setTeacherName(filter.getName()).setTeacherNum(filter.getTeacherNum());
        Page<Teacher> teacherPage = teacherService.getPage(target, new RowBounds(filter.getPage(), filter.getCount()));

        model.addAttribute("newFilter", filter.getNewFilter());
        model.addAttribute("fromIndex", teacherPage.getStartRow());
        model.addAttribute("sumPage", teacherPage.getPages());
        model.addAttribute("page", teacherPage.getPageNum());
        model.addAttribute("teachers", teacherPage);
        return "admin/teacherList";
    }

    @PutMapping("/teacher")
    public @ResponseBody
    ResponseEntity<Object> addTeacher(@RequestBody Teacher teacher) {
        teacher.setPassword(DEFAULT_PASSWORD);
        teacher.setActivated(false);
        // FIXME:前端待修复
        if (teacherService.insert(teacher)) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PatchMapping(value = "/teacher")
    public @ResponseBody
    ResponseEntity<Object> updateTeacher(@RequestBody Teacher teacher) {
        if (teacherService.updateById(teacher)) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PatchMapping("/teacher/{teacherNum}/resetPwd")
    public @ResponseBody
    ResponseEntity<Object> resetTeacherPassword(@PathVariable String teacherNum) {
        if (teacherService.resetPassword(teacherNum)) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @DeleteMapping("/teacher/{teacherNum}")
    public @ResponseBody
    ResponseEntity<Object> deleteTeacher(@PathVariable String teacherNum) {
        if (teacherService.delete(teacherNum)) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @DeleteMapping("/teacher")
    public @ResponseBody
    ResponseEntity<Object> deleteTeacher(@RequestBody String[] teacherNum) {
        for (String s : teacherNum) {
            if (!teacherService.delete(s)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(s);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/studentManage")
    public String studentManage() {
        return "admin/studentManage";
    }

    @PostMapping("/studentList")
    public String studentList(Model model, StudentFilter filter) {
        Student target = new Student().setStudentName(filter.getName()).setStudentNum(filter.getStudentNum());
        Page<Student> studentPage = studentService.getPage(target, new RowBounds(filter.getPage(), filter.getCount()));

        model.addAttribute("newFilter", filter.getNewFilter());
        model.addAttribute("fromIndex", studentPage.getStartRow());
        model.addAttribute("sumPage", studentPage.getPages());
        model.addAttribute("page", studentPage.getPageNum());
        model.addAttribute("students", studentPage);
        return "admin/studentList";
    }

    @PutMapping("/student")
    public @ResponseBody
    ResponseEntity<Object> addStudent(@RequestBody Student student) {
        student.setPassword(DEFAULT_PASSWORD);
        student.setActivated(false);
        // FIXME:前端待修复
        if (studentService.insert(student)) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PatchMapping("/student")
    public @ResponseBody
    ResponseEntity<Object> updateStudent(@RequestBody Student student) {
        // FIXME:前端待修复
        if (studentService.update(student)) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PatchMapping("/student/{studentNum}/resetPwd")
    public @ResponseBody
    ResponseEntity<Object> resetStudentPassword(@PathVariable String studentNum) {
        if (studentService.resetPassword(studentNum)) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    @DeleteMapping("/student/{studentNum}")
    public @ResponseBody
    ResponseEntity<Object> deleteStudent(@PathVariable String studentNum) {
        if (studentService.delete(studentNum)) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/student")
    public @ResponseBody
    ResponseEntity<Object> deleteStudent(@RequestBody String[] studentNum) {
        for (String s : studentNum) {
            if (!studentService.delete(s)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(s);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
