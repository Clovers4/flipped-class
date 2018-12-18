package online.templab.flippedclass.service;

import online.templab.flippedclass.entity.Course;

import java.util.List;

/**
 * Course 业务 接口类
 *
 * @author wk
 * @author fj
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
     * 插入一门课程
     *
     * @param course
     * @return
     */
    Boolean insert(Course course);

    /**
     * 根据 teacherId 查找该 teacher 的所有 course
     *
     * @param teacherId
     * @return
     */
    List<Course> listByTeacherId(Long teacherId);

    /**
     * 更新一门课程
     *
     * @param course
     * @return
     */
    Boolean update(Course course);

    /**
     * 根据 studentId 查找该 student 的所有 course
     *
     * @param studentId
     * @return
     */
    List<Course> listByStudentId(Long studentId);
}
