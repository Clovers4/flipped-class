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
     * 根据 teacherId 查找该 teacher 的所有 course
     *
     * @param teacherId
     * @return
     */
    List<Course> selectByTeacherId(Long teacherId);
}
