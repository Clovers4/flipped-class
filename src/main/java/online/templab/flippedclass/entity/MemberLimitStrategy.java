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
    private Integer minMember;

    /**
     * 最多人数
     */
    @Column(name = "`max_member`")
    private Integer maxMember;

    private static final long serialVersionUID = 1L;

    @Override
    public Boolean isValid(List<Student> studentList) {
        if(minMember == null && maxMember == null)
        {
            return true;
        }
        else if(minMember == null){
            if(studentList.size() <=maxMember){
                return true;
            }
        }
        else if(maxMember == null) {
            if (studentList.size() >= minMember) {
                return true;
            }
        }
        else {
            if (studentList.size() >= minMember && studentList.size() <=maxMember){
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
    public Long getMyCourseId() {
        return null;
    }

}