package online.templab.flippedclass.dao;

import online.templab.flippedclass.entity.Klass;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * klass dao层接口
 * @author jh
 */
@Component
public interface KlassDao {
    /**
     * 插入一个 Klass
     *
     * @param klass
     * @return 成功则返回 true ,失败则返回 false
     */
    Boolean insert(Klass klass);

    /**
     * 删除某个 id 对应的课程
     *
     * @param id
     * @return 成功则返回 true ,失败则返回 false
     */
    Boolean delete(Long id);

    /**
     * 根据 id 更新一个 Klass
     *
     * @param klass
     * @return 成功则返回 true ,失败则返回 false
     */
    Boolean update(Klass klass);

    /**
     * 根据 courseId 查找该 course 的所有 klass
     *
     * @param courseId
     * @return
     */
    List<Klass> selectByCourseId(Long courseId);
}
