package online.templab.flippedclass.controller;

import online.templab.flippedclass.dto.TeacherFilterDTO;
import online.templab.flippedclass.entity.Teacher;
import online.templab.flippedclass.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

/**
 * 管理员 Controller
 *
 * @author wk
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    // 普通行为--------------------------------------------------------------------------------------

    /**
     * 跳转至 管理员登录页面
     *
     * @return "admin/login"
     */
    @GetMapping(value = "/login")
    public String login() {
        return "admin/login";
    }

    /**
     * rest
     * 登录
     *
     * @return
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseEntity<Object> login(HttpServletResponse response, @RequestParam String account, @RequestParam String password) {
        if (adminService.login(account, password)) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    /**
     * 跳转至 管理员主页
     *
     * @return "admin/index"
     */
    @GetMapping(value = "/index")
    public String index() {
        return "admin/index";
    }


    // 教师管理--------------------------------------------------------------------------------------

    @GetMapping(value = "/teacherManage")
    public String teacherManage() {
        return "admin/teacherManage";
    }

    /**
     * 获取 教师列表页
     * 分页
     *
     * @param teacherFilterDTO
     * @return
     */
    @PostMapping(value = "/teacherList")
    public String teacherList(Model model, @RequestBody TeacherFilterDTO teacherFilterDTO) {
        // TODO: 假数据 -> 真据
        boolean newFilter = false;
        int fromIndex = 0;
        int sumPage = 0;
        int page = 0;
        List<Teacher> teachers = new LinkedList<>();

        model.addAttribute("newFilter", newFilter);
        model.addAttribute("fromIndex", fromIndex);
        model.addAttribute("sumPage", sumPage);
        model.addAttribute("page", page);
        model.addAttribute("teachers", teachers);

        return "admin/teacherList";
    }

    /**
     * rest
     * 创建教师
     */
    @PutMapping("/teacher")
    @ResponseBody
    public ResponseEntity<Object> createTeacher(@RequestBody Teacher teacher) {
        //TODO
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * rest
     * 删除教师
     *
     * @param teacherNum
     * @return
     */
    @DeleteMapping("/teacher/{teacherNum}")
    @ResponseBody
    public ResponseEntity<Object> deleteTeacher(@PathVariable("teacherNum") int teacherNum) {
        //TODO
        System.out.println(teacherNum);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * rest
     * 修改教师信息
     *
     * @param teacher
     * @return
     */
    @PatchMapping("/teacher")
    @ResponseBody
    public ResponseEntity<Object> updateTeacher(@RequestBody Teacher teacher) {
        //TODO
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * rest
     * 重置教师密码
     *
     * @param teacherNum
     * @return
     */
    @PostMapping("/teacher/{teacherNum}/resetPassword")
    @ResponseBody
    public ResponseEntity<Object> resetTeacherPassword(@PathVariable("teacherNum") int teacherNum) {
        //TODO
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    // 学生管理--------------------------------------------------------------------------------------

    /**
     * 跳转至学生管理
     *
     * @return
     */
    @GetMapping(value = "/studentManage")
    public String studentManage() {
        return "admin/studentManage";
    }

}
