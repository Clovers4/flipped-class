package online.templab.flippedclass.entity;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
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
    private Byte roundSerial;

    /**
     * 本轮展示分数计算方法
     */
    @Column(name = "`presentation_score_method`")
    private Byte presentationScoreMethod;

    /**
     * 本轮报告分数计算方法
     */
    @Column(name = "`report_score_method`")
    private Byte reportScoreMethod;

    /**
     * 本轮提问分数计算方法
     */
    @Column(name = "`question_score_method`")
    private Byte questionScoreMethod;

    private static final long serialVersionUID = 1L;
}