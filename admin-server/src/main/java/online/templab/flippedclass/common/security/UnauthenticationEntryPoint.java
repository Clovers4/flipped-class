package online.templab.flippedclass.common.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 解决匿名用户访问无权限资源时的异常
 *
 * @author wk
 */
@Component
public class UnauthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        String typeHeader = "X-Requested-With";
        String ajaxFlag = "XMLHttpRequest";
        String adminPrefix = "/admin";
        String url = request.getRequestURI();

        if (ajaxFlag.equals(request.getHeader(typeHeader))) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        } else if (url.startsWith(adminPrefix)) {
            response.sendRedirect("/admin/login");
        } else {
            response.sendRedirect("/login");
        }
    }
}
