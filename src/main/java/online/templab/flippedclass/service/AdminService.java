package online.templab.flippedclass.service;

import org.springframework.stereotype.Service;

/**
 * 管理员 业务 接口类
 *
 * @author wk
 */
@Service
public class AdminService {

    /**
     * 通过账号密码判断是否登录成功
     *
     * @param account
     * @param password
     * @return
     */
    public boolean login(String account, String password) {
        // TODO
        if ("123".equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}
