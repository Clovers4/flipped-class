package online.templab.flippedclass.dao.impl;

import online.templab.flippedclass.dao.CourseDao;
import online.templab.flippedclass.entity.Course;
import online.templab.flippedclass.mapper.CourseMapper;
import online.templab.flippedclass.mapper.KlassStudentMapper;
import online.templab.flippedclass.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseDaoImpl implements CourseDao {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Course selectOne(Long id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(Course course) {
        return courseMapper.insertSelective(course);
    }

    @Override
    public int update(Course course) {
        return courseMapper.updateByPrimaryKeySelective(course);
    }

    @Override
    public List<Course> selectByTeacherId(Long teacherId) {
        return courseMapper.select(new Course().setTeacherId(teacherId));
    }

    @Override
    public List<Course> selectByStudentId(Long studentId) {
        return courseMapper.selectByStudentId(studentId);
    }

}
