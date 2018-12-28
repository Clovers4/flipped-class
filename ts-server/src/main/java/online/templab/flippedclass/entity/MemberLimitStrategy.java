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
@Table(name = "`member_limit_strategy`")
public class MemberLimitStrategy implements Serializable, CourseStrategy {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 最少人数
     */
    @Column(name = "`min_member`")
    private Integer min;

    /**
     * 最多人数
     */
    @Column(name = "`max_member`")
    private Integer max;

    private static final long serialVersionUID = 1L;

    @Override
    public Boolean isValid(List<Student> studentList) {
        if(min == null && max == null)
        {
            return true;
        }
        else if(min == null){
            if(studentList.size() <=max){
                return true;
            }
        }
        else if(max == null) {
            if (studentList.size() >= min) {
                return true;
            }
        }
        else {
            if (studentList.size() >= min && studentList.size() <=max){
                return true;
            }
        }

        return false;
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
        return null;
    }

    @Override
    public Integer getMyMax() {
        return this.max;
    }

    @Override
    public Integer getMyMin() {
        return this.min;
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