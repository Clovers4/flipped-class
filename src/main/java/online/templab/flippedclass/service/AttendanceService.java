package online.templab.flippedclass.service;

import online.templab.flippedclass.entity.Attendance;

import java.util.List;

/**
 * Attendance 业务 接口类
 *
 * @author wk
 */
public interface AttendanceService {

    /**
     * 根据 klassSeminarId 获得一个 List<Attendances>
     *
     * @param klassSeminarId
     * @return
     */
    List<Attendance> listByKlassSeminarId(Long klassSeminarId);
}
