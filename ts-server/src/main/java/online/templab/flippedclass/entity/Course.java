package online.templab.flippedclass.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 新增 List<Klass> klassList
 * @author chenr
 */
@Data
@Accessors(chain = true)
@Table(name = "`course`")
public class Course implements Serializable {

    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 教师id
     */
    @Column(name = "`teacher_id`")
    private Long teacherId;

    /**
     * 课程名称
     */
    @Column(name = "`course_name`")
    private String courseName;

    /**
     * 课程介绍
     */
    @Column(name = "`introduction`")
    private String introduction;

    /**
     * 展示分数占比
     */
    @Column(name = "`presentation_percentage`")
    private Integer prePercentage;

    /**
     * 提问分数占比
     */
    @Column(name = "`question_percentage`")
    private Integer quesPercentage;

    /**
     * 报告分数占比
     */
    @Column(name = "`report_percentage`")
    private Integer reportPercentage;

    /**
     * 开始组队时间
     */
    @Column(name = "`team_start_time`")
    @JsonFormat(pattern = "yyyy-MM-dd H:mm", timezone = "GMT+8")
    private Date teamStartDate;

    /**
     * 截止组队时间
     */
    @Column(name = "`team_end_time`")
    @JsonFormat(pattern = "yyyy-MM-dd H:mm", timezone = "GMT+8")
    private Date teamEndDate;

    /**
     * 共享分组，主课程id
     */
    @Column(name = "`team_main_course_id`")
    private Long teamMainCourseId;

    /**
     * 共享讨论课，主课程id
     */
    @Column(name = "`seminar_main_course_id`")
    private Long seminarMainCourseId;

    private List<Round> roundList;

    private List<Seminar> seminarList;

    /**
     * 对应的教师
     */
    private Teacher teacher;

    //TODO:
//    @Block
//    private RegulationComposition regulationComposition;

    /**
     * 老师的Course下会有零到多个klass，学生只有一个klass
     */
    private List<Klass> klassList;

    // TODO: private 分组规则
    /**
     * 这个课程下的分组策略
     */
    private List<TeamStrategy> teamStrategyList;

    private static final long serialVersionUID = 1L;
}