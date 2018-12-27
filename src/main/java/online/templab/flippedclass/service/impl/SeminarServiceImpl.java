package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.dao.AttendanceDao;
import online.templab.flippedclass.dao.KlassSeminarDao;
import online.templab.flippedclass.dao.RoundDao;
import online.templab.flippedclass.dao.SeminarDao;
import online.templab.flippedclass.entity.*;
import online.templab.flippedclass.service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fj
 * @author jh
 */
@Service
public class SeminarServiceImpl implements SeminarService {

    @Autowired
    SeminarDao seminarDao;

    @Autowired
    KlassSeminarDao klassSeminarDao;

    @Autowired
    AttendanceDao attendanceDao;

    @Autowired
    RoundDao roundDao;

    @Override
    public Boolean insert(Seminar seminar) {
        if(seminar.getRoundId() == null){
            int courseRoundCount = roundDao.selectCount(seminar.getCourseId());
            int roundCount= roundDao.selectCount(null);
            roundDao.insert(new Round().setCourseId(seminar.getCourseId())
                                        .setRoundNum(courseRoundCount+1)
                                        .setReportScoreType(0)
                                        .setQuesScoreType(0)
                                        .setPreScoreType(0)
                                        .setId((long)roundCount+1)

            );
            seminar.setRoundId((long)roundCount+1);
        }
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

    //TODO:
    @Override
    public void downloadPPT(Long klassSeminarId, String PPTurl) {

    }

    //TODO:
    @Override
    public void uploadPPT(Long klassSeminarId, String fileType) {

    }

    //TODO:
    @Override
    public void uploadReport(Long klassSeminarId, String fileType) {

    }

    @Override
    public Boolean insertKlassSeminar(KlassSeminar klassSeminar) {
        return klassSeminarDao.insert(klassSeminar);
    }

    @Override
    public KlassSeminar getKlassSeminar(Long klassId, Long seminarId) {
        return klassSeminarDao.selectOne(klassId, seminarId);
    }

    @Override
    public Boolean deleteKlassSeminar(Long id) {
        return klassSeminarDao.delete(id);
    }

    @Override
    public List<Attendance> getEnrollListByKlassSeminarId(Long klassSeminarId) {
        return attendanceDao.selectByKlassSeminarId(klassSeminarId);
    }

    @Override
    public Boolean enRoll(Attendance attendance) {
        //判断该小组是否报名
        Boolean hasEnRoll = attendanceDao.selectCount(new Attendance()
                .setTeamId(attendance.getTeamId())
                .setKlassSeminarId(attendance.getKlassSeminarId()));
        if (hasEnRoll) {
            return false;
        }
        //判断该报名顺序是否已被报名
        hasEnRoll = attendanceDao.selectCount(new Attendance()
                .setSn(attendance.getSn())
                .setKlassSeminarId(attendance.getKlassSeminarId()));
        if (hasEnRoll) {
            return false;
        }
        return attendanceDao.insert(attendance);
    }

    @Override
    public Boolean deleteEnroll(Long klassSeminarId, Long studentId) {
        return attendanceDao.delete(klassSeminarId, studentId);
    }

    @Override
    public Boolean addQuestion(Long presentationTeamId, Long questionTeamId, Long studentId) {
        return seminarDao.insertQuestion(presentationTeamId, questionTeamId, studentId);
    }

    @Override
    public Student selectOneQuestion(Long presentationTeamId) {
        return seminarDao.selectOneQuestion(presentationTeamId);
    }

    @Override
    public KlassSeminar getKlassSeminarById(Long klassSeminarId) {
        return klassSeminarDao.selectByPrimaryKey(klassSeminarId);
    }

    @Override
    public Attendance getByTeamIdKlassSeminarId(Long teamId, Long klassSeminarId) {
        return attendanceDao.selectByTeamIdKlassSeminarId(teamId,klassSeminarId);
    }

    @Override
    public Boolean updateAttendanceSelective(Attendance attendance) {
        return attendanceDao.updateSelective(attendance);
    }

    @Override
    public Attendance getAttendanceByPrimaryKey(Long attendanceId) {
        return attendanceDao.selectByPrimaryKey(attendanceId);
    }

}
