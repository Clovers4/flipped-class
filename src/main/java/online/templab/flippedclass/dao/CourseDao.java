package online.templab.flippedclass.dao;

import online.templab.flippedclass.entity.Course;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jh
 */
@Component
public interface CourseDao {
    /**
     * 根据 id 查找该课程
     *
     * @param id
     * @return
     */
    Course selectOne(Long id);

    /**
     * 插入一门课程
     *
     * @param course
     * @return
     */
    int insert(Course course);

    /**
     * 更新一门课程
     *
     * @param course
     * @return
     */
    int update(Course course);

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
}
