package online.templab.flippedclass.dao;

import online.templab.flippedclass.entity.KlassSeminar;

/**
 * @author fj
 */
public interface KlassSeminarDao {

    /**
     * 插入一个 klassSeminar
     *
     * @param klassSeminar
     * @return
     */
    Boolean insert(KlassSeminar klassSeminar);
}
