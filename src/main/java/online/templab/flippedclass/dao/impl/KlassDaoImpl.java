package online.templab.flippedclass.dao.impl;

import online.templab.flippedclass.dao.KlassDao;
import online.templab.flippedclass.entity.*;
import online.templab.flippedclass.mapper.KlassMapper;
import online.templab.flippedclass.mapper.KlassRoundMapper;
import online.templab.flippedclass.mapper.KlassSeminarMapper;
import online.templab.flippedclass.mapper.KlassStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class KlassDaoImpl implements KlassDao {

    @Autowired
    private KlassMapper klassMapper;

    @Autowired
    private KlassStudentMapper klassStudentMapper;

    @Autowired
    private KlassRoundMapper klassRoundMapper;

    @Autowired
    private KlassSeminarMapper klassSeminarMapper;

    @Override
    public Boolean insert(Klass klass) {
        int line = 0;
        try {
            klassMapper.insert(klass);
        } catch (DuplicateKeyException e) {
            return false;
        }
        return line == 1;
    }

    @Override
    public Boolean delete(Long id) {
        klassStudentMapper.delete(new KlassStudent().setKlassId(id));
        klassRoundMapper.delete(new KlassRound().setKlassId(id));
        klassSeminarMapper.delete(new KlassSeminar().setKlassId(id));
        int line = klassMapper.deleteByPrimaryKey(id);
        return line == 1;
    }

    @Override
    public Boolean update(Klass klass) {
        int line = klassMapper.updateByPrimaryKeySelective(klass);
        return line == 1;
    }

    @Override
    public List<Klass> selectByCourseId(Long courseId) {
        return klassMapper.select(new Klass().setCourseId(courseId));
    }

    @Override
    public Klass select(Long id) {
        return klassMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean intsertList(Long id, List<Student> students) {
        klassStudentMapper.delete(new KlassStudent().setKlassId(id));
        List<Long> studentIds = new LinkedList<>();
        for (Student student : students) {
            studentIds.add(student.getId());
        }
        Long courseId = klassMapper.selectByPrimaryKey(id).getCourseId();
        int line = klassStudentMapper.insertList(courseId, id, null, studentIds);
        return line==students.size();
    }

}
