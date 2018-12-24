package online.templab.flippedclass.service;

import online.templab.flippedclass.entity.Course;

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
     * 传入要查询的 courseId,返回与这个 course 共享分组的 course,并且这个 course 是主课程（也可能自己就是主课程）
     *
     * @param id 即要查询的courseId
     * @return
     */
    Course getShareTeamMainCourse(Long id);

    /**
     * 传入要查询的 courseId,返回与这个 course 共享讨论课的 course,并且这个 course 是主课程（也可能自己就是主课程）
     *
     * @param id
     * @return
     */
    Course getShareSeminarMainCourse(Long id);

    /**
     * 传入要查询的 courseId,返回与这个 course 共享分组的 courses,并且这些 courses 是从课程（也可能自己就是从课程）
     *
     * @param id
     * @return
     */
    List<Course> listShareTeamSubCourse(Long id);

    /**
     * 传入要查询的 courseId,返回与这个 course 共享讨论课的 courses,并且这些 courses 是从课程（也可能自己就是从课程）
     *
     * @param id
     * @return
     */
    List<Course> listShareSeminarSubCourse(Long id);

    /**
     * 根据 courseId 查看该课程可以共享给的课程list
     *
     * @param id
     * @param type ( 0代表讨论课，1代表组队)
     * @return
     */
    List<Course> listCanShareCourses(Long id, int type);

}
