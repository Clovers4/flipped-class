package online.templab.flippedclass.controller;

import lombok.extern.slf4j.Slf4j;
import online.templab.flippedclass.common.email.EmailService;
import online.templab.flippedclass.common.multipart.FileService;
import online.templab.flippedclass.common.websocket.WebSocketService;
import online.templab.flippedclass.entity.*;
import online.templab.flippedclass.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.*;

/**
 * @author wk
 */
@Slf4j
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SeminarService seminarService;

    @Autowired
    private RoundService roundService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private KlassService klassService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private FileService fileService;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private WebSocketService webSocketService;

    private final static String STUDENT_ID_GIST = "studentId";

    private final static Integer MAX_MEMBER = 6;

    @GetMapping(value = {"", "/", "/index"})
    public String index(Model model, HttpSession session, @AuthenticationPrincipal User user) {
        Student student = studentService.getByStudentNum(user.getUsername());
        session.setAttribute(STUDENT_ID_GIST, student.getId());

        if (student.getActivated()) {
            model.addAttribute("student", student);
            return "student/index";
        } else {
            return "redirect:/student/activation";
        }
    }


    @PostMapping("/captcha/{type}")
    public @ResponseBody
    ResponseEntity<Object> getCaptcha(@PathVariable String type, String email, HttpSession session) {
        String captcha = captchaService.generate();
        emailService.sendCaptcha(email, captcha);
        session.setAttribute(type + "Captcha", captcha);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/activation")
    public String activation() {
        return "student/activation";
    }

    @PostMapping("/activation")
    public @ResponseBody
    ResponseEntity<Object> activate(String password, String email, HttpSession session) {
        if (studentService.activate(((Long) session.getAttribute(STUDENT_ID_GIST)), password, email)) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("学生不存在");
        }
    }

    @GetMapping("/setting")
    public String setting(Model model, @AuthenticationPrincipal User user) {
        Student student = studentService.getByStudentNum(user.getUsername());
        model.addAttribute("student", student);

        return "student/setting";
    }


    @GetMapping("/modifyEmail")
    public String modifyEmail() {
        return "student/modifyEmail";
    }

    @PostMapping("/modifyEmail")
    public @ResponseBody
    ResponseEntity<Object> modifyEmail(String email, String captcha, HttpSession session) {
        String senderCaptcha = ((String) session.getAttribute("modifyEmailCaptcha"));
        if (captcha.equals(senderCaptcha)) {
            studentService.update(
                    new Student()
                            .setId((Long) session.getAttribute(STUDENT_ID_GIST))
                            .setEmail(email)
            );
            session.removeAttribute("modifyEmailCaptcha");
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }


    @GetMapping("/modifyPassword")
    public String modifyPassword() {
        return "student/modifyPassword";
    }

    @PostMapping("/modifyPassword")
    public @ResponseBody
    ResponseEntity<Object> modifyPassword(String password, HttpSession session) {
        if (!studentService.update(
                new Student()
                        .setId((Long) session.getAttribute(STUDENT_ID_GIST))
                        .setPassword(password)
        )) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/courseList")
    public String courses(Model model, HttpSession session) {
        Long studentId = (Long) session.getAttribute(STUDENT_ID_GIST);
        model.addAttribute("klasses", klassService.listByStudentId(studentId));
        return "student/courseList";
    }


    @PostMapping("/course/seminarList")
    public String seminarList(Long courseId, Model model) {
        model.addAttribute("rounds", roundService.listByCourseId(courseId));
        return "student/course/seminarList";
    }

    @PostMapping("/course/seminar/info")
    public String seminarInfo(Long klassId, Long seminarId, Model model) {
        KlassSeminar klassSeminar = seminarService.getKlassSeminar(klassId, seminarId);

        model.addAttribute("klassSeminar", klassSeminar);
        return "student/course/seminar/info";
    }

    @PostMapping("/course/seminar/enrollList")
    public String seminarEnrollList(Long klassId, Long seminarId, Model model, HttpSession session) {
        Long studentId = (Long) session.getAttribute("studentId");
        Klass klass = klassService.get(klassId);
        KlassSeminar klassSeminar = seminarService.getKlassSeminar(klassId, seminarId);
        Boolean canEnroll = new Date().compareTo(klassSeminar.getSeminar().getEnrollEndDate()) < 0;

        List<Attendance> enrollList = seminarService.getEnrollListWithNullByKlassSeminarId(klassSeminar.getId());
        Team team = teamService.get(klass.getCourseId(), studentId);
        log.info("enrollList : {}", enrollList.toString());
        log.info("team : {}", team.toString());
        model.addAttribute("enrollList", enrollList);
        model.addAttribute("team", team);
        model.addAttribute("ksId", klassSeminar.getId());
        model.addAttribute("canEnroll", canEnroll);
        return "student/course/seminar/enrollList";
    }

    @PostMapping("/course/seminar/cancelEnroll")
    public ResponseEntity<Object> cancelEnroll(Long attendanceId, HttpSession session) {
        Long studentId = (Long) session.getAttribute("studentId");
        Attendance attendance = seminarService.getAttendanceByPrimaryKey(attendanceId);
        seminarService.deleteEnroll(attendance.getKlassSeminarId(), studentId);
        webSocketService.resetMonitor(attendance.getKlassSeminarId());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/course/seminar/uploadPPT")
    public ResponseEntity<Object> uploadPPT(@RequestParam("file") MultipartFile multipartFile, Long attendanceId) {
        String uuidFilename = fileService.storeWithUUID(multipartFile);
        seminarService.updateAttendanceSelective(
                new Attendance()
                        .setId(attendanceId)
                        .setPreFile(uuidFilename)
                        .setPptName(multipartFile.getOriginalFilename())
        );
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }


    @GetMapping(value = "/course/seminar/downloadPPT", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<FileSystemResource> downloadPPT(String fileName) {
        return ResponseEntity.status(HttpStatus.OK).body(new FileSystemResource(fileService.load(fileName)));
    }

    @PostMapping("/course/seminar/enroll")
    public ResponseEntity<Object> seminarEnroll(Long ksId, Long teamId, Integer sn) {
        log.info("报名讨论课 ksid:{},teamid:{},sn:{}", ksId, teamId, sn);
        if (seminarService.enRoll(new Attendance().setKlassSeminarId(ksId).setTeamId(teamId).setSn(sn))) {
            webSocketService.resetMonitor(ksId);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/course/seminar/report")
    public String seminarReport(Long klassId, Long seminarId, Model model, HttpSession session) {
        System.out.println("klassId:" + klassId + " seminarId:" + seminarId);
        Klass klass = klassService.get(klassId);
        System.out.println("kalss:" + klass.toString());
        KlassSeminar klassSeminar = seminarService.getKlassSeminar(klassId, seminarId);
        System.out.println("klassSeminar:" + klassSeminar.toString());
        Team team = teamService.get(klass.getCourseId(), ((Long) session.getAttribute("studentId")));
        System.out.println("team:" + team.toString());
        Attendance attendance;
        if (team != null) {
            System.out.println(team.getId() + "," + klassSeminar.getId());
            attendance = seminarService.getByTeamIdKlassSeminarId(team.getId(), klassSeminar.getId());
        } else {
            attendance = null;
        }
        model.addAttribute("attendance", attendance);
        return "student/course/seminar/report";
    }

    @PostMapping("/course/seminar/uploadReport")
    public ResponseEntity<Object> uploadReport(@RequestParam("file") MultipartFile multipartFile, Long attendanceId) {
        String uuidFilename = fileService.storeWithUUID(multipartFile);
        seminarService.updateAttendanceSelective(
                new Attendance()
                        .setId(attendanceId)
                        .setReportName(multipartFile.getOriginalFilename())
                        .setReportFile(uuidFilename)
        );
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }


    @PostMapping("/course/teamList")
    public String teamList(Long courseId, Model model, HttpSession session) {
        Course course = courseService.get(courseId);
        Boolean mPermitCreate = course.getTeamEndDate().compareTo(new Date()) > 0;

        List<Team> teamList = teamService.listByCourseId(courseId);
        for (Team team : teamList) {
            System.out.println(team);
        }

        System.out.println(courseId + " , " + ((Long) session.getAttribute("studentId")));
        System.out.println(teamService.get(courseId, ((Long) session.getAttribute("studentId"))));

        model.addAttribute("course", course);
        model.addAttribute("permitCreate", mPermitCreate);
        model.addAttribute("myTeam", teamService.get(courseId, ((Long) session.getAttribute("studentId"))));
        model.addAttribute("teams", teamService.listByCourseId(courseId));
        model.addAttribute("students", teamService.listUnTeamedStudentByCourseId(courseId));
        System.out.println(teamService.listUnTeamedStudentByCourseId(courseId));
        return "student/course/teamList";
    }

    @PostMapping("/course/team/create")
    public String createTeam(Long courseId, Model model, HttpSession session) {
        model.addAttribute("leaderId", session.getAttribute(STUDENT_ID_GIST));
        model.addAttribute("klasses", klassService.listByCourseId(courseId));
        return "student/course/team/create";
    }

    @PutMapping("/course/team")
    public ResponseEntity<Object> createTeam(@RequestBody Team team) {
        try {
            System.out.println(team);
            teamService.create(team);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/course/myTeam")
    public String myTeam(Long courseId, Long teamId, Model model, HttpSession session) {
        Course course = courseService.get(courseId);
        Boolean canChange = new Date().compareTo(course.getTeamEndDate()) < 0;
        Team team = teamService.get(courseId, (Long) session.getAttribute(STUDENT_ID_GIST));

        model.addAttribute("canChange", canChange);
        model.addAttribute("course", course);
        model.addAttribute("maxMember", MAX_MEMBER);
        // 不需要这一行也可以跑
        // model.addAttribute("studentId", session.getAttribute(STUDENT_ID_GIST));
        model.addAttribute("team", team);
        model.addAttribute("students", teamService.listUnTeamedStudentByCourseId(team.getCourseId()));
        return "student/course/myTeam";
    }


    @PostMapping("/course/myTeam/addMembers")
    public ResponseEntity<Object> addMembers(Long studentId, Long teamId, HttpSession session) throws ParseException {
        Team team = teamService.getByPrimaryKey(teamId);

        if (team.getLeaderId().equals(session.getAttribute(STUDENT_ID_GIST))) {
            // 生成一个 List 供 service使用
            List<String> studentNumList = new LinkedList<>();
            studentNumList.add(studentService.getByPrimaryKey(studentId).getStudentNum());
            teamService.addMember(teamId, studentNumList);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/course/myTeam/deleteMember")
    public ResponseEntity<Object> deleteMember(Long studentId, Long teamId, HttpSession session) throws ParseException {
        Team team = teamService.getByPrimaryKey(teamId);

        if (team.getLeaderId().equals(session.getAttribute(STUDENT_ID_GIST))) {
            // 获得要删除的 studentNum
            String studentNum = studentService.getByPrimaryKey(studentId).getStudentNum();
            if (!teamService.removeMember(teamId, studentNum)) {
                ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/course/myTeam/dissolveTeam")
    public ResponseEntity<Object> dissolveTeam(Long teamId, HttpSession session) throws ParseException {
        Team team = teamService.getByPrimaryKey(teamId);
        if (team.getLeaderId().equals(session.getAttribute(STUDENT_ID_GIST))) {
            // 获得 leader 用于解散
            Student leader = studentService.getByPrimaryKey((Long) session.getAttribute(STUDENT_ID_GIST));
            teamService.dissolve(teamId, leader.getId());
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/course/myTeam/quitTeam")
    public ResponseEntity<Object> quitTeam(Long teamId, HttpSession session) throws ParseException {
        teamService.quitTeam(teamId, (Long) session.getAttribute(STUDENT_ID_GIST));
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/course/myTeam/validApplication")
    public ResponseEntity<Object> validApplication(Long teamId, String content) {
        Team team = teamService.getByPrimaryKey(teamId);
        if (team.getStatus() != 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        TeamValidApplication teamValidApplication = new TeamValidApplication()
                .setTeamId(teamId)
                .setContent(content)
                .setTeacherId(courseService.get(team.getCourseId()).getTeacherId());
        if (!teamService.sendTeamValidApplication(teamValidApplication)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("该申请已存在");
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }


    @PostMapping("/course/info")
    public String courseInfo(Long courseId, Model model) {
        model.addAttribute("course", courseService.get(courseId));
        return "student/course/info";
    }

    @PostMapping("/course/grade")
    public String seminarGrade(Long courseId, Long klassId, Model model, HttpSession session) {
        Team team = teamService.get(courseId, ((Long) session.getAttribute(STUDENT_ID_GIST)));
        Boolean hasGrade = (team != null);
        model.addAttribute("hasGrade", hasGrade);
        if (hasGrade) {
            List<Round> rounds = roundService.listByCourseId(courseId);
            Map<String, SeminarScore> seminarScoreMap = new HashMap<>(rounds.size());
            Map<String, RoundScore> roundScoreMap = new HashMap<>(rounds.size());
            rounds.forEach(round -> {
                roundScoreMap.put(String.valueOf(round.getId()), scoreService.getScoreOfRound(team.getId(), round.getId()));
                round.getSeminars().forEach(seminar -> {
                    seminarScoreMap.put(String.valueOf(seminar.getId()),
                            scoreService.getSeminarScore(seminarService.getKlassSeminar(klassId, seminar.getId()).getId(), team.getId()));
                });
            });
            model.addAttribute("rounds", rounds);
            model.addAttribute("seminarScoreMap", seminarScoreMap);
            model.addAttribute("roundScoreMap", roundScoreMap);
        }
        return "student/course/grade";
    }

}
