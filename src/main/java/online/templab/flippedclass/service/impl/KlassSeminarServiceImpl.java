package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.dao.KlassSeminarDao;
import online.templab.flippedclass.entity.KlassSeminar;
import online.templab.flippedclass.service.KlassSeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fj
 */
@Service
public class KlassSeminarServiceImpl implements KlassSeminarService {

    @Autowired
    KlassSeminarDao klassSeminarDao;

    @Override
    public Boolean insert(KlassSeminar klassSeminar) {
        return klassSeminarDao.insert(klassSeminar);
    }

    @Override
    public KlassSeminar get(Long klassId, Long seminarId) {
        return null;
    }
}
