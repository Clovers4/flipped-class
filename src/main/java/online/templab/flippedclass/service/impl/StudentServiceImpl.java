package online.templab.flippedclass.service.impl;

import com.github.pagehelper.Page;
import online.templab.flippedclass.dao.StudentDao;
import online.templab.flippedclass.entity.Student;
import online.templab.flippedclass.service.StudentService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author fj
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String DEFAULT_PASSWORD = "123456";

    @Override
    public Boolean insert(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        int line = 0;
        try {
            line = studentDao.insert(student);
        } catch (DuplicateKeyException e) {
            return false;
        }
        return line == 1;
    }

    @Override
    public Boolean delete(String account) {
        int line = studentDao.deleteByAccount(account);
        return line == 1;
    }

    @Override
    public Boolean update(Student student) {
        int line = studentDao.updateByPrimaryKeySelective(student);
        return line == 1;
    }

    @Override
    public Boolean activate(Long id, String password, String email) {
        int line = studentDao.updateByPrimaryKeySelective(
                new Student()
                        .setId(id)
                        .setPassword(password)
                        .setActive(true)
                        .setEmail(email)
        );
        return line == 1;
    }

    @Override
    public Boolean resetPassword(String account) {
        int line = studentDao.updateByAccountSelective(
                new Student()
                        .setAccount(account)
                        .setPassword(DEFAULT_PASSWORD)
        );
        return line == 1;
    }

    @Override
    public Boolean modifyPassword(Long id, String password) {
        int line = studentDao.updateByPrimaryKeySelective(
                new Student()
                        .setId(id)
                        .setPassword(password)
        );
        return line == 1;
    }

    @Override
    public Page<Student> getPage(RowBounds rowBounds) {
        return studentDao.getPage(rowBounds);
    }
}
