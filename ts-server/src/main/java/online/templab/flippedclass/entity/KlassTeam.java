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
@Table(name = "`klass_team`")
public class KlassTeam implements Serializable {
    /**
     * 班级id
     */
    @Id
    @Column(name = "`klass_id`")
    private Long klassId;

    /**
     * 队伍id
     */
    @Id
    @Column(name = "`team_id`")
    private Long teamId;

    private static final long serialVersionUID = 1L;
}