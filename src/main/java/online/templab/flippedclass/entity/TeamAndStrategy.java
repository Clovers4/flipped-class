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
@Table(name = "`team_and_strategy`")
public class TeamAndStrategy implements Serializable {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 策略所属类标识
     */
    @Id
    @Column(name = "`strategy_name`")
    private String strategyName;

    /**
     * 组队策略id
     */
    @Id
    @Column(name = "`strategy_id`")
    private Long strategyId;

    private static final long serialVersionUID = 1L;
}