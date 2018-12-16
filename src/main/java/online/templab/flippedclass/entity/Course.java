package online.templab.flippedclass.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
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
    private Integer presentationPercentage;

    /**
     * 提问分数占比
     */
    @Column(name = "`question_percentage`")
    private Integer questionPercentage;

    /**
     * 报告分数占比
     */
    @Column(name = "`report_percentage`")
    private Integer reportPercentage;

    /**
     * 开始组队时间
     */
    @Column(name = "`team_start_time`")
    private Date teamStartTime;

    /**
     * 截止组队时间
     */
    @Column(name = "`team_end_time`")
    private Date teamEndTime;

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

    private static final long serialVersionUID = 1L;
}