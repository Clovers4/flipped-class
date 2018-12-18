package online.templab.flippedclass.dao.impl;

import online.templab.flippedclass.dao.KlassDao;
import online.templab.flippedclass.entity.Klass;
import online.templab.flippedclass.mapper.KlassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class KlassDaoImpl implements KlassDao {

    @Autowired
    private KlassMapper klassMapper;

    @Override
    public Boolean insert(Klass klass) {
        int line=0;
        try {
            klassMapper.insert(klass);
        }
        catch (DuplicateKeyException e){
            return false;
        }
        return line==1;
    }

    @Override
    public Boolean delete(Long id) {
        int line=klassMapper.deleteByPrimaryKey(id);
        return line==1;
    }

    @Override
    public Boolean update(Klass klass) {
        int line=klassMapper.updateByPrimaryKeySelective(klass);
        return line==1;
    }

    @Override
    public List<Klass> selectByCourseId(Long courseId) {
        return klassMapper.select(new Klass().setCourseId(courseId));
    }
}
