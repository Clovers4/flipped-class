package online.templab.flippedclass.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证失败的处理
 *
 * @author wk
 */
@Component
public class AjaxAuthFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        //TODO
        System.out.println("Auth Failure : " + e.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "验证失败");
    }
}
