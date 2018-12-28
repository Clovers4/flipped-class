package online.templab.flippedclass.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * @author wk
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "userDetailServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    private UnauthenticationEntryPoint unauthenticationEntryPoint;

    @Autowired
    private AjaxAuthSuccessHandler ajaxAuthSuccessHandler;

    @Autowired
    private AjaxAuthFailureHandler ajaxAuthFailureHandler;

    @Autowired
    private  MyAccessDeniedHandler myAccessDeniedHandler;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors();

        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/", "/login").permitAll()
                .antMatchers("/student/**").hasRole("student")
                .antMatchers("/teacher/**").hasRole("teacher");

        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("account")
                .passwordParameter("password")
                .successHandler(ajaxAuthSuccessHandler)
                .failureHandler(ajaxAuthFailureHandler)
                .permitAll();

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll();

        // 开启 Iframe
        http.headers().frameOptions().sameOrigin();

        http.exceptionHandling()
                .authenticationEntryPoint(unauthenticationEntryPoint)
                .accessDeniedHandler(myAccessDeniedHandler);
    }
}
