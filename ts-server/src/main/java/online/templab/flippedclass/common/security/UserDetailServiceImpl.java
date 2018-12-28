package online.templab.flippedclass.common.security;

import online.templab.flippedclass.entity.Student;
import online.templab.flippedclass.entity.Teacher;
import online.templab.flippedclass.mapper.StudentMapper;
import online.templab.flippedclass.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义的UserDetailsService，用于判断用户是否登录成功。
 * <br/>若成功，则置入ROLE_XXX，生成User，跳转AjaxAuthSuccessHandler
 * <br/>若失败，则跳转跳转AjaxAuthFailureHandler
 *
 * @author wk
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserWithRole userWithRole = loadUserWithRole(username);

        if (userWithRole == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userWithRole.getRole()));

        return new User(userWithRole.getUsername(), userWithRole.getPassword(), authorities);
    }

    private UserWithRole loadUserWithRole(String username) {
        UserWithRole userWithRole  = loadTeacher(username);

        // teacher中没有该账号
        if (userWithRole == null) {
            userWithRole = loadStudent(username);
        }

        return userWithRole;
    }

    private UserWithRole loadTeacher(String username) {
        Teacher teacher = teacherMapper.selectOne(new Teacher().setTeacherNum(username));
        if (teacher == null) {
            return null;
        }

        UserWithRole userWithRole = new UserWithRole()
                .setUsername(teacher.getTeacherNum())
                .setPassword(teacher.getPassword())
                .setRole("ROLE_teacher");
        return userWithRole;
    }

    private UserWithRole loadStudent(String username) {
        Student student = studentMapper.selectOne(new Student().setStudentNum(username));
        if (student == null) {
            return null;
        }

        UserWithRole userWithRole = new UserWithRole()
                .setUsername(student.getStudentNum())
                .setPassword(student.getPassword())
                .setRole("ROLE_student");
        return userWithRole;
    }

}
