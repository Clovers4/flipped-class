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
     * 根据 studentId 查找该 student 的所有 course
     * course注入对应 student 的班级
     *
     * @param studentId
     * @return
     */
    List<Course> selectCourseKlassByStudentId(Long studentId);

    /**
     * 通过当前coreseId找到其它所有课程
     * @param courseId
     * @return
     */
    List<Course> selectOtherCourse(Long courseId);


    /**
     * 根据 courseId 查看该课程可以共享给的课程list
     * 0 是 分组 ， 1 是 讨论课
     *
     * @param id
     * @param type
     * @return
     */
    List<Course> selectCanShareCourseByPrimaryKey(Long id, int type);

    /**
     * 根据 teamId 查到截止日期
     *
     * @param teamId
     * @return
     */
    Course selectDateByTeamId(Long teamId);

    /**
     * 获取表里所有课程
     *
     * @return
     */
    List<Course> selectAll();
}
