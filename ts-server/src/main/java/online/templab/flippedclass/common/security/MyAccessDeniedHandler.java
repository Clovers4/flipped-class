package online.templab.flippedclass.common.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限不足处理器
 *
 * @author wk
 */
@Slf4j
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        String typeHeader = "X-Requested-With";
        String ajaxFlag = "XMLHttpRequest";
        String adminPrefix = "/admin";
        String url = request.getRequestURI();

        log.info("Access Denied !");

        if (ajaxFlag.equals(request.getHeader(typeHeader))) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        } else if (url.startsWith(adminPrefix)) {
            response.sendRedirect("/admin/login");
        } else {
            response.sendRedirect("/login");
        }
    }
}
