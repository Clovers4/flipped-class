package online.templab.flippedclass.dao.impl;

import online.templab.flippedclass.dao.CourseDao;
import online.templab.flippedclass.entity.Course;
import online.templab.flippedclass.mapper.CourseMapper;
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
    public List<Course> selectByTeacherId(Long teacherId) {
        return courseMapper.select(new Course().setTeacherId(teacherId));
    }
}
