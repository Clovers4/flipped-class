package online.templab.flippedclass.dao.impl;

import online.templab.flippedclass.dao.CourseDao;
import online.templab.flippedclass.entity.*;
import online.templab.flippedclass.mapper.*;
import online.templab.flippedclass.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Component
public class CourseDaoImpl implements CourseDao {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private KlassStudentMapper klassStudentMapper;

    @Autowired
    private KlassMapper klassMapper;

    @Autowired
    private KlassRoundMapper klassRoundMapper;

    @Autowired
    private KlassSeminarMapper klassSeminarMapper;

    @Autowired
    private ShareSeminarApplicationMapper shareSeminarApplicationMapper;

    @Autowired
    private  ShareTeamApplicationMapper shareTeamApplicationMapper;

    @Autowired
    private ConflictCourseStrategyMapper conflictCourseStrategyMapper;

    @Override
    public int insert(Course course) {
        return courseMapper.insertSelective(course);
    }

    @Override
    public int update(Course course) {
        return courseMapper.updateByPrimaryKeySelective(course);
    }

    @Override
    public Boolean delete(Long id) {
        //删除和klass相关
        List<Long> klasseIds=klassMapper.selectIdByCourseId(id);
        for(Long klassId:klasseIds)
        {
            klassStudentMapper.delete(new KlassStudent().setKlassId(klassId));
            klassRoundMapper.delete(new KlassRound().setKlassId(klassId));
            klassSeminarMapper.delete(new KlassSeminar().setKlassId(klassId));
            klassMapper.deleteByPrimaryKey(klassId);
        }
        //删除和共享相关
        System.out.println(id);
        Example exampleSeminar=new Example(ShareSeminarApplication.class);
        exampleSeminar.createCriteria().andCondition("main_course_id =",id);
        exampleSeminar.or().andCondition("sub_course_id =",id);
        shareSeminarApplicationMapper.deleteByExample(exampleSeminar);
        Example exampleTeam=new Example(ShareTeamApplication.class);
        exampleTeam.createCriteria().andCondition("main_course_id =",id);
        exampleTeam.or().andCondition("sub_course_id =",id);
        shareTeamApplicationMapper.deleteByExample(exampleTeam);
        //删除和strategy相关
        Example exampleConflict=new Example(ConflictCourseStrategy.class);
        exampleConflict.createCriteria().andCondition("course_1_id =",id);
        exampleConflict.or().andCondition("course_2_id =",id);
        conflictCourseStrategyMapper.deleteByExample(exampleConflict);
        //删除课程
        int line = courseMapper.deleteByPrimaryKey(id);
        return line == 1;
    }

    @Override
    public Course selectOne(Long id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Course> selectByTeacherId(Long teacherId) {
        return courseMapper.select(new Course().setTeacherId(teacherId));
    }

    @Override
    public List<Course> selectByStudentId(Long studentId) {
        return courseMapper.selectByStudentId(studentId);
    }

    @Override
    public List<Course> selectCourseKlassByStudentId(Long studentId) {
        return courseMapper.selectCourseKlassByStudentId(studentId);
    }

    @Override
    public List<Course> selectCanShareCourseByPrimaryKey(Long id, int type) {
        if (type == 0) {
            return courseMapper.selectCanShareSeminar(id);
        } else {
            return courseMapper.selectCanShareTeam(id);
        }
    }
}
