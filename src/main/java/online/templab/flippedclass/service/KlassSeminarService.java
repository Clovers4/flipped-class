package online.templab.flippedclass.service;

import online.templab.flippedclass.entity.KlassSeminar;
import org.springframework.stereotype.Service;

/**
 * KlassSeminar 业务 接口类
 *
 * @author wk
 */
public interface KlassSeminarService {

    /**
     * 插入一个 klassSeminar
     *
     * @return
     */
    Boolean insert(KlassSeminar klassSeminar);

    /**
     * 根据 klassId 和 seminarId 获得一个 KlassSeminar
     *
     * @param klassId
     * @param seminarId
     * @return
     */
    KlassSeminar get(Long klassId, Long seminarId);
}
