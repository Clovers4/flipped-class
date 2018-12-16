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
@Table(name = "`klass_round`")
public class KlassRound implements Serializable {
    /**
     * 课程id
     */
    @Id
    @Column(name = "`klass_id`")
    private Long klassId;

    /**
     * 轮次id
     */
    @Id
    @Column(name = "`round_id`")
    private Long roundId;

    /**
     * 某班级，某轮次队伍报名次数限制
     */
    @Column(name = "`enroll_number`")
    private Integer enrollNumber;

    private static final long serialVersionUID = 1L;
}