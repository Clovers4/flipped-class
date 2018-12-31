package online.templab.flippedclass.service.impl;

import lombok.extern.slf4j.Slf4j;
import online.templab.flippedclass.dao.AttendanceDao;
import online.templab.flippedclass.dao.KlassSeminarDao;
import online.templab.flippedclass.dao.RoundDao;
import online.templab.flippedclass.dao.SeminarDao;
import online.templab.flippedclass.entity.*;
import online.templab.flippedclass.service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author fj
 * @author jh
 */
@Slf4j
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
        if (seminar.getRoundId() == null) {
            int courseRoundCount = roundDao.selectCount(seminar.getCourseId());
            int roundCount = roundDao.selectCount(null);
            roundDao.insert(new Round().setCourseId(seminar.getCourseId())
                    .setRoundNum(courseRoundCount + 1)
                    .setReportScoreType(0)
                    .setQuesScoreType(0)
                    .setPreScoreType(0)
                    .setId((long) roundCount + 1)

            );
            seminar.setRoundId((long) roundCount + 1);
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
    public List<Attendance> getEnrollListWithNullByKlassSeminarId(Long klassSeminarId) {
        List<Attendance> attendanceList = attendanceDao.selectByKlassSeminarId(klassSeminarId);
        Long seminarId = klassSeminarDao.selectByPrimaryKey(klassSeminarId).getSeminarId();
        Seminar seminar = seminarDao.selectByPrimaryKey(seminarId);

        Collections.sort(attendanceList, new Comparator<Attendance>() {
            @Override
            public int compare(Attendance o1, Attendance o2) {
                return o1.getSn() - o2.getSn();
            }
        });

        List<Attendance> resultAttendanceList = new ArrayList<>();
        int flag = 0;
        log.info("排序的但未添加null的attendanceList：{}", attendanceList.toString());
        for (int i = 1; i <= seminar.getMaxTeam(); i++) {
            for (Attendance attendance : attendanceList) {
                if (attendance.getSn() == (i)) {
                    resultAttendanceList.add(attendance);
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                resultAttendanceList.add(null);
            }
            flag = 0;
        }
        log.info("排序的且添加null的attendanceList：{}", resultAttendanceList.toString());

        return resultAttendanceList;
    }

    @Override
    public List<Attendance> getEnrollListByKlassSeminarId(Long klassSeminarId) {
        List<Attendance> attendanceList = attendanceDao.selectByKlassSeminarId(klassSeminarId);
        Long seminarId = klassSeminarDao.selectByPrimaryKey(klassSeminarId).getSeminarId();
        Seminar seminar = seminarDao.selectByPrimaryKey(seminarId);

        Collections.sort(attendanceList, new Comparator<Attendance>() {
            @Override
            public int compare(Attendance o1, Attendance o2) {
                return o1.getSn() - o2.getSn();
            }
        });

        return attendanceList;
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
        KlassSeminar klassSeminar = klassSeminarDao.selectByPrimaryKey(klassSeminarId);
        klassSeminar.setSeminar(seminarDao.selectByPrimaryKey(klassSeminar.getSeminarId()));
        return klassSeminar;
    }

    @Override
    public Attendance getByTeamIdKlassSeminarId(Long teamId, Long klassSeminarId) {
        return attendanceDao.selectByTeamIdKlassSeminarId(teamId, klassSeminarId);
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
