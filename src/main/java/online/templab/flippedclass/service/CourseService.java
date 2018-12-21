package online.templab.flippedclass.service;

import online.templab.flippedclass.entity.Course;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据 courseId删除该课程和相关内容
     *
     * @param id
     * @return
     */
    Boolean delete(Long id);

    /**
     * 根据学生id返回课程和班级
     *
     * @param studentId
     * @return
     */
    List<Map<String,Object>> listCourseKlassByStudentId(Long studentId);

    /**
     * 根据老师id返回课程和班级
     *
     * @param teacherId
     * @return
     */
    List<Map<String,Object>> listCourseKlassByTeacherId(Long teacherId);
}
