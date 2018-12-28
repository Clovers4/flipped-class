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
@Table(name = "`teacher`")
public class Teacher implements Serializable {

    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 教师账户
     */
    @Column(name = "`account`")
    private String teacherNum;

    /**
     * 账户密码
     */
    @Column(name = "`password`")
    private String password;

    /**
     * 教师姓名
     */
    @Column(name = "`teacher_name`")
    private String teacherName;

    /**
     * 账号是否激活
     */
    @Column(name = "`is_active`")
    private Boolean activated;

    /**
     * 邮箱地址
     */
    @Column(name = "`email`")
    private String email;

    /**
     * 教授的课程
     */
    private List<Course> courses;

    private static final long serialVersionUID = 1L;
}