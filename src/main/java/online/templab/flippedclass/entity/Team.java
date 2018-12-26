package online.templab.flippedclass.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author chenr
 */
@Data
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
     * 班级序号
     */
    @Column(name = "`klass_serial`")
    private Byte klassSerial;

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

    /**
     *
     * 所有成员
     */
    private List<Student> allStudents;

    /**
     * 对应的班级
     */
    private Klass klass;

    /**
     * 这个课程下的分组策略
     */
    private List<TeamStrategy> teamStrategyList;

    private static String[] teamStatus = new String[]{"不合法", "合法", "审核中"};

    /**
     * 返回给前端ftl使用,以字符串的形式获取TeamStatus
     * @return
     */
    public String getTeamStatus() {
        return teamStatus[status];
    }

    private static final long serialVersionUID = 1L;
}