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
        int line = studentDao.delete(account);
        return line == 1;
    }

    @Override
    public Boolean update(Student student) {
        int line = studentDao.update(student);
        return line == 1;
    }

    @Override
    public Boolean activate(Long id, String password, String email) {
        int line = studentDao.activate(id,password,email);
        return line == 1;
    }

    @Override
    public Boolean resetPassword(String account, String password) {
        int line = studentDao.resetPassword(account,password);
        return line == 1;
    }

    @Override
    public Boolean modifyPassword(Long id, String password) {
        int line =studentDao.modifyPassword(id,password);
        return line == 1;
    }

    @Override
    public Page<Student> getPage(RowBounds rowBounds) {
        return studentDao.getPage(rowBounds);
    }
}
