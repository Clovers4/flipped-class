package online.templab.flippedclass.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@Table(name = "`seminar`")
public class Seminar implements Serializable {

    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 课程id
     */
    @Column(name = "`course_id`")
    private Long courseId;

    /**
     * 轮次id
     */
    @Column(name = "`round_id`")
    private Long roundId;

    /**
     * 讨论课名称
     */
    @Column(name = "`seminar_name`")
    private String theme;

    /**
     * 讨论课介绍
     */
    @Column(name = "`introduction`")
    private String content;

    /**
     * 报名讨论课最多组数
     */
    @Column(name = "`max_team`")
    private Integer maxTeam;

    /**
     * 是否可见
     */
    @Column(name = "`is_visible`")
    private Boolean visible;

    /**
     * 讨论课序号
     */
    @Column(name = "`seminar_serial`")
    private Integer serial;

    /**
     * 讨论课报名开始时间
     */
    @Column(name = "`enroll_start_time`")
    @JsonFormat(pattern = "yyyy-MM-dd H:mm", timezone = "GMT+8")
    private Date enrollStartDate;

    /**
     * 讨论课报名截止时间
     */
    @Column(name = "`enroll_end_time`")
    @JsonFormat(pattern = "yyyy-MM-dd H:mm", timezone = "GMT+8")
    private Date enrollEndDate;

    private static final long serialVersionUID = 1L;
}