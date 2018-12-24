package online.templab.flippedclass.dao.impl;

import online.templab.flippedclass.dao.AttendanceDao;
import online.templab.flippedclass.entity.Attendance;
import online.templab.flippedclass.entity.KlassSeminar;
import online.templab.flippedclass.entity.KlassStudent;
import online.templab.flippedclass.entity.Team;
import online.templab.flippedclass.mapper.AttendanceMapper;
import online.templab.flippedclass.mapper.KlassSeminarMapper;
import online.templab.flippedclass.mapper.KlassStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AttendanceDaoImpl implements AttendanceDao {

    @Autowired
    AttendanceMapper attendanceMapper;

    @Autowired
    KlassStudentMapper klassStudentMapper;

    @Autowired
    KlassSeminarMapper klassSeminarMapper;

    @Override
    public List<Attendance> selectByKlassSeminarId(Long klassSeminarId) {
        return attendanceMapper.selectByKlassSeminarId(klassSeminarId);
    }

    @Override
    public Boolean insert(Attendance attendance) {
        return attendanceMapper.insertSelective(attendance) == 1;
    }

    @Override
    public Boolean delete(Long klassSeminarId, Long studentId) {
//        KlassSeminar klassSeminar = klassSeminarMapper.selectByPrimaryKey(klassSeminarId);
//        KlassStudent klassStudent = klassStudentMapper.selectOne(new KlassStudent()
//                .setStudentId(studentId)
//                .setKlassId(klassSeminar.getKlassId()));
//        return attendanceMapper.delete(new Attendance()
//                .setTeamId(klassStudent.getTeamId())
//                .setKlassSeminarId(klassSeminarId)) == 1;
      // TODO
      return null;

    }

    @Override
    public Boolean selectCount(Attendance attendance) {
        return attendanceMapper.selectCount(attendance) != 0;
    }

}
