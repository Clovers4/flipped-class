package online.templab.flippedclass.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 学生 API
 *
 * @author wk
 */
@Controller
@RequestMapping(value = "/student")
public class StudentController {

    /**
     * 跳转至 学生主页
     *
     * @return
     */
    @GetMapping(value = "/index")
    public String index() {
        return "student/index";
    }

}
