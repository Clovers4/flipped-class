package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.dao.SeminarDao;
import online.templab.flippedclass.entity.KlassSeminar;
import online.templab.flippedclass.entity.Round;
import online.templab.flippedclass.entity.Seminar;
import online.templab.flippedclass.service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fj
 */
@Service
public class SeminarServiceImpl implements SeminarService {

    @Autowired
    SeminarDao seminarDao;

    @Override
    public Boolean insert(Seminar seminar) {
        return seminarDao.insert(seminar);
    }

    @Override
    public Boolean update(Seminar seminar) {
        return seminarDao.updateByPrimaryKey(seminar);
    }

    @Override
    public Boolean delete(Long id) {
        return seminarDao.deleteByPrimaryKey(id);
    }

    @Override
    public Integer getMaxSeminarSerialByCourseId(Long courseId) {
        return seminarDao.selectMaxSeminarSerialByCourseId(courseId);
    }

    @Override
    public Seminar get(Long id) {
        return seminarDao.selectByPrimaryKey(id);
    }
}
