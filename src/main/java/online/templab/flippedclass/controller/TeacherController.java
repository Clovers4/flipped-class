package online.templab.flippedclass.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 教师 API
 *
 * @author wk
 */
@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {

    /**
     * 跳转至 教师主页
     *
     * @return
     */
    @GetMapping(value = "/index")
    public String index() {
        return "teacher/index";
    }
}
