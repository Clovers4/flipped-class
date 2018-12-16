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
@Table(name = "`question`")
public class Question implements Serializable {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`klass_seminar_id`")
    private Long klassSeminarId;

    /**
     * 问题所针对的发言id
     */
    @Column(name = "`attendance_id`")
    private Long attendanceId;

    /**
     * 提问小组的id
     */
    @Column(name = "`team_id`")
    private Long teamId;

    /**
     * 提问学生的id
     */
    @Column(name = "`student_id`")
    private Long studentId;

    /**
     * 是否被选中
     */
    @Column(name = "`is_selected`")
    private Integer isSelected;

    /**
     * 提问分数
     */
    @Column(name = "`score`")
    private BigDecimal score;

    private static final long serialVersionUID = 1L;
}