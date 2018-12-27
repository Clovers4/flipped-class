package online.templab.flippedclass.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@Table(name = "`seminar_score`")
public class SeminarScore implements Serializable {
    /**
     * 班级讨论课id
     */
    @Id
    @Column(name = "`klass_seminar_id`")
    private Long klassSeminarId;

    /**
     * 小组id
     */
    @Id
    @Column(name = "`team_id`")
    private Long teamId;

    /**
     * 总成绩
     */
    @Column(name = "`total_score`")
    private BigDecimal totalScore;

    /**
     * 展示成绩
     */
    @Column(name = "`presentation_score`")
    private BigDecimal presentationScore;

    /**
     * 提问成绩
     */
    @Column(name = "`question_score`")
    private BigDecimal questionScore;

    /**
     * 报告成绩
     */
    @Column(name = "`report_score`")
    private BigDecimal reportScore;

    private static final long serialVersionUID = 1L;
}