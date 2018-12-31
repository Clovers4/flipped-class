package online.templab.flippedclass.dao;

import online.templab.flippedclass.entity.KlassSeminar;

/**
 * @author fj
 * @author jh
 */
public interface KlassSeminarDao {

    /**
     * 插入一个 klassSeminar
     *
     * @param klassSeminar
     * @return
     */
    Boolean insert(KlassSeminar klassSeminar);

    /**
     * 通过klass和seminar得到klassSeminar
     *
     * @param klassId
     * @param seminarId
     * @return
     */
    KlassSeminar selectOne(Long klassId, Long seminarId);

    /**
     * 删除klassSeminar
     * 一般是在取消共享时删除从课程的klassSeminar
     *
     * @param id
     * @return
     */
    Boolean delete(Long id);

    /**
     * 根据 id  获取klassSeminar
     *
     * @param id
     * @return
     */
    KlassSeminar selectByPrimaryKey(Long id);
}
