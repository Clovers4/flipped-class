package online.templab.flippedclass.dao.impl;

import online.templab.flippedclass.dao.SeminarDao;
import online.templab.flippedclass.entity.*;
import online.templab.flippedclass.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

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

    @Autowired
    AttendanceMapper attendanceMapper;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    RoundMapper roundMapper;

    @Override
    public Boolean insert(Seminar seminar) {
        if(seminar.getVisible()==null){
            seminar.setVisible(false);
        }
        //主课程的关系创建
        int line = seminarMapper.insert(seminar);
        List<Long> klassIds=klassMapper.selectIdByCourseId(seminar.getCourseId());
        for(Long klassId:klassIds){
            klassSeminarMapper.insert(new KlassSeminar()
                    .setKlassId(klassId)
                    .setSeminarId(seminar.getId())
                    .setState(0));
        }
        //考虑从课程的关系创建
        List<Course> courses=courseMapper.select(new Course().setSeminarMainCourseId(seminar.getCourseId()));
        if(!courses.isEmpty()){
            for(Course course:courses){
                List<Long> klassIds2=klassMapper.selectIdByCourseId(course.getId());
                for(Long klassId:klassIds2){
                    klassSeminarMapper.insert(new KlassSeminar()
                            .setKlassId(klassId)
                            .setSeminarId(seminar.getId())
                            .setState(0));
                }
            }
        }
        return line == 1;
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

    @Override
    public Boolean insertQuestion(Long presentationTeamId, Long questionTeamId, Long studentId) {
        Attendance attendance = attendanceMapper.selectPresenting(presentationTeamId);
        int line = questionMapper.insert(new Question()
                .setAttendanceId(attendance.getId())
                .setKlassSeminarId(attendance.getKlassSeminarId())
                .setStudentId(studentId)
                .setTeamId(questionTeamId)
                .setIsSelected(0));
        return line == 1;
    }

    @Override
    public Student selectOneQuestion(Long presentationTeamId) {
        Attendance attendance = attendanceMapper.selectPresenting(presentationTeamId);
        List<Question> questions = questionMapper.select(new Question()
                .setAttendanceId(attendance.getId())
                .setKlassSeminarId(attendance.getKlassSeminarId())
                .setIsSelected(0));
        Random random = new Random();
        int chooseNumber = random.nextInt(questions.size());
        return studentMapper.selectOne(new Student().setId(questions.get(chooseNumber).getStudentId()));
    }
}
