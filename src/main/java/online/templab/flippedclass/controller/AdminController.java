package online.templab.flippedclass.controller;

import com.sun.deploy.net.HttpResponse;
import online.templab.flippedclass.dto.TeacherFilterDTO;
import online.templab.flippedclass.service.AdminService;
import online.templab.flippedclass.vo.TeacherPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
     * 登录
     *
     * @return
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseEntity<Object> login(@RequestParam String account, @RequestParam String password) {
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

    /**
     * 获取 教师列表页
     *
     * @param teacherFilterDTO
     * @return
     */
    @PostMapping(value = "/teacherList")
    @ResponseBody
    public TeacherPageVO teacherList(@RequestBody TeacherFilterDTO teacherFilterDTO) {
        // TODO
        return null;
    }

}
