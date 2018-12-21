package online.templab.flippedclass.dao.impl;

import online.templab.flippedclass.dao.KlassSeminarDao;
import online.templab.flippedclass.entity.KlassSeminar;
import online.templab.flippedclass.mapper.KlassSeminarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author fj
 */
@Component
public class KlassSeminarDaoImpl implements KlassSeminarDao {

    @Autowired
    KlassSeminarMapper klassSeminarMapper;

    @Override
    public Boolean insert(KlassSeminar klassSeminar) {
        int line = klassSeminarMapper.insert(klassSeminar);
        return line == 1;
    }
}
