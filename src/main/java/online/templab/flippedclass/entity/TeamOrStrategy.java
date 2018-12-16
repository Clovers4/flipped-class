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
@Table(name = "`team_or_strategy`")
public class TeamOrStrategy implements Serializable {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * “或”组队策略1实现类名称
     */
    @Column(name = "`strategy_1_name`")
    private String strategy1Name;

    /**
     * “或”组队策略1_id
     */
    @Column(name = "`strategy_1_id`")
    private Long strategy1Id;

    /**
     * “或”组队策略2实现类名称
     */
    @Column(name = "`strategy_2_name`")
    private String strategy2Name;

    /**
     * “或”组队策略2_id
     */
    @Column(name = "`strategy_2_id`")
    private Long strategy2Id;

    private static final long serialVersionUID = 1L;
}