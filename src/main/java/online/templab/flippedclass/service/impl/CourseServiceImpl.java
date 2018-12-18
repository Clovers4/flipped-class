package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.dao.CourseDao;
import online.templab.flippedclass.entity.Course;
import online.templab.flippedclass.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Course 业务 实现类
 *
 * @author jh
 * @author fj
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
    public Boolean insert(Course course) {
        int line = 0;
        try {
            line = courseDao.insert(course);
        } catch (DuplicateKeyException e) {
            return false;
        }
        return line == 1;
    }

    @Override
    public Boolean update(Course course) {
        int line = courseDao.update(course);
        return line == 1;
    }

    @Override
    public List<Course> listByTeacherId(Long teacherId) {
        return courseDao.selectByTeacherId(teacherId);
    }

    @Override
    public List<Course> listByStudentId(Long studentId) {
        return courseDao.selectByStudentId(studentId);
    }

}
