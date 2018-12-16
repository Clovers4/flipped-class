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
@Table(name = "`share_team_application`")
public class ShareTeamApplication implements Serializable {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 主课程id
     */
    @Column(name = "`main_course_id`")
    private Long mainCourseId;

    /**
     * 从课程id
     */
    @Column(name = "`sub_course_id`")
    private Long subCourseId;

    /**
     * 从课程老师id
     */
    @Column(name = "`sub_course_teacher_id`")
    private Long subCourseTeacherId;

    /**
     * 请求状态，同意1、不同意0、未处理null
     */
    @Column(name = "`status`")
    private Integer status;

    private static final long serialVersionUID = 1L;
}