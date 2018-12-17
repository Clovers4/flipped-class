package online.templab.flippedclass.service;

import online.templab.flippedclass.entity.Klass;

import java.util.List;

/**
 * Klass 业务 接口类
 *
 * @author wk
 */
public interface KlassService {

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
    List<Klass> listByCourseId(Long courseId);


}
