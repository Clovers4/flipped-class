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
@Table(name = "`klass_student`")
public class KlassStudent implements Serializable {
    /**
     * 班级id
     */
    @Id
    @Column(name = "`klass_id`")
    private Long klassId;

    /**
     * 学生id
     */
    @Id
    @Column(name = "`student_id`")
    private Long studentId;

    /**
     * 课程id
     */
    @Column(name = "`course_id`")
    private Long courseId;

    /**
     * 学生所在小组id
     */
    @Column(name = "`team_id`")
    private Long teamId;

    private static final long serialVersionUID = 1L;
}