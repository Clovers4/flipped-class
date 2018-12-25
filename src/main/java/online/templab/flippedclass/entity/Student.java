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
@Table(name = "`student`")
public class Student implements Serializable {

    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 学生账户
     */
    @Column(name = "`account`")
    private String studentNum;

    /**
     * 账户密码
     */
    @Column(name = "`password`")
    private String password;

    /**
     * 账号是否激活
     */
    @Column(name = "`is_active`")
    private Boolean activated;

    /**
     * 学生姓名
     */
    @Column(name = "`student_name`")
    private String studentName;

    /**
     * 邮箱地址
     */
    @Column(name = "`email`")
    private String email;

    private List<Long> couseIdList;

    private static final long serialVersionUID = 1L;
}