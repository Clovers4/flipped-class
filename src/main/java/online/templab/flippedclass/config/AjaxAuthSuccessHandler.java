package online.templab.flippedclass.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 验证通过的处理
 *
 * @author wk
 */
@Component
public class AjaxAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
        String authorities = auth.getPrincipal().toString();
        // FIXME 跳转不对
        System.out.println("Auth Success : " + authorities);

        PrintWriter writer = response.getWriter();
        writer.write(authorities);
        writer.close();

        response.setStatus(HttpServletResponse.SC_OK);
    }
}
