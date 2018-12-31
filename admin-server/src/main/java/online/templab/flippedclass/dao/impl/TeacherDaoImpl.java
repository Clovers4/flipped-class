package online.templab.flippedclass.dao.impl;

import com.github.pagehelper.Page;
import online.templab.flippedclass.dao.TeacherDao;
import online.templab.flippedclass.entity.Teacher;
import online.templab.flippedclass.mapper.TeacherMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wk
 */
@Component
public class TeacherDaoImpl implements TeacherDao {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public int updateByAccountSelective(Teacher record) {
        return teacherMapper.updateByTeacherNumSelective(record);
    }

    @Override
    public int insertSelective(Teacher record) {
        return teacherMapper.insertSelective(record);
    }

    @Override
    public int deleteByAccount(String account) {
        return teacherMapper.delete(new Teacher().setTeacherNum(account));
    }

    @Override
    public int updateByPrimaryKeySelective(Teacher record) {
        return teacherMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Teacher selectByPrimaryKey(Object key) {
        return teacherMapper.selectByPrimaryKey(key);
    }

    @Override
    public Teacher selectByTeacherNum(String teacherNum) {
        return teacherMapper.selectOne(new Teacher().setTeacherNum(teacherNum));
    }

    @Override
    public List<Teacher> select(Teacher record) {
        return teacherMapper.select(record);
    }

    @Override
    public Page<Teacher> selectByRowBounds(Teacher record, RowBounds rowBounds) {
        return (Page<Teacher>) teacherMapper.selectByRowBounds(record, rowBounds);
    }
}
