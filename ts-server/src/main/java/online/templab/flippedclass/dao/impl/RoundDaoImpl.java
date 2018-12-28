package online.templab.flippedclass.dao.impl;

import online.templab.flippedclass.dao.RoundDao;
import online.templab.flippedclass.entity.Course;
import online.templab.flippedclass.entity.Klass;
import online.templab.flippedclass.entity.KlassRound;
import online.templab.flippedclass.entity.Round;
import online.templab.flippedclass.mapper.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.LinkedList;
import java.util.List;

/**
 * Round Dao 实现类
 *
 * @author chenr
 */
@Mapper
@Component
public class RoundDaoImpl implements RoundDao {

    @Autowired
    RoundMapper roundMapper;

    @Autowired
    KlassRoundMapper klassRoundMapper;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    KlassMapper klassMapper;

    @Autowired
    SeminarMapper seminarMapper;

    @Override
    public int insert(Round round) {
        List<Course> courseList = courseMapper.selcetCourseSubCourseByCourseId(round.getCourseId());
        for(Course course: courseList){
            List<Klass> klassList = course.getKlassList();
            if(klassList != null){
                for(Klass klass : klassList){
                    int line = klassRoundMapper.insertSelective(new KlassRound().setKlassId(klass.getId()).setRoundId(round.getId()).setEnrollLimit(1));
                    if(line <= 0){
                        return 0;
                    }
                }
            }
        }

//        Course course = courseMapper.selcetByCourseId(round.getCourseId());
//        List<Klass> klassList = course.getKlassList();
//        if(klassList != null){
//            for(Klass klass : klassList){
//                int line = klassRoundMapper.insertSelective(new KlassRound().setKlassId(klass.getId()).setRoundId(round.getId()));
//                if(line <= 0){
//                    return 0;
//                }
//            }
//        }

        if(roundMapper.insertSelective(round)==1){
            return 1;
        }
        return 0;
    }

    @Override
    public int delete(Long roundId) {
        klassRoundMapper.delete(new KlassRound().setRoundId(roundId));
        Round round = roundMapper.selectByPrimaryKey(new Round().setId(roundId));
        roundMapper.updateRoundSerial(round.getCourseId(),(long)round.getRoundNum());
        return roundMapper.delete(new Round().setId(roundId));
    }

    @Override
    public int updateByRoundIdSelective(Round round) {
        List<KlassRound> klassRoundList = round.getKlassRounds();

        boolean success = true;
        if(klassRoundList != null) {
            for (KlassRound klassRound : klassRoundList) {
                int successLoop = klassRoundMapper.updateByRoundIdKlassIdSelective(klassRound);
                if (successLoop < 1) {
                    success = false;
                    break;
                }
            }
        }

        if(round.getCourseId()==null){
            if((roundMapper.updateByRoundIdSelective(round))==1 &&success==true){
                return 1;
            }
            return 0;
        }
        else{
            if(courseMapper.selectByPrimaryKey(new Course().setId(round.getCourseId())).getSeminarMainCourseId() ==null){
                if((roundMapper.updateByRoundIdSelective(round))==1 &&success==true){
                    return 1;
                }
                return 0;
            }
        }
        return 1;
    }

    @Override
    public int updateKlassRoundByPrimaryKey(KlassRound klassRound) {
        return klassRoundMapper.updateByRoundIdKlassIdSelective(klassRound);
    }

    @Override
    public List<Round> selectByCourseId(Long courseId) {
        Course course = courseMapper.selectByPrimaryKey(new Course().setId(courseId));
        if(course.getSeminarMainCourseId()!= null){
            course = courseMapper.selectByPrimaryKey(new Course().setId(course.getSeminarMainCourseId()));
        }
        return roundMapper.selectByCourseId(course.getId());
    }

    @Override
    public List<Round> selectByCourseIdKlassId(Long courseId, Long klassId) {

        Course course = courseMapper.selectByPrimaryKey(new Course().setId(courseId));
        if(course.getSeminarMainCourseId()!= null){
            course = courseMapper.selectByPrimaryKey(new Course().setId(course.getSeminarMainCourseId()));
        }

        List<Round> roundList = roundMapper.selectByCourseId(course.getId());
        for(int i = 0 ; i < roundList.size();++i){
            roundList.get(i).setKlassRounds(klassRoundMapper.selectByRoundIdKlassId(roundList.get(i).getId(),klassId));
        }
        return roundList;
    }

    @Override
    public int selectCount(Long couseId) {
        if(couseId != null){
            return roundMapper.selectCount(new Round().setCourseId(couseId));
        }
        else{
            return roundMapper.getMaxId();
        }


    }

    @Override
    public Round getOne(Long roundId, Long courseId) {
        Round round = roundMapper.getOne(roundId);
        round.setKlassRounds(klassRoundMapper.selectByRoundId(roundId, courseId));
        return round;
    }

    @Override
    public KlassRound getKlassRound(Long klassId, Long roundId) {
        return klassRoundMapper.selectByPrimaryKey(new KlassRound().setKlassId(klassId).setRoundId(roundId));
    }

    @Override
    public Long getRoundIdByKlassSeminarId(Long klassSeminarId) {
        return seminarMapper.selectRoundIdByKlassSeminarId(klassSeminarId).getRoundId();
    }


}
