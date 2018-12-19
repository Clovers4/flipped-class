package online.templab.flippedclass.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author wk
 */
@Data
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
    private Integer serial;

    /**
     * 上课时间
     */
    @Column(name = "`klass_time`")
    private String time;

    /**
     * 上课地点
     */
    @Column(name = "`klass_location`")
    private String location;

    private static final long serialVersionUID = 1L;
}