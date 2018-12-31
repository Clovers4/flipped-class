package online.templab.flippedclass.service;

import online.templab.flippedclass.entity.Course;
import online.templab.flippedclass.entity.TeamStrategy;

import java.util.List;
import java.util.Map;

/**
 * Course 业务 接口类
 *
 * @author wk
 * @author fj
 * @author chenr listCourseKlassByStudentId
 */
public interface CourseService {

    /**
     * 插入一门课程
     *
     * @param course
     * @return
     */
    Boolean insert(Course course);

    /**
     * 根据 courseId删除该课程和相关内容
     *
     * @param id
     * @return
     */
    Boolean delete(Long id);

    /**
     * 更新一门课程
     *
     * @param course
     * @return
     */
    Boolean update(Course course);

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



    /**
     * 根据 studentId 查找该 student 的所有 course
     *
     * @param studentId
     * @return
     */
    List<Course> listByStudentId(Long studentId);


    /**
     * 根据学生id返回课程和班级
     *
     * @param studentId
     * @return
     */
    List<Map<String,Object>> listCourseKlassByStudentId(Long studentId);

    /**
     * 通过当前coreseId找到其它所有课程
     *
     * @param courseId
     * @return
     */
    List<Course> listOtherCourse(Long courseId);

    /**
     * 根据 courseId 查看该课程可以共享给的课程list
     * 0 是 分組 ， 1 是 讨论课
     *
     * @param id
     * @param type
     * @return
     */
    List<Course> listCanShareCourseByPrimaryKey(Long id,int type);

    /**
     * 获取所有课程
     *
     * @return
     */
    List<Course> listAllCourse();
}
