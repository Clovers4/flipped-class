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

    /**
     * 对应的课程
     */
    private Course course;

    /**
     * 获得班级名,利用反射机制,使得在前端ftl页面上能够正常调用 klass.klassName
     *
     * @return
     */
    public String getKlassName() {
        return grade + "-" + serial + "班";
    }

    private static final long serialVersionUID = 1L;
}