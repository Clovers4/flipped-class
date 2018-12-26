package online.templab.flippedclass.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author wk
 */
@Data
@Accessors(chain = true)
@Table(name = "`attendance`")
public class Attendance implements Serializable {

    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 讨论课（某班级）id
     */
    @Column(name = "`klass_seminar_id`")
    private Long klassSeminarId;

    /**
     * 队伍id
     */
    @Column(name = "`team_id`")
    private Long teamId;

    /**
     * 该队伍顺序
     */
    @Column(name = "`team_order`")
    private Integer sn;

    /**
     * 是否正在进行
     */
    @Column(name = "`is_present`")
    private Boolean presenting;

    /**
     * 提交的报告文件名
     */
    @Column(name = "`report_name`")
    private String reportName;

    /**
     * 提交的报告文件位置
     */
    @Column(name = "`report_url`")
    private String reportFile;

    /**
     * 提交的PPT文件名
     */
    @Column(name = "`ppt_name`")
    private String pptName;

    /**
     * 提交的PPT文件位置(展示文件的位置)
     */
    @Column(name = "`ppt_url`")
    private String preFile;

    /**
     * 对应的队伍
     */
    private Team team;

    private static final long serialVersionUID = 1L;
}