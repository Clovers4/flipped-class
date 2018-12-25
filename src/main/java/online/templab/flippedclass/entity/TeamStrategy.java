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
@Table(name = "`team_strategy`")
public class TeamStrategy implements Serializable {
    /**
     * 课程id
     */
    @Id
    @Column(name = "`course_id`")
    private Long courseId;

    /**
     * 课程组队策略序号
     */
    @Id
    @Column(name = "`strategy_serial`")
    private Byte strategySerial;

    /**
     * 策略所属类的类名
     */
    @Column(name = "`strategy_name`")
    private String strategyName;

    /**
     * 策略id
     */
    @Column(name = "`strategy_id`")
    private Long strategyId;

    private static final long serialVersionUID = 1L;
}