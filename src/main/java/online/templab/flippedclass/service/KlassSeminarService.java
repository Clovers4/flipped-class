package online.templab.flippedclass.service;

import online.templab.flippedclass.entity.KlassSeminar;

/**
 * KlassSeminar 业务 接口类
 *
 * @author wk
 */
public interface KlassSeminarService {

    /**
     * 根据 klassId 和 seminarId 获得一个 KlassSeminar
     *
     * @param klassId
     * @param seminarId
     * @return
     */
    KlassSeminar get(Long klassId, Long seminarId);
}
