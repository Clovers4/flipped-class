package online.templab.flippedclass.service;

import online.templab.flippedclass.entity.Course;

import java.util.List;

/**
 * Course 业务 接口类
 *
 * @author wk
 */
public interface CourseService {

    /**
     * 根据 id 查找该课程
     *
     * @param id
     * @return
     */
    Course get(Long id);

    /**
     * 根据 teacherId 查找该 teacher 的所有 course
     *
     * @param teacherId
     * @return
     */
    List<Course> listByTeacherId(Long teacherId);
}
