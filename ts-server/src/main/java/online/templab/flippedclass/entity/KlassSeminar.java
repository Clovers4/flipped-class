package online.templab.flippedclass.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author wk
 */
@Data
@Accessors(chain = true)
@Table(name = "`klass_seminar`")
public class KlassSeminar implements Serializable {

    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 班级id
     */
    @Column(name = "`klass_id`")
    private Long klassId;

    /**
     * 讨论课id
     */
    @Column(name = "`seminar_id`")
    private Long seminarId;

    /**
     * 报告截止时间
     */
    @Column(name = "`report_ddl`")
    private Date reportDeadline;

    /**
     * 讨论课所处状态，未开始0，正在进行1，已结束2，暂停3
     */
    @Column(name = "`status`")
    private Integer state;

    /**
     * 报名的展示
     */
    private List<Attendance> attendances;

    /**
     * 讨论课本体
     */
    private Seminar seminar;

    private static final long serialVersionUID = 1L;
}