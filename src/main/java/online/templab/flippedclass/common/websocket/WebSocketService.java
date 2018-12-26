package online.templab.flippedclass.common.websocket;

import online.templab.flippedclass.entity.*;
import online.templab.flippedclass.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wk
 */
@Service
public class WebSocketService {

    @Autowired
    private TeamService teamService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private SeminarService seminarService;

    @Autowired
    private KlassService klassService;

    @Autowired
    private StudentService studentService;

    /**
     * K klassSeminarId
     * V SeminarMonitor
     */
    public static final Map<Long, SeminarMonitor> SEMINAR_MONITOR_MAP = new HashMap<>();

    public SeminarMonitor getMonitor(Long klassSeminarId) {
        SeminarMonitor seminarMonitor = SEMINAR_MONITOR_MAP.getOrDefault(klassSeminarId,
                new SeminarMonitor(klassSeminarId, seminarService.getEnrollListByKlassSeminarId(klassSeminarId)));
        SEMINAR_MONITOR_MAP.putIfAbsent(klassSeminarId, seminarMonitor);
        return seminarMonitor;
    }

    public Team getTeamByStudentNum(Long klassSeminarId, String studentNum) {
        KlassSeminar klassSeminar = seminarService.getKlassSeminarById(klassSeminarId);
        Klass klass = klassService.get(klassSeminar.getKlassId());
        Course course = courseService.get(klass.getCourseId());
        Student student = studentService.getByStudentNum(studentNum);
        return teamService.get(course.getId(), student.getId());
    }
}
