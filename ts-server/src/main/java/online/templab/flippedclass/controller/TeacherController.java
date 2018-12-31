package online.templab.flippedclass.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import online.templab.flippedclass.common.email.EmailService;
import online.templab.flippedclass.common.excel.ExcelService;
import online.templab.flippedclass.common.excel.ExcelUtil;
import online.templab.flippedclass.common.multipart.FileService;
import online.templab.flippedclass.dto.*;
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
import net.sf.json.JSONArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Cesare
 */
@Slf4j
@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private KlassService klassService;

    @Autowired
    private RoundService roundService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private SeminarService seminarService;

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ExcelService excelService;

    @Autowired
    private ShareService shareService;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FileService fileService;

    private final static String TEACHER_ID_GIST = "teacherId";

    @GetMapping(value = {"", "/index"})
    public String index(Model model, HttpSession session, @AuthenticationPrincipal User user) {
        Teacher teacher = teacherService.getByTeacherNum(user.getUsername());
        session.setAttribute(TEACHER_ID_GIST, teacher.getId());
        if (teacher.getActivated()) {
            model.addAttribute("teacher", teacher);
            return "teacher/index";
        } else {
            return "redirect:/teacher/activation";
        }
    }

    @PostMapping("/captcha/{type}")
    public @ResponseBody
    ResponseEntity<Object> getCaptcha(@PathVariable String type, String email, HttpSession session) {
        String captcha = captchaService.generate();
        session.setAttribute(type + "Captcha", captcha);
        emailService.sendCaptcha(email, captcha);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/activation")
    public String activation(Model model, @AuthenticationPrincipal User user) {
        Teacher teacher = teacherService.getByTeacherNum(user.getUsername());
        model.addAttribute("teacher", teacher);
        return "teacher/activation";
    }

    @PostMapping("/activation")
    public @ResponseBody
    ResponseEntity<Object> activate(String password, String email, HttpSession session) {
        if (teacherService.activate(((Long) session.getAttribute(TEACHER_ID_GIST)), password, email)) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("教师不存在");
        }
    }


    @GetMapping("/setting")
    public String setting(Model model, @AuthenticationPrincipal User user) {
        Teacher teacher = teacherService.getByTeacherNum(user.getUsername());
        model.addAttribute("teacher", teacher);

        return "teacher/setting";
    }

    @GetMapping("/modifyEmail")
    public String modifyEmail() {
        return "teacher/modifyEmail";
    }

    @PostMapping("/modifyEmail")
    public @ResponseBody
    ResponseEntity<Object> modifyEmail(String email, String captcha, HttpSession session) {
        String senderCaptcha = ((String) session.getAttribute("modifyEmailCaptcha"));
        Long teacherId = (Long) session.getAttribute("teacherId");
        if (captcha.equals(senderCaptcha)) {
            teacherService.updateById(
                    new Teacher()
                            .setId(teacherId)
                            .setEmail(email)
            );
            session.removeAttribute("modifyEmailCaptcha");
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @GetMapping("/modifyPassword")
    public String modifyPassword() {
        return "teacher/modifyPassword";
    }

    @PostMapping("/modifyPassword")
    public @ResponseBody
    ResponseEntity<Object> modifyPassword(String password, HttpSession session) {
        Long teacherId = (Long) session.getAttribute("teacherId");
        if (!teacherService.updateById(
                new Teacher()
                        .setId(teacherId)
                        .setPassword(password)
        )) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/notification")
    public String notification(Model model, HttpSession session) {
        Long teacherId = ((Long) session.getAttribute(TEACHER_ID_GIST));
        List<ShareTeamApplication> shareTeamApplications = shareService.listShareTeamApplication(teacherId);
        model.addAttribute("STApps", shareTeamApplications);
        model.addAttribute("SSApps", shareService.listShareSeminarApplication(teacherId));
        model.addAttribute("TVApps", teamService.getTeamApplicationByTeacherId(teacherId));
        return "teacher/notification";
    }

    @PostMapping("/notification/handle")
    public @ResponseBody
    ResponseEntity<Object> handleApplication(@RequestBody ApplicationHandleDTO applicationHandleDTO) {
        /*
         * 0 : ShareSeminar
         * 1 : ShareTeam
         * 2 : Team valid
         */
        switch (applicationHandleDTO.getAppType()) {
            case 0:
                if (shareService.processShareSeminarApplication(applicationHandleDTO.getAppId(), applicationHandleDTO.getOperationType() == 1)) {
                    return ResponseEntity.status(HttpStatus.OK).body(null);
                } else {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
                }
            case 1:
                if (shareService.processShareTeamApplication(applicationHandleDTO.getAppId(), applicationHandleDTO.getOperationType() == 1)) {
                    return ResponseEntity.status(HttpStatus.OK).body(null);
                } else {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
                }
            case 2:
                if (teamService.processTeamValidApplication(applicationHandleDTO.getAppId(),
                        applicationHandleDTO.getTeamId(), applicationHandleDTO.getOperationType() == 1)) {
                    return ResponseEntity.status(HttpStatus.OK).body(null);
                } else {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
                }
            default:
                throw new RuntimeException();
        }
    }

    @GetMapping("/courseList")
    public String course(Model model, HttpSession session) {
        Long teacherId = (Long) session.getAttribute(TEACHER_ID_GIST);
        model.addAttribute("courses", courseService.listByTeacherId(teacherId));
        return "teacher/courseList";
    }

    @PostMapping("/course/info")
    public String courseInfo(Long courseId, Model model) {
        model.addAttribute("course", courseService.get(courseId));
        //TODO:model.addAttribute("strategies", seminarService.getStrategiesByCourseId(courseId));
        return "teacher/course/info";
    }

    /// TODO 陈旭辉组
    @PostMapping("/course")
    @ResponseBody
    public ResponseEntity<String> courseCreate(HttpServletRequest request, Model model, HttpSession session) throws IOException {
        // TODO:下面的BIgInteger都用LOng代替了，不知道有没有问题，若没有问题删掉该TODO
        Long teacherId = (Long) session.getAttribute(TEACHER_ID_GIST);
        log.info("courseName : {}", request.getParameter("courseName"));

        Course course = new Course()
                .setCourseName(request.getParameter("courseName"))
                .setIntroduction(request.getParameter("introduction"))
                .setPrePercentage(Integer.valueOf(request.getParameter("presentationPercentage")))
                .setQuesPercentage(Integer.valueOf(request.getParameter("questionPercentage")))
                .setReportPercentage(Integer.valueOf(request.getParameter("reportPercentage")));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            course.setTeamStartDate(sdf.parse(request.getParameter("teamStartTime")))
                    .setTeamEndDate(sdf.parse(request.getParameter("teamEndTime")));
        } catch (Exception e) {
            log.warn("时间格式出错！");
        }

        MemberLimitStrategy memberLimitStrategy = new MemberLimitStrategy()
                .setMin(Integer.valueOf(request.getParameter("minTeamMember")))
                .setMax(Integer.valueOf(request.getParameter("maxTeamMember")));


        List<CourseMemberLimitStrategy> courseMemberLimitStrategyList = new LinkedList<>();
        String members = request.getParameter("members");
        JSONArray myArray = JSONArray.fromObject(members);
        for (int i = 0; i < myArray.size(); i++) {
            JSONArray secondArray = (JSONArray) myArray.get(i);
            //Long cid = (Long) secondArray.get(0);
            Long cid = Long.valueOf((String) secondArray.get(0));
            CourseMemberLimitStrategy optionCourse = new CourseMemberLimitStrategy()
                    .setCourseId(cid)
                    .setMax(Integer.valueOf((String) secondArray.get(1)))
                    .setMin(Integer.valueOf((String) secondArray.get(2)));
            courseMemberLimitStrategyList.add(optionCourse);
            System.out.println(optionCourse);
        }
//        List<ConflictCourseStrategy> conflictCourseStrategyArrayList = new ArrayList<>();
//        String conflicts = request.getParameter("conflicts");
//        JSONArray conflictsArray = JSONArray.fromObject(conflicts);
//        for (int i = 0; i < conflictsArray.size(); i++) {
//            JSONArray secondArray = (JSONArray) conflictsArray.get(i);
//            ConflictCourseStrategy conflictCourse = new ConflictCourseStrategy();
//            List<Long> courseIdList = new ArrayList<>();
//            for (int j = 0; j < secondArray.size(); j++) {
//                courseIdList.add((Long) secondArray.get(j));
//            }
//            conflictCourse.setConflictCourseIdList(courseIdList);
//            conflictCourseStrategyArrayList.add(conflictCourse);
//        }

        //这个choose
        Integer choose = Integer.valueOf(request.getParameter("choose"));

        course.setTeacherId(teacherId);
        courseService.insert(course);
        //courseService.insertCourse(course, teacherId, conflictCourseStrategyArrayList, courseMemberLimitStrategyList, thisCourse, choose);

        Long courseId = course.getId();
        List<TeamStrategy> teamStrategyList = new LinkedList<>();

        //组装 MemberLimitStrategy
        List<CourseStrategy> courseStrategyList = new LinkedList<>();
        courseStrategyList.add(memberLimitStrategy);
        TeamStrategy teamStrategy = new TeamStrategy()
                .setCourseId(courseId)
                .setStrategyName("MemberLimitStrategy");
        teamStrategy.setCourseStrategyList(courseStrategyList);
        System.out.println(teamStrategy);
        teamStrategyList.add(teamStrategy);

        //组装 CourseMemberLimitStrategyList
        List<CourseStrategy> courseStrategyListResutlt = new LinkedList<>();
        courseStrategyList = new LinkedList<>();
        if (choose == 1) {
            for (int i = 0; i < courseMemberLimitStrategyList.size(); ++i) {
                courseStrategyList.add(courseMemberLimitStrategyList.get(i));
                TeamOrStrategy teamOrStrategy = new TeamOrStrategy().setStrategyName("CourseMemberLimitStrategy");
                teamOrStrategy.setCourseStrategyList(courseStrategyList);
                courseStrategyList = new LinkedList<>();
                courseStrategyListResutlt.add(teamOrStrategy);
            }

            teamStrategy = new TeamStrategy()
                    .setCourseId(courseId)
                    .setStrategyName("TeamOrStrategy");
            teamStrategy.setCourseStrategyList(courseStrategyListResutlt);
            System.out.println(teamStrategy);
            teamStrategyList.add(teamStrategy);
        } else {

            for (int i = 0; i < courseMemberLimitStrategyList.size(); ++i) {
                courseStrategyList.add(courseMemberLimitStrategyList.get(i));
                TeamAndStrategy teamAndStrategy = new TeamAndStrategy()
                        .setStrategyName("CourseMemberLimitStrategy");
                teamAndStrategy.setCourseStrategyList(courseStrategyList);
                courseStrategyList = new LinkedList<>();
                courseStrategyListResutlt.add(teamAndStrategy);
            }

            teamStrategy = new TeamStrategy()
                    .setCourseId(courseId)
                    .setStrategyName("TeamAndStrategy");
            teamStrategy.setCourseStrategyList(courseStrategyListResutlt);
            System.out.println(teamStrategy);
            teamStrategyList.add(teamStrategy);
        }
//        for(int i = 0; i < courseMemberLimitStrategyList.size() ; ++i){
//            courseStrategyList.add(courseMemberLimitStrategyList.get(i));
//        }
//        teamStrategy.setCourseStrategyList(courseStrategyList);
//        teamStrategyList.add(teamStrategy);

        //组装 ConflictCourseStrategy
        List<ConflictCourseStrategy> conflictCourseStrategyArrayList = new ArrayList<>();
        String conflicts = request.getParameter("conflicts");
        JSONArray conflictsArray = JSONArray.fromObject(conflicts);
        for (int i = 0; i < conflictsArray.size(); i++) {
            JSONArray secondArray = (JSONArray) conflictsArray.get(i);
            teamStrategy = new TeamStrategy()
                    .setCourseId(courseId)
                    .setStrategyName("ConflictCourseStrategy");
            courseStrategyList = new LinkedList<>();

            for (int j = 0; j < secondArray.size(); j++) {
                ConflictCourseStrategy conflictCourseStrategy = new ConflictCourseStrategy()
                        .setCourseId(Long.valueOf((String) secondArray.get(j)));
                courseStrategyList.add(conflictCourseStrategy);
                //courseIdList.add((Long) secondArray.get(j));
            }
            teamStrategy.setCourseStrategyList(courseStrategyList);
            System.out.println(teamStrategy);
            teamStrategyList.add(teamStrategy);
//            conflictCourse.setConflictCourseIdList(courseIdList);
//            conflictCourseStrategyArrayList.add(conflictCourse);
        }

        System.out.println(courseStrategyList);
        teamService.insertTeamStratgyList(teamStrategyList);


        return new ResponseEntity<>("", HttpStatus.OK);
    }


    // TODO: 这里将courses修改成了courseList，确认可以接上cxh组的代码之后可以删掉TODO以及后面的courseCreate
    @GetMapping("/course/create")
    public String courseCreate(Model model) {
        model.addAttribute("courseList", courseService.listAllCourse());
        return "teacher/course/create";
    }
/*
TODO：可删除
    @PutMapping("/course")
    public @ResponseBody
    ResponseEntity<Object> courseCreate(@RequestBody CourseCreateDTO courseCreateDTO, HttpSession session) {
        Course course = courseCreateDTO.getCourse();
        course.setTeacherId(((Long) session.getAttribute(TEACHER_ID_GIST)));
        courseService.insert(course);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
*/

    @DeleteMapping("/course/{courseId}")
    public ResponseEntity<Object> deleteCourse(@PathVariable Long courseId) {
        courseService.delete(courseId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/course/seminarList")
    public String seminarList(Long courseId, Model model) {
        Course course = courseService.get(courseId);
        Boolean canAdd = course.getSeminarMainCourseId() == null;
        model.addAttribute("canAdd", canAdd);
        model.addAttribute("rounds", roundService.listByCourseId(courseId));
        model.addAttribute("klasses", klassService.listByCourseId(courseId));

        return "teacher/course/seminarList";
    }

    @PostMapping("/course/round/setting")
    public String roundSetting(Long roundId, Long courseId, Model model) {
        Round round = roundService.get(roundId, courseId);
        Map<String, KlassRound> klassRoundMap = new HashMap<>(5);
        List<Klass> klasses = klassService.listByCourseId(courseId);
        klasses.forEach(klass -> {
            klassRoundMap.put(String.valueOf(klass.getId()), roundService.getKlassRound(klass.getId(), roundId));
        });
        System.out.println(klassRoundMap);
        model.addAttribute("klasses", klasses);
        model.addAttribute("klassRoundMap", klassRoundMap);
        model.addAttribute("round", round);
        return "teacher/course/roundSetting";
    }

    @PostMapping("/course/round/setting/update")
    public @ResponseBody
    ResponseEntity<Object> updateRoundSetting(@RequestBody RoundSettingDTO roundSettingDTO) {
        Round round = roundSettingDTO.getRound();
        List<KlassRound> klassRounds = roundSettingDTO.getKlassRounds();

        if (!roundService.update(round)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("更新轮次分数计算失败");
        }

        if (klassRounds != null) {
            for (KlassRound klassRound : klassRounds) {
                roundService.updateKlassRound(klassRound);
                roundService.updateKlassRound(klassRound);
            }
        }
        roundService.update(round);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/course/seminar/create")
    public String seminarCreate(Long courseId, Model model) {
        Integer maxSerial = seminarService.getMaxSeminarSerialByCourseId(courseId);
        model.addAttribute("maxSerial", maxSerial);
        model.addAttribute("rounds", roundService.listByCourseId(courseId));
        return "teacher/course/seminar/create";
    }

    @PutMapping("/course/seminar")
    public ResponseEntity<Object> createSeminar(@RequestBody Seminar seminar) {
        seminarService.insert(seminar);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/course/seminar/option")
    public String seminarOption(Long seminarId, Model model) {
        Seminar seminar = seminarService.get(seminarId);
        model.addAttribute("seminar", seminar);
        model.addAttribute("rounds", roundService.listByCourseId(seminar.getCourseId()));
        return "teacher/course/seminar/option";
    }

    @PatchMapping("/course/seminar")
    public ResponseEntity<Object> updateSeminar(@RequestBody Seminar seminar) {
        Round round = new Round();
        if (seminar.getRoundId() == null) {
            round.setCourseId(seminar.getCourseId());
            roundService.insert(round);
            seminar.setRoundId(round.getId());
        }
        if (!seminarService.update(seminar)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/course/seminar/{seminarId}")
    public ResponseEntity<Object> deleteSeminar(@PathVariable Long seminarId) {
        seminarService.delete(seminarId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/course/seminar/info")
    public String seminarInfo(Long klassId, Long seminarId, Long ksId, Model model) {
        KlassSeminar klassSeminar;
        System.out.println("ksid:" + ksId);
        if (ksId != null) {
            klassSeminar = seminarService.getKlassSeminarById(ksId);
        } else {
            klassSeminar = seminarService.getKlassSeminar(klassId, seminarId);
        }
        if (klassSeminar == null) {
            throw new RuntimeException("No klass seminar");
        }
        model.addAttribute("klassSeminar", klassSeminar);
        return "teacher/course/seminar/info";
    }

    @PostMapping("/course/seminar/grade")
    public String seminarGrade(Long klassSeminarId, Model model) {
        List<Attendance> attendances = seminarService.getEnrollListByKlassSeminarId(klassSeminarId);
        Map<String, SeminarScore> seminarScoreMap = new HashMap<>(attendances.size());
        attendances.forEach(attendance -> {
            seminarScoreMap.put(String.valueOf(attendance.getId()), scoreService.getSeminarScore(klassSeminarId, attendance.getTeamId()));
        });
        model.addAttribute("seminarScore", seminarScoreMap);
        model.addAttribute("attendances", attendances);
        model.addAttribute("ksId", klassSeminarId);
        return "teacher/course/seminar/grade";
    }

    @PostMapping("/course/seminar/grade/modify")
    public ResponseEntity<Object> modifyGrade(Long attendanceId, BigDecimal preScore, BigDecimal queScore, BigDecimal reportScore, Model model) {
        Attendance attendance = seminarService.getAttendanceByPrimaryKey(attendanceId);
        scoreService.markerScore(
                new SeminarScore()
                        .setTeamId(attendance.getTeamId())
                        .setKlassSeminarId(attendance.getKlassSeminarId())
                        .setPresentationScore(preScore)
                        .setQuestionScore(queScore)
                        .setReportScore(reportScore)
        );
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }


    @PostMapping("/course/seminar/enrollList")
    public String seminarEnrollList(Long klassSeminarId, Model model) {
        KlassSeminar klassSeminar = seminarService.getKlassSeminarById(klassSeminarId);
        Boolean hasEnd = klassSeminar.getState() == 2;
        model.addAttribute("hasEnd", hasEnd);
        model.addAttribute("ksId", klassSeminar.getId());
        model.addAttribute("enrollList", seminarService.getEnrollListWithNullByKlassSeminarId(klassSeminarId));
        return "teacher/course/seminar/enrollList";
    }

    @PostMapping("/course/seminar/enrollList/giveScore")
    public ResponseEntity<Object> giveScore(BigDecimal score, Long ksId, Long teamId) {
        scoreService.markerScore(
                new SeminarScore()
                        .setTeamId(teamId)
                        .setKlassSeminarId(ksId)
                        .setReportScore(score)
        );
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping(value = "/course/seminar/downloadPPT", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<FileSystemResource> downloadPPT(String fileName, String teamId) {
        return ResponseEntity.status(HttpStatus.OK).body(new FileSystemResource(fileService.load(fileName)));
    }

    @GetMapping(value = "/course/seminar/downloadReport", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<FileSystemResource> downloadReport(String fileName, String teamId) {
        return ResponseEntity.status(HttpStatus.OK).body(new FileSystemResource(fileService.load(fileName)));
    }

    @PostMapping("/course/klassList")
    public String klassList(Long courseId, Model model) {
        model.addAttribute("klasses", klassService.listByCourseId(courseId));
        return "teacher/course/klassList";
    }

    @GetMapping("/course/klass/create")
    public String klassCreate() {
        return "teacher/course/klass/create";
    }

    @PutMapping("/course/klass")
    public @ResponseBody
    ResponseEntity<Object> createKlass(KlassCreateDTO vo, @RequestParam("file") MultipartFile multipartFile) throws IOException {
        if (!ExcelUtil.isExcel(multipartFile.getOriginalFilename())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("文件格式不正确");
        }

        Klass klass = vo.getKlass();
        if (klassService.insert(klass)) {
            List<Student> students = excelService.loadStudentList(multipartFile);
            log.info("读取学生名单到 {} 班级", klass.getKlassName());
            for (Student student : students) {
                log.info(student.toString());
            }
            klassService.setStudentList(klass.getId(), students);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PostMapping("/course/klass/insertStudents")
    public ResponseEntity<Object> insertStudents(@RequestParam("file") MultipartFile multipartFile, Long klassId) throws IOException {
        if (!ExcelUtil.isExcel(multipartFile.getOriginalFilename())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("文件格式不正确");
        }

        List<Student> students = excelService.loadStudentList(multipartFile);
        klassService.resetStudentList(klassId, students);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/course/klass/{klassId}")
    public @ResponseBody
    ResponseEntity<Object> deleteKlass(@PathVariable Long klassId) {
        klassService.delete(klassId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/course/teamList")
    public String teamList(Long courseId, Model model) {
        model.addAttribute("teams", teamService.listByCourseId(courseId));
        return "teacher/course/teamList";
    }

    @PostMapping("/course/grade")
    public String grade(Long courseId, Model model) {
        //Map roundId - roundScores(for every team in the course)
        List<Round> rounds = roundService.listByCourseId(courseId);
        List<Team> teams = teamService.listByCourseId(courseId);
        model.addAttribute("rounds", rounds);
        model.addAttribute("teams", teams);
        model.addAttribute("roundScores", scoreService.getRoundScores(rounds, teams));
        return "teacher/course/grade";
    }

    @PostMapping("/course/share")
    public String courseShare(Long courseId, Model model) {
        // 读取与该课程共享的主课程,根据共享种类(共享分组、共享讨论课)分别放入map.team和map.seminar中
        // 由于前端使用的是List,因此这里也放入list来返回
        Map<String, List<Course>> mainCourse = new HashMap<>(2);
        List<Course> shareTeamMainCourse = new ArrayList<>();
        Course course = shareService.getShareTeamMainCourse(courseId);
        if (course != null) {
            shareTeamMainCourse.add(course);
        }
        List<Course> shareSeminarMainCourse = new ArrayList<>();
        Course course1 = shareService.getShareSeminarMainCourse(courseId);
        if (course1 != null) {
            shareSeminarMainCourse.add(course1);
        }
        mainCourse.put("team", shareTeamMainCourse);
        mainCourse.put("seminar", shareSeminarMainCourse);

        // 读取与该课程共享的从课程,根据共享种类(共享分组、共享讨论课)分别放入map.team和map.seminar中
        Map<String, List<Course>> subCourse = new HashMap<>(2);
        subCourse.put("team", shareService.listShareTeamSubCourse(courseId));
        subCourse.put("seminar", shareService.listShareSeminarSubCourse(courseId));

        model.addAttribute("subCourse", subCourse);
        model.addAttribute("mainCourse", mainCourse);
        model.addAttribute("course", courseService.get(courseId));
        return "teacher/course/share";
    }

    @PostMapping("/course/share/create")
    public String courseShareCreate(Long courseId, Integer shareType, Model model) {
        if (shareType == null) {
            model.addAttribute("otherCourses", courseService.listCanShareCourseByPrimaryKey(courseId, 0));
        } else {
            model.addAttribute("otherCourses", courseService.listCanShareCourseByPrimaryKey(courseId, shareType));
        }
        return "teacher/course/share/create";
    }

    @PostMapping("/course/share/cancelTeamShare")
    public ResponseEntity<Object> cancelTeamShare(Long subCourseId) {
        shareService.cancelShareTeamApplication(subCourseId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/course/share/cancelSeminarShare")
    public ResponseEntity<Object> cancelSeminarShare(Long subCourseId) {
        shareService.cancelShareSeminarApplication(subCourseId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PutMapping("/course/shareApplication")
    public @ResponseBody
    ResponseEntity<Object> createCourseShareApplication(@RequestBody ShareApplicationDTO shareApplicationDTO) {

        Course subCourse = courseService.get(shareApplicationDTO.getSubCourseId());
        Long teacherId = subCourse.getTeacherId();

        if (shareApplicationDTO.getShareType() == 0) {
            //Share team
            ShareTeamApplication shareTeamApplication = new ShareTeamApplication();
            shareTeamApplication.setMainCourseId(shareApplicationDTO.getMainCourseId());
            shareTeamApplication.setSubCourseId(shareApplicationDTO.getSubCourseId());
            shareTeamApplication.setTeacherId(teacherId);
            Course course = courseService.get(shareApplicationDTO.getSubCourseId());
            if (course.getTeamMainCourseId() != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if (shareService.sendShareTeamApplication(shareTeamApplication)) {
                return ResponseEntity.status(HttpStatus.OK).body(null);
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            }
        } else if (shareApplicationDTO.getShareType() == 1) {
            ShareSeminarApplication shareSeminarApplication = new ShareSeminarApplication();
            Course course = courseService.get(shareApplicationDTO.getSubCourseId());
            if (course.getSeminarMainCourseId() != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            shareSeminarApplication.setMainCourseId(shareApplicationDTO.getMainCourseId());
            shareSeminarApplication.setSubCourseId(shareApplicationDTO.getSubCourseId());
            shareSeminarApplication.setTeacherId(teacherId);
            if (shareService.sendShareSeminarApplication(shareSeminarApplication)) {
                return ResponseEntity.status(HttpStatus.OK).body(null);
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            }
        } else {
            throw new RuntimeException();
        }
    }
}
