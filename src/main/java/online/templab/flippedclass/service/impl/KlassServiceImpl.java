package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.dao.KlassDao;
import online.templab.flippedclass.entity.Klass;
import online.templab.flippedclass.entity.Teacher;
import online.templab.flippedclass.mapper.KlassMapper;
import online.templab.flippedclass.service.KlassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Klass 业务 实现类
 *
 * @author jh
 */
@Service
public class KlassServiceImpl implements KlassService {

    @Autowired
    private KlassDao klassDao;

    @Override
    public Boolean insert(Klass klass) {
        return klassDao.insert(klass);
    }

    @Override
    public Boolean delete(Long id) {
        return klassDao.delete(id);
    }

    @Override
    public Boolean update(Klass klass) {
        return klassDao.update(klass);
    }

    @Override
    public List<Klass> listByCourseId(Long courseId) {
        return klassDao.selectByCourseId(courseId);
    }
}