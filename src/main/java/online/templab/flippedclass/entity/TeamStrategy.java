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
     * 策略id
     */
    @Id
    @Column(name = "`strategy_id`")
    private Long strategyId;

    /**
     * 组队策略实现类名称
     */
    @Id
    @Column(name = "`strategy_name`")
    private String strategyName;

    private static final long serialVersionUID = 1L;
}