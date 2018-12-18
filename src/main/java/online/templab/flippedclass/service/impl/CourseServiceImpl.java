package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.dao.CourseDao;
import online.templab.flippedclass.entity.Course;
import online.templab.flippedclass.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Course 业务 实现类
 *
 * @author jh
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Override
    public Course get(Long id) {
        return courseDao.selectOne(id);
    }

    @Override
    public List<Course> listByTeacherId(Long teacherId) {
        return courseDao.selectByTeacherId(teacherId);
    }
}
