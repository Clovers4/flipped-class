package online.templab.flippedclass.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@Table(name = "`team`")
public class Team implements Serializable {

    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 班级序号
     */
    @Column(name = "`klass_id`")
    private Long klassId;

    /**
     * 课程序号
     */
    @Column(name = "`course_id`")
    private Long courseId;

    /**
     * 队长的学生id
     */
    @Column(name = "`leader_id`")
    private Long leaderId;

    /**
     * 队伍名称
     */
    @Column(name = "`team_name`")
    private String teamName;

    /**
     * 队伍序号
     */
    @Column(name = "`team_serial`")
    private Integer serial;

    /**
     * 队伍状态，不合法0、合法1、审核中2
     */
    @Column(name = "`status`")
    private Integer status;

    /**
     * 队长
     */
    private Student leader;

    /**
     *  队伍成员（不包括队长）
     */
    private List<Student> students;

    private static final long serialVersionUID = 1L;
}