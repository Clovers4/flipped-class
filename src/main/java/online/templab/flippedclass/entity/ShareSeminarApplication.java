package online.templab.flippedclass.entity;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

// TODO: 未与hzy组同步
@Getter
@Setter
@ToString
@Accessors(chain = true)
@Table(name = "`share_seminar_application`")
public class ShareSeminarApplication implements Serializable {

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
     * 从课程的教师id
     */
    @Column(name = "`sub_course_teacher_id`")
    private Long teacherId;

    /**
     * 请求状态，同意1、不同意0、未处理null
     */
    @Column(name = "`status`")
    private Integer status;

    /**
     * 对应的主课程实体
     */
    private Course mainCourse;

    /**
     * 对应的从课程实体
     */
    private Course subCourse;

    /**
     * 对应的主课程的老师
     */
    private Teacher mainTeacher;

    private static final long serialVersionUID = 1L;
}