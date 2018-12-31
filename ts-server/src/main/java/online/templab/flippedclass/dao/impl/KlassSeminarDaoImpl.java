package online.templab.flippedclass.dao.impl;

import online.templab.flippedclass.dao.KlassSeminarDao;
import online.templab.flippedclass.entity.Attendance;
import online.templab.flippedclass.entity.KlassSeminar;
import online.templab.flippedclass.entity.Seminar;
import online.templab.flippedclass.mapper.AttendanceMapper;
import online.templab.flippedclass.mapper.KlassSeminarMapper;
import online.templab.flippedclass.mapper.SeminarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author fj
 */
@Component
public class KlassSeminarDaoImpl implements KlassSeminarDao {

    @Autowired
    KlassSeminarMapper klassSeminarMapper;

    @Autowired
    SeminarMapper seminarMapper;

    @Autowired
    AttendanceMapper attendanceMapper;

    @Override
    public Boolean insert(KlassSeminar klassSeminar) {
        int line = klassSeminarMapper.insert(klassSeminar);
        return line == 1;
    }

    @Override
    public KlassSeminar selectOne(Long klassId, Long seminarId) {
        return klassSeminarMapper.selectOneByKlassIdSeminarId(klassId,seminarId);

    }

    @Override
    public Boolean delete(Long id) {
        attendanceMapper.delete(new Attendance().setKlassSeminarId(id));
        return klassSeminarMapper.deleteByPrimaryKey(id)==1;
    }

    @Override
    public KlassSeminar selectByPrimaryKey(Long id) {
        return klassSeminarMapper.selectByPrimaryKey(new KlassSeminar().setId(id));
    }


}
