package online.templab.flippedclass.dao.impl;

import com.github.pagehelper.Page;
import online.templab.flippedclass.dao.StudentDao;
import online.templab.flippedclass.entity.Student;
import online.templab.flippedclass.mapper.StudentMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
        return studentMapper.insertSelective(student.setPassword("123456").setActivated(false));
    }

    @Override
    public int deleteByStudentNum(String studentNum) {
        return studentMapper.delete(new Student().setStudentNum(studentNum));
    }

    @Override
    public int updateByPrimaryKeySelective(Student student) {
        return studentMapper.updateByPrimaryKeySelective(student);
    }

    @Override
    public int updateByStudentNumSelective(Student student) {
        return studentMapper.updateByStudentNumSelective(student);
    }

    @Override
    public Page<Student> selectByRowBounds(Student record, RowBounds rowBounds) {
        return (Page<Student>) studentMapper.selectByRowBounds(record, rowBounds);
    }

    @Override
    public Student selectByStudentNum(String studentNum){
        return studentMapper.selectOne(new Student().setStudentNum(studentNum));
    }


    @Override
    public Student selectByPrimaryKey(Long id) {
        return studentMapper.selectByPrimaryKey(id);
    }
}
