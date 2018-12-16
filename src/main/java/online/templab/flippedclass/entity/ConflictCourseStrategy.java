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
@Table(name = "`conflict_course_strategy`")
public class ConflictCourseStrategy implements Serializable {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 冲突课程1
     */
    @Column(name = "`course_1_id`")
    private Long course1Id;

    /**
     * 冲突课程2
     */
    @Column(name = "`course_2_id`")
    private Long course2Id;

    private static final long serialVersionUID = 1L;
}