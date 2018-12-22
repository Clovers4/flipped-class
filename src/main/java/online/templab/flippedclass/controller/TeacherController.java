package online.templab.flippedclass.controller;

import online.templab.flippedclass.common.email.EmailService;
import online.templab.flippedclass.common.excel.ExcelService;
import online.templab.flippedclass.common.excel.ExcelUtil;
import online.templab.flippedclass.dto.KlassCreateDTO;
import online.templab.flippedclass.dto.RoundSettingDTO;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Cesare
 */
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
    public String activation() {
        return "teacher/activation";
    }

    @PostMapping("/activation")
    public @ResponseBody
    ResponseEntity<Object> activate(String password, String email, String captcha, HttpSession session) {
        String senderCaptcha = ((String) session.getAttribute("activationCaptcha"));
        Long teacherId = (Long) session.getAttribute("teacherId");
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

    /**
     * Todo: Remain to be realized
     */
    @GetMapping("/notification")
    public String notification() {
        return "teacher/notification";
    }

    @GetMapping("/courseList")
    public String course(Model model, HttpSession session) {
        Long teacherId = (Long) session.getAttribute("teacherId");
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
/*
TODO:恢复
    @PostMapping("/course/seminar/info")
    public String seminarInfo(String klassId, String seminarId, Model model) {
        List<KlassSeminar> klassSeminar = seminarService.getKlassSeminarByKlassIdAndSeminarId(klassId, seminarId);
        if (klassSeminar.size() == 0) {
            //TODO:need better code here.
            throw new RuntimeException("No klass seminar");
        }
        model.addAttribute("klassSeminar", klassSeminar.get(0));
        return "teacher/course/seminar/info";
    }
*/
/*
TODO:恢复

    @PostMapping("/course/seminar/enrollList")
    public String seminarEnrollList(String klassSeminarId, Model model) {
        model.addAttribute("enrollList", seminarService.getEnrollListByKsId(klassSeminarId));
        return "teacher/course/seminar/enrollList";
    }
*/

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
TODO:恢复
    @PostMapping("/course/seminar/progressing")
    public String seminarProgressing(String klassSeminarId, Model model) {
        model.addAttribute("ksId", klassSeminarId);
        model.addAttribute("enrollList", seminarService.getEnrollListByKsId(klassSeminarId));
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
            List<Student> students = excelService.loadStudentList(multipartFile);
            klassService.resetStudentList(klass.getId(), students);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }
/*
TODO:delete?????
    @PostMapping("/course/klass/insertStudents")
    public ResponseEntity<Object> insertStudents(@RequestParam("file") MultipartFile multipartFile, String klassId) throws IOException {
        String type = fileService.getFileType(multipartFile);
        Klass klass = seminarService.getKlassById(klassId).get(0);
        if (type.equals(SeminarConfig.WorkBookType.HSSF.getType())) {
            teacherService.insertKlassStudent(klass, new HSSFWorkbook(multipartFile.getInputStream()));
        } else if (type.equals(SeminarConfig.WorkBookType.XSSF.getType())) {
            teacherService.insertKlassStudent(klass, new XSSFWorkbook(multipartFile.getInputStream()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
*/

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

/*
TODO: 恢复
    @PostMapping("/course/share")
    public String seminarShare(String courseId, Model model) {
        model.addAttribute("mainCourse", seminarService.getMainCoursesByCourseId(courseId));
        model.addAttribute("subCourse", seminarService.getSubCoursesByCourseId(courseId));
        return "teacher/course/seminar/share";
    }*/
}
