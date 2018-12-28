package online.templab.flippedclass.dao.impl;

import online.templab.flippedclass.dao.AttendanceDao;
import online.templab.flippedclass.entity.Attendance;
import online.templab.flippedclass.entity.KlassSeminar;
import online.templab.flippedclass.entity.KlassStudent;
import online.templab.flippedclass.entity.Team;
import online.templab.flippedclass.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AttendanceDaoImpl implements AttendanceDao {

    @Autowired
    AttendanceMapper attendanceMapper;

    @Autowired
    KlassTeamMapper klassTeamMapper;

    @Autowired
    KlassSeminarMapper klassSeminarMapper;

    @Override
    public List<Attendance> selectByKlassSeminarId(Long klassSeminarId) {
        return attendanceMapper.selectByKlassSeminarId(klassSeminarId);
    }

    @Override
    public Boolean insert(Attendance attendance) {
        if (attendance.getPresenting() == null) {
            attendance.setPresenting(false);
        }
        return attendanceMapper.insertSelective(attendance) == 1;
    }

    @Override
    public Boolean delete(Long klassSeminarId, Long studentId) {
        KlassSeminar klassSeminar = klassSeminarMapper.selectByPrimaryKey(klassSeminarId);
        Long teamId = klassTeamMapper.selectByKlassIdAndStudentId(klassSeminar.getKlassId(), studentId).getTeamId();
        return attendanceMapper.delete(new Attendance()
                .setTeamId(teamId)
                .setKlassSeminarId(klassSeminarId)) == 1;
    }

    @Override
    public Boolean selectCount(Attendance attendance) {
        return attendanceMapper.selectCount(attendance) != 0;
    }

    @Override
    public Attendance selectByTeamIdKlassSeminarId(Long teamId, Long klassSeminarId) {
        return attendanceMapper.selectOne(new Attendance().setKlassSeminarId(klassSeminarId).setTeamId(teamId));
    }

    @Override
    public Boolean updateSelective(Attendance attendance) {
        return attendanceMapper.updateByPrimaryKeySelective(attendance) == 1;
    }

    @Override
    public Attendance selectByPrimaryKey(Long attendanceId) {
        return attendanceMapper.selectByPrimaryKey(attendanceId);
    }

}
