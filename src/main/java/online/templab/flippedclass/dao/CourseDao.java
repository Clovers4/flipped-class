package online.templab.flippedclass.dao;

import online.templab.flippedclass.entity.Course;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jh
 */
public interface CourseDao {
    /**
     * 插入一门课程
     *
     * @param course
     * @return
     */
    int insert(Course course);

    /**
     * 删除有关的course
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
    int update(Course course);

    /**
     * 根据 id 查找该课程
     *
     * @param id
     * @return
     */
    Course selectOne(Long id);

    /**
     * 根据 teacherId 查找该 teacher 的所有 course
     *
     * @param teacherId
     * @return
     */
    List<Course> selectByTeacherId(Long teacherId);

    /**
     * 根据 studentId 查找该 student 的所有 course
     *
     * @param studentId
     * @return
     */
    List<Course> selectByStudentId(Long studentId);

    /**
     * 传入要查询的 courseId,返回与这个 course 共享分组的 course,并且这个 course 是主课程（也可能自己就是主课程）
     *
     * @param id
     * @return
     */
    Course selectShareTeamMainCourseByPrimaryKey(Long id);

    /**
     * 传入要查询的 courseId,返回与这个 course 共享讨论课的 course,并且这个 course 是主课程（也可能自己就是主课程）
     *
     * @param id
     * @return
     */
    Course selectShareSeminarMainCourseByPrimaryKey(Long id);

    /**
     * 传入要查询的 courseId,返回与这个 course 共享分组的 courses,并且这些 courses 是从课程（也可能自己就是从课程）
     *
     * @param id
     * @return
     */
    List<Course> selectShareTeamSubCourse(Long id);

    /**
     * 传入要查询的 courseId,返回与这个 course 共享讨论课的 courses,并且这些 courses 是从课程（也可能自己就是从课程）
     *
     * @param id
     * @return
     */
    List<Course> selectShareSeminarSubCourse(Long id);

    /**
     * 根据 studentId 查找该 student 的所有 course
     * course注入对应 student 的班级
     *
     * @param studentId
     * @return
     */
    List<Course> selectCourseKlassByStudentId(Long studentId);



}
