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
@Table(name = "`conflict_course_strategy`")
public class ConflictCourseStrategy implements Serializable, CourseStrategy {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 冲突课程
     */
    @Id
    @Column(name = "`course_id`")
    private Long courseId;

    private static final long serialVersionUID = 1L;

    @Override
    public Boolean isValid(List<Student> studentList) {
        return null;
    }

    @Override
    public Long getMyStrategyId() {
        return null;
    }

    @Override
    public String getMyStrategyName() {
        return null;
    }

    @Override
    public void setMyStrategyName(String strategyName) {

    }

    @Override
    public void setMyStrategyId(Long strategyId) {

    }

    @Override
    public void setCourseStrategyList(List<CourseStrategy> courseStrategyList) {

    }

    @Override
    public List<CourseStrategy> getCourseStrategyList() {
        return null;
    }

    @Override
    public Long getMyCourseId() {
        return this.courseId;
    }

    @Override
    public Integer getMyMax() {
        return null;
    }

    @Override
    public Integer getMyMin() {
        return null;
    }

    @Override
    public Long getMyId() {
        return this.id;
    }

    @Override
    public void setMyId(Long id) {
        this.id = id;
    }

}