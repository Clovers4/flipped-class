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
@Table(name = "`klass`")
public class Klass implements Serializable {
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
     * 年级
     */
    @Column(name = "`grade`")
    private Integer grade;

    /**
     * 班级序号
     */
    @Column(name = "`klass_serial`")
    private Integer klassSerial;

    /**
     * 上课时间
     */
    @Column(name = "`klass_time`")
    private String klassTime;

    /**
     * 上课地点
     */
    @Column(name = "`klass_location`")
    private String klassLocation;

    private static final long serialVersionUID = 1L;
}