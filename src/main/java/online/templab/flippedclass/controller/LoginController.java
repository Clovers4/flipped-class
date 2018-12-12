package online.templab.flippedclass.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 登录 API
 *
 * @author wk
 */
@Controller
@RequestMapping(value = "")
public class LoginController {

    /**
     * 默认页面，即老师/学生登录页面
     * 跳转至 老师/学生登录页面
     *
     * @return Page(" / login ")
     */
    @GetMapping(value = {"/", "/login"})
    public String login() {
        return "login";
    }

    /**
     * rest
     * 登录
     *
     * @return 认证成功：HTTPStatus(200 + authorities) ; 认证失败：HTTPStatus(401 + "")
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseEntity<Object> login(String account, String password) {
        // TODO: 请使用service
        System.out.println(account + "," + password);
//        if ("teacher1".equals(account)) {
            return ResponseEntity.status(HttpStatus.OK).body("[ROLE_teacher]");
//        }
//        if ("student1".equals(account)) {
//            return ResponseEntity.status(HttpStatus.OK).body("[ROLE_student]");
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    /**
     * 跳转至 忘记密码页面
     *
     * @return Page("/forgetPassword ")
     */
    @GetMapping(value = "/forgetPassword")
    public String forgetPassword() {
        return "forgetPassword";
    }

    /**
     * rest
     * 获取验证码
     *
     * @return 成功：HTTPStatus(200 + "") ; 找不到用户：HTTPStatus(204 + "")
     */
    @PostMapping(value = "/captcha/forgetPassword")
    @ResponseBody
    public ResponseEntity<Object> getCaptcha(String account) {
        // TODO:修正
        // TODO:测试

        System.out.println(account);
        if ("teacher".equals(account)) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    /**
     * rest
     * 忘记密码请求，也就是验证验证码的正确性
     *
     * @return 验证验证码成功：HTTPStatus(200 + "") ; 验证验证码失败：HTTPStatus(204 + "")
     */
    @PostMapping(value = "/forgetPassword")
    @ResponseBody
    public ResponseEntity<Object> getCaptcha(String account, String captcha) {
        // TODO
        if ("teacher".equals(account) && "123".equals(captcha)) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    /**
     * 跳转至 修改密码页面
     *
     * @return Page(" / modifyPassword ")
     */
    @GetMapping(value = "/modifyPassword")
    public String modifyPassword() {
        // TODO: 缺少 exception: HTTPStatus(403 + "")
        return "modifyPassword";
    }

    /**
     * rest
     * 修改密码请求
     * <br/>此处不给予前端填写account参数，防止通过忘记密码请求后通过篡改account来修改他人密码
     *
     * @return 成功：HTTPStatus(200 + "")
     */
    @PostMapping(value = "/modifyPassword")
    @ResponseBody
    public ResponseEntity<Object> modifyPassword(String password) {
        // TODO
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
