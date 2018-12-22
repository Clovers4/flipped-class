package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.dao.CourseDao;
import online.templab.flippedclass.entity.Course;
import online.templab.flippedclass.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public Boolean delete(Long id) {
        return courseDao.delete(id);
    }

    @Override
    public List<Map<String, Object>> listCourseKlassByStudentId(Long studentId) {
        //TODO
        return null;
    }

    @Override
    public List<Map<String, Object>> listCourseKlassByTeacherId(Long teacherId) {
        //TODO
        return null;
    }

    @Override
    public Course getShareTeamMainCourse(Long id){
        return courseDao.selectShareTeamMainCourseByPrimaryKey(id);
    }

    @Override
    public Course getShareSeminarMainCourse(Long id){
        return courseDao.selectShareSeminarMainCourseByPrimaryKey(id);
    }

    @Override
    public List<Course> listShareTeamSubCourse(Long id){
        return courseDao.selectShareTeamSubCourse(id);
    }

    @Override
    public List<Course> listShareSeminarSubCourse(Long id){
        return courseDao.selectShareSeminarSubCourse(id);
    }
}
