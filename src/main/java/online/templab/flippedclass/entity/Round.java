package online.templab.flippedclass.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
/**
 * @author chenr
 */
@Data
@Accessors(chain = true)
@Table(name = "`round`")
public class Round implements Serializable {

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
     * 轮次序号
     */
    @Column(name = "`round_serial`")
    private Integer roundNum;

    /**
     * 本轮展示分数计算方法
     */
    @Column(name = "`presentation_score_method`")
    private Integer preScoreType;

    /**
     * 本轮报告分数计算方法
     */
    @Column(name = "`report_score_method`")
    private Integer reportScoreType;

    /**
     * 本轮提问分数计算方法
     */
    @Column(name = "`question_score_method`")
    private Integer quesScoreType;

    /**
     * 这一轮包含的讨论课
     */
    private List<Seminar> seminars;

    /**
     * 这一轮包含的 KlassRound , 内含每一个班级报名次数限制
     */
    private List<KlassRound> klassRounds;

    private static final long serialVersionUID = 1L;
}