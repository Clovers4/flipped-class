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
@Table(name = "`course_member_limit_strategy`")
public class CourseMemberLimitStrategy implements Serializable {
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
     * 队伍中选该课程最少人数
     */
    @Column(name = "`min_member`")
    private Integer minMember;

    /**
     * 队伍中选该课程最多人数
     */
    @Column(name = "`max_member`")
    private Integer maxMember;

    private static final long serialVersionUID = 1L;
}