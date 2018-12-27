package online.templab.flippedclass.service.impl;

import com.github.pagehelper.Page;
import online.templab.flippedclass.dao.TeacherDao;
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
    private TeacherDao teacherDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String DEFAULT_PASSWORD = "123456";

    @Override
    public Boolean insert(Teacher teacher) {
        // 对密码加密
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        int line = 0;
        try {
            line = teacherDao.insertSelective(teacher);
        } catch (DuplicateKeyException e) {
            return false;
        }
        return line == 1;
    }

    @Override
    public Boolean delete(String account) {
        int line = teacherDao.deleteByAccount(account);
        return line == 1;
    }

    @Override
    public Boolean updateById(Teacher teacher) {
        int line = teacherDao.updateByPrimaryKeySelective(teacher);
        return line == 1;
    }

    @Override
    public Boolean activate(Long id, String password, String email) {
        int line = teacherDao.updateByPrimaryKeySelective(
                new Teacher()
                        .setId(id)
                        .setPassword(passwordEncoder.encode(password))
                        .setEmail(email)
                        .setActivated(true)
        );
        return line == 1;
    }

    @Override
    public Boolean resetPassword(String account) {
        int line = teacherDao.updateByAccountSelective(
                new Teacher()
                        .setTeacherNum(account)
                        .setPassword(passwordEncoder.encode(DEFAULT_PASSWORD))
        );
        return line == 1;
    }

    @Override
    public Boolean modifyPassword(String teacherNum, String password) {
        int line = teacherDao.updateByAccountSelective(
                new Teacher()
                        .setTeacherNum(teacherNum)
                        .setPassword(passwordEncoder.encode(password))
        );
        return line == 1;
    }

    @Override
    public Page<Teacher> getPage(Teacher target, RowBounds rowBounds) {
        return (Page<Teacher>) teacherDao.selectByRowBounds(target, rowBounds);
    }

    @Override
    public Teacher getByTeacherNum(String teacherNum) {
        return teacherDao.selectByTeacherNum(teacherNum);
    }
}
