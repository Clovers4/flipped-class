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

import java.util.List;

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
    public Boolean delete(String studentNum) {
        int line = studentDao.deleteByStudentNum(studentNum);
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
                        .setPassword(passwordEncoder.encode(password))
                        .setActivated(true)
                        .setEmail(email)
        );
        return line == 1;
    }

    @Override
    public Student getByPrimaryKey(Long id) {
        return studentDao.selectByPrimaryKey(id);
    }

    @Override
    public Boolean resetPassword(String studentNum) {
        int line = studentDao.updateByStudentNumSelective(
                new Student()
                        .setStudentNum(studentNum)
                        .setPassword(passwordEncoder.encode(DEFAULT_PASSWORD))
        );
        return line == 1;
    }

    @Override
    public Boolean modifyPassword(String studentNum, String password) {
        int line = studentDao.updateByStudentNumSelective(
                new Student()
                        .setStudentNum(studentNum)
                        .setPassword(passwordEncoder.encode(password))
        );
        return line == 1;
    }

    @Override
    public Page<Student> getPage(Student target, RowBounds rowBounds) {
        return studentDao.selectByRowBounds(target, rowBounds);
    }

    @Override
    public Student getByStudentNum(String studentNum) {
        return studentDao.selectByStudentNum(studentNum);
    }

}
