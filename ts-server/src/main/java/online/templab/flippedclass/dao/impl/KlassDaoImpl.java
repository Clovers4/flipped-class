package online.templab.flippedclass.dao.impl;

import online.templab.flippedclass.dao.KlassDao;
import online.templab.flippedclass.entity.*;
import online.templab.flippedclass.mapper.*;
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

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Boolean insert(Klass klass) {
        int line = 0;
        try {
            line = klassMapper.insert(klass);
            Course course = courseMapper.selectRoundListByCourseId(klass.getCourseId());
            System.out.println(course);
            List<Round> roundList = course.getRoundList();
            List<Seminar> seminarList = course.getSeminarList();
            for(int i = 0 ; i < roundList.size() ; ++i){
                klassRoundMapper.insertSelective(new KlassRound().setRoundId(roundList.get(i).getId()).setKlassId(klass.getId()).setEnrollLimit(1));
            }
            System.out.println("ll:"+seminarList);
            for(int i = 0 ; i < seminarList.size() ; ++i){
                klassSeminarMapper.insertSelective(new KlassSeminar()
                        .setKlassId(klass.getId())
                        .setSeminarId(seminarList.get(i).getId())
                        .setState(0)
                );
            }


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
    public List<Klass> selectByStudentId(Long studentId) {
        return klassMapper.listByStudentId(studentId);
    }
}
