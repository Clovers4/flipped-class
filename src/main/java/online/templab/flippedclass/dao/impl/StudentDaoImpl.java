package online.templab.flippedclass.dao.impl;

import com.github.pagehelper.Page;
import online.templab.flippedclass.dao.StudentDao;
import online.templab.flippedclass.entity.Student;
import online.templab.flippedclass.mapper.StudentMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author fj
 */
@Component
public class StudentDaoImpl implements StudentDao {
    @Autowired
    StudentMapper studentMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public int insert(Student student) {
        return studentMapper.insertSelective(student);
    }

    @Override
    public int deleteByAccount(String account) {
        return studentMapper.delete(new Student().setAccount(account));
    }

    @Override
    public int updateByPrimaryKeySelective(Student student) {
        return studentMapper.updateByPrimaryKeySelective(student);
    }

    @Override
    public int updateByAccountSelective(Student student) {
        return studentMapper.updateByAccountSelective(student);
    }

    @Override
    public Page<Student> getPage(RowBounds rowBounds) {
        return (Page<Student>) studentMapper.selectByRowBounds(new Student(), rowBounds);
    }
}
