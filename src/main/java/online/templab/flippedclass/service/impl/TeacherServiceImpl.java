package online.templab.flippedclass.service.impl;

import com.github.pagehelper.Page;
import online.templab.flippedclass.entity.Teacher;
import online.templab.flippedclass.mapper.TeacherMapper;
import online.templab.flippedclass.service.TeacherService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Teacher 业务 实现类
 *
 * @author wk
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String DEFAULT_PASSWORD = "123456";

    @Override
    public Boolean insert(Teacher teacher) {
        // 对密码加密
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        int line = 0;
        try {
            line = teacherMapper.insertSelective(teacher);
        } catch (DuplicateKeyException e) {
            return false;
        }
        return line == 1;
    }

    @Override
    public Boolean delete(String account) {
        int line = teacherMapper.delete(new Teacher().setAccount(account));
        return line == 1;
    }

    @Override
    public Boolean update(Teacher teacher) {
        int line = teacherMapper.updateByPrimaryKeySelective(teacher);
        return line == 1;
    }

    @Override
    public Boolean activate(Long id, String password, String email) {
        int line = teacherMapper.updateByPrimaryKeySelective(
                new Teacher()
                        .setId(id)
                        .setPassword(password)
                        .setEmail(email)
                        .setActive(true)
        );
        return line == 1;
    }

    @Override
    public Boolean resetPassword(String account) {
        int line = teacherMapper.updateByAccountSelective(
                new Teacher()
                        .setAccount(account)
                        .setPassword(passwordEncoder.encode(DEFAULT_PASSWORD))
        );
        return line == 1;
    }

    @Override
    public Boolean modifyPassword(Long id, String password) {
        int line = teacherMapper.updateByAccountSelective(
                new Teacher()
                        .setId(id)
                        .setPassword(passwordEncoder.encode(password))
        );
        return line == 1;
    }

    @Override
    public Page<Teacher> getPage(RowBounds rowBounds) {
        return (Page<Teacher>) teacherMapper.selectByRowBounds(new Teacher(), rowBounds);
    }
}
