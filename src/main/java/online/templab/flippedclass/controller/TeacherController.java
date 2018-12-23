package online.templab.flippedclass.controller;

import lombok.extern.slf4j.Slf4j;
import online.templab.flippedclass.common.email.EmailService;
import online.templab.flippedclass.common.excel.ExcelService;
import online.templab.flippedclass.common.excel.ExcelUtil;
import online.templab.flippedclass.dto.ApplicationHandleDTO;
import online.templab.flippedclass.dto.KlassCreateDTO;
import online.templab.flippedclass.dto.RoundSettingDTO;
import online.templab.flippedclass.dto.ShareApplicationDTO;
import online.templab.flippedclass.entity.*;
import online.templab.flippedclass.service.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //   @Autowired
//    private  FileService fileService;

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
    ResponseEntity<Object> activate(String password, String email, String captcha, HttpSession session) {
        String senderCaptcha = ((String) session.getAttribute("activationCaptcha"));
        Long teacherId = (Long) session.getAttribute(TEACHER_ID_GIST);
        if (captcha.equals(senderCaptcha)) {
            if (teacherService.activate(teacherId, password, email)) {
                session.removeAttribute("activationCaptcha");
                return ResponseEntity.status(HttpStatus.OK).body(null);
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("教师不存在");
            }
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("验证码错误");
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
        teacherService.updateById(
                new Teacher()
                        .setId(teacherId)
                        .setPassword(password)
        );
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /// TODO: 下面的待完善

    @GetMapping("/notification")
    public String notification(Model model, HttpSession session) {
        String teacherId = ((String) session.getAttribute(TEACHER_ID_GIST));
        // TODO: 恢复
        //STApps:ShareTeamApplications     SSApps:ShareSeminarApplications
        //    List<ShareTeamApplication> shareTeamApplications = applicationService.getShareTeamApplicationByTeacherId(teacherId);
        //     model.addAttribute("STApps",shareTeamApplications);
        //    model.addAttribute("SSApps", applicationService.getShareSeminarApplicationByTeacherId(teacherId));

        return "teacher/notification";
    }
/*
TODO: 恢复
    @PostMapping("/notification/handle")
    public @ResponseBody
    ResponseEntity<Object> handleApplication(@RequestBody ApplicationHandleDTO applicationHandleDTO) {
        log.info(applicationHandleDTO.toString());
        switch (applicationHandleDTO.getAppType()) {
            case 0:
                if (applicationService.handleShareSeminarApplication(applicationHandleDTO)) {
                    return ResponseEntity.status(HttpStatus.OK).body(null);
                } else {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
                }
            case 1:
                if (applicationService.handleShareTeamApplication(applicationHandleDTO)) {
                    return ResponseEntity.status(HttpStatus.OK).body(null);
                } else {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
                }
            case 2:
                return ResponseEntity.status(HttpStatus.OK).body(null);
            default:
                throw new RuntimeException();
        }
    }
*/


    @GetMapping("/courseList")
    public String course(Model model, HttpSession session) {
        Long teacherId = (Long) session.getAttribute(TEACHER_ID_GIST);
        model.addAttribute("courses", courseService.listByTeacherId(teacherId));
        return "teacher/courseList";
    }

    /**
     * Todo: Remains to be deepen designed
     */
    @PostMapping("/course/info")
    public String courseInfo(String courseId, Model model) {
        model.addAttribute("course", courseService.get(Long.valueOf(courseId)));
        return "teacher/course/info";
    }


    @GetMapping("/course/create")
    public String courseCreate() {
        return "teacher/course/create";
    }

    /**
     * Todo[cesare]:
     * Remain to
     * be realized
     */
    @PutMapping("/course")
    public @ResponseBody
    ResponseEntity<Object> courseCreate(Course course) {
        return null;
    }

    @PostMapping("/course/seminarList")
    public String seminarList(String courseId, Model model) {
        // FIXME: roundService待修复，应采用left join的方式
        model.addAttribute("rounds", roundService.listByCourseId(Long.valueOf(courseId)));
        model.addAttribute("klasses", klassService.listByCourseId(Long.valueOf(courseId)));

        return "teacher/course/seminarList";
    }

    @PostMapping("/course/round/setting")
    public String roundSetting(String roundId, Model model) {
        // FIXME: roundService待修复，应采用left join的方式
        Round round = roundService.get(Long.valueOf(roundId));
        Map<String, Klass> klassMap = new HashMap<>(5);
        round.getKlassRounds().forEach(klassRound -> {
            klassMap.put(String.valueOf(klassRound.getKlassId()), klassService.get(klassRound.getKlassId()));
        });
        model.addAttribute("klassMap", klassMap);
        model.addAttribute("round", round);
        return "teacher/course/roundSetting";
    }

    @PostMapping("/course/round/setting/update")
    public @ResponseBody
    ResponseEntity<Object> updateRoundSetting(@RequestBody RoundSettingDTO roundSettingDTO) {
        Round round = roundSettingDTO.getRound();
        List<KlassRound> klassRounds = roundSettingDTO.getKlassRounds();

        roundService.update(round);

        // TODO: klassRound update 未完成
        //klassRounds.forEach(teacherService::updateKlassRound);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/course/seminar/create")
    public String seminarCreate(String courseId, Model model) {
        Integer maxSerial = seminarService.getMaxSeminarSerialByCourseId(Long.valueOf(courseId));
        model.addAttribute("maxSerial", maxSerial);
        model.addAttribute("rounds", roundService.listByCourseId(Long.valueOf(courseId)));
        return "teacher/course/seminar/create";
    }

    @PutMapping("/course/seminar")
    public ResponseEntity<Object> createSeminar(@RequestBody Seminar seminar) {
        Round round = new Round();
        round.setCourseId(seminar.getCourseId());
        if (seminar.getRoundId() == null) {
            roundService.insert(round);
            seminar.setRoundId(round.getId());
        }
        seminarService.insert(seminar);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/course/seminar/option")
    public String seminarOption(String seminarId, Model model) {
        Seminar seminar = seminarService.get(Long.valueOf(seminarId));
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
        seminarService.update(seminar);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * TODO:
     * Should delete
     * the klass
     * seminar
     */
    @DeleteMapping("/course/seminar/{seminarId}")
    public ResponseEntity<Object> deleteSeminar(@PathVariable String seminarId) {
        seminarService.delete(Long.valueOf(seminarId));
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/course/seminar/info")
    public String seminarInfo(String klassId, String seminarId, Model model) {
        KlassSeminar klassSeminar = seminarService.getKlassSeminar(Long.valueOf(klassId), Long.valueOf(seminarId));
        if (klassSeminar == null) {
            //TODO:need better code here.
            throw new RuntimeException("No klass seminar");
        }
        model.addAttribute("klassSeminar", klassSeminar);
        return "teacher/course/seminar/info";
    }


    @PostMapping("/course/seminar/enrollList")
    public String seminarEnrollList(String klassSeminarId, Model model) {
        model.addAttribute("enrollList", seminarService.getEnrollListByKlassSeminarId(Long.valueOf(klassSeminarId)));
        return "teacher/course/seminar/enrollList";
    }

    /**
     * Todo:
     * Remain to
     * be realize
     */
    @PostMapping("/course/seminar/grade")
    public String seminarGrade() {
        return "teacher/course/seminar/grade";
    }
/*
TODO: 貌似不需要了？
    @PostMapping("/course/seminar/progressing")
    public String seminarProgressing(String klassSeminarId, Model model) {
        model.addAttribute("ksId", klassSeminarId);
        model.addAttribute("enrollList", seminarService.getEnrollListByKlassSeminarId(Long.valueOf(klassSeminarId)));
        return "teacher/course/seminar/progressing";
    }
*/

    @PostMapping("/course/klassList")
    public String klassList(String courseId, Model model) {
        model.addAttribute("klasses", klassService.listByCourseId(Long.valueOf(courseId)));
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
            // FIXME
            List<Student> students = excelService.loadStudentList(multipartFile);
            log.info("读取学生名单到 {} 班级", klass.getKlassName());
            for (Student student : students) {
                log.info(student.toString());
            }
            klassService.resetStudentList(klass.getId(), students);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PostMapping("/course/klass/insertStudents")
    public ResponseEntity<Object> insertStudents(@RequestParam("file") MultipartFile multipartFile, String klassId) throws IOException {
        if (!ExcelUtil.isExcel(multipartFile.getOriginalFilename())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("文件格式不正确");
        }

        List<Student> students = excelService.loadStudentList(multipartFile);
        klassService.resetStudentList(Long.valueOf(klassId), students);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/course/klass/{klassId}")
    public @ResponseBody
    ResponseEntity<Object> deleteKlass(@PathVariable String klassId) {
        klassService.delete(Long.valueOf(klassId));
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/course/teamList")
    public String teamList(String courseId, Model model) {
        model.addAttribute("teams", teamService.listByCourseId(Long.valueOf(courseId)));
        return "teacher/course/teamList";
    }

    /**
     * Todo:
     * Remain to
     * be realize
     */
    @PostMapping("/course/grade")
    public String grade(String courseId) {
        return "teacher/course/grade";
    }

    @PostMapping("/course/share")
    public String courseShare(String courseId, Model model) {
        // 读取与该课程共享的主课程,根据共享种类(共享分组、共享讨论课)分别放入map.team和map.seminar中
        // 由于前端使用的是List,因此这里也放入list来返回
        Map<String, List<Course>> mainCourse = new HashMap<>(2);

        List<Course> shareTeamMainCourse = new ArrayList<>();
        shareTeamMainCourse.add(courseService.getShareTeamMainCourse(Long.valueOf(courseId)));
        mainCourse.put("team", shareTeamMainCourse);

        List<Course> shareSeminarMainCourse = new ArrayList<>();
        shareTeamMainCourse.add(courseService.getShareSeminarMainCourse(Long.valueOf(courseId)));
        mainCourse.put("seminar", shareSeminarMainCourse);

        model.addAttribute("mainCourse", mainCourse);

        // 读取与该课程共享的从课程,根据共享种类(共享分组、共享讨论课)分别放入map.team和map.seminar中
        Map<String, List<Course>> subCourse = new HashMap<>(2);

        subCourse.put("team", courseService.listShareTeamSubCourse(Long.valueOf(courseId)));
        subCourse.put("seminar", courseService.listShareSeminarSubCourse(Long.valueOf(courseId)));

        model.addAttribute("subCourse", subCourse);
        return "teacher/course/share";
    }

    @PostMapping("/course/share/create")
    public String courseShareCreate(String courseId, Model model) {
        //TODO     model.addAttribute("otherCourses", seminarService.getOtherCoursesByCourseId(courseId));
        return "teacher/course/share/create";
    }
/*
TODO:
    @PutMapping("/course/shareApplication")
    public @ResponseBody
    ResponseEntity<Object> createCourseShareApplication(@RequestBody ShareApplicationDTO shareApplicationDTO) {

        Course subCourse = courseService.get(shareApplicationDTO.getSubCourseId());
        Long teacherId =subCourse.getTeacherId();

        if (shareApplicationDTO.getShareType() == 0) {
            //Share team
            ShareTeamApplication shareTeamApplication = new ShareTeamApplication();
            shareTeamApplication.setMainCourseId(shareApplicationDTO.getMainCourseId());
            shareTeamApplication.setSubCourseId(shareApplicationDTO.getSubCourseId());
            shareTeamApplication.setTeacherId(teacherId);
            if (applicationService.createShareTeamApplication(shareTeamApplication)) {
                return ResponseEntity.status(HttpStatus.OK).body(null);
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            }
        } else if (shareApplicationDTO.getShareType() == 1) {
            ShareSeminarApplication shareSeminarApplication = new ShareSeminarApplication();
            shareSeminarApplication.setMainCourseId(shareApplicationDTO.getMainCourseId());
            shareSeminarApplication.setSubCourseId(shareApplicationDTO.getSubCourseId());
            shareSeminarApplication.setTeacherId(teacherId);
            if (applicationService.createShareSeminarApplication(shareSeminarApplication)) {
                return ResponseEntity.status(HttpStatus.OK).body(null);
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            }
        } else {
            throw new RuntimeException();
        }
    }*/
}
