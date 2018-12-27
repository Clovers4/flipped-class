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
@Table(name = "`team_valid_application`")
public class TeamValidApplication implements Serializable {

    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 小组id
     */
    @Column(name = "`team_id`")
    private Long teamId;

    /**
     * 教师id
     */
    @Column(name = "`teacher_id`")
    private Long teacherId;

    /**
     * 申请理由
     */
    @Column(name = "`reason`")
    private String content;

    /**
     * 请求状态，同意1、不同意0、未处理null
     */
    @Column(name = "`status`")
    private Integer status;

    /**
     * 该申请的队伍
     */
    private Team team;

    private static final long serialVersionUID = 1L;
}