package online.templab.flippedclass.dao.impl;

import online.templab.flippedclass.dao.SeminarDao;
import online.templab.flippedclass.entity.Course;
import online.templab.flippedclass.entity.KlassSeminar;
import online.templab.flippedclass.entity.Round;
import online.templab.flippedclass.entity.Seminar;
import online.templab.flippedclass.mapper.CourseMapper;
import online.templab.flippedclass.mapper.KlassMapper;
import online.templab.flippedclass.mapper.KlassSeminarMapper;
import online.templab.flippedclass.mapper.SeminarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author fj
 */
@Component
public class SeminarDaoImpl implements SeminarDao {

    @Autowired
    SeminarMapper seminarMapper;

    @Autowired
    KlassSeminarMapper klassSeminarMapper;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    KlassMapper klassMapper;

    @Override
    public Boolean insert(Seminar seminar) {
        //考虑从课程的关系创建
        List<Course> courses=courseMapper.select(new Course().setSeminarMainCourseId(seminar.getCourseId()));
        if(!courses.isEmpty()){
            for(Course course:courses){
                List<Long> klassIds=klassMapper.selectIdByCourseId(course.getId());
                for(Long klassId:klassIds){
                    klassSeminarMapper.insert(new KlassSeminar()
                            .setKlassId(klassId)
                            .setSeminarId(seminar.getId())
                            .setState(0));
                }
            }
        }
        //主课程的关系创建
        if (seminar.getRoundId() != null) {
            int line = seminarMapper.insert(seminar);
            List<Long> klassIds=klassMapper.selectIdByCourseId(seminar.getCourseId());
            for(Long klassId:klassIds){
                klassSeminarMapper.insert(new KlassSeminar()
                        .setKlassId(klassId)
                        .setSeminarId(seminar.getId())
                        .setState(0));
            }
            return line == 1;
        } else {
            // TODO
            // 对于 roundId为空情况 需要创建一个round 这里待完成
            return true;
        }
    }

    @Override
    public Boolean updateByPrimaryKey(Seminar seminar){
        int line = seminarMapper.updateByPrimaryKey(seminar);
        return line == 1;
    }

    @Override
    public Boolean deleteByPrimaryKey(Long id){
        int deleteSeminar = seminarMapper.deleteByPrimaryKey(id);
        int deleteSeminarKlass = klassSeminarMapper.delete(new KlassSeminar().setSeminarId(id));
        return (deleteSeminar*deleteSeminarKlass)>0;
    }

    @Override
    public Seminar selectByPrimaryKey(Long id){
        return seminarMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer selectMaxSeminarSerialByCourseId(Long courseId){
        return seminarMapper.selectMaxSeminarSerialByCourseId(courseId);
    }
}
