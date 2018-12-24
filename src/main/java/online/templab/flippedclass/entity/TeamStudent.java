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
@Table(name = "`team_student`")
public class TeamStudent implements Serializable {
    /**
     * 队伍id
     */
    @Id
    @Column(name = "`team_id`")
    private Long teamId;

    /**
     * 学生id
     */
    @Id
    @Column(name = "`student_id`")
    private Long studentId;

    private static final long serialVersionUID = 1L;
}