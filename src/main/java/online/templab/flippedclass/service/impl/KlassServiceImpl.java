package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.entity.Klass;
import online.templab.flippedclass.entity.Teacher;
import online.templab.flippedclass.mapper.KlassMapper;
import online.templab.flippedclass.service.KlassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KlassServiceImpl implements KlassService {

    @Autowired
    KlassDao klassDao;

    @Override
    public Boolean insert(Klass klass) {
        klassMa
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public Boolean update(Klass klass) {
        return null;
    }

    @Override
    public List<Klass> listByCourseId(Long courseId) {
        return null;
    }
}
