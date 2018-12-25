package online.templab.flippedclass.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Table(name = "`team_and_strategy`")
public class TeamAndStrategy implements Serializable, CourseStrategy {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 策略所属类标识
     */
    @Id
    @Column(name = "`strategy_name`")
    private String strategyName;

    /**
     * 组队策略id
     */
    @Id
    @Column(name = "`strategy_id`")
    private Long strategyId;

    private List<CourseStrategy> courseStrategyList;

    private static final long serialVersionUID = 1L;

    @Override
    public Boolean isValid(List<Student> studentList) {

        if(this.strategyName.equals("TeamAndStrategy")){
            for(int i = 0 ; i < this.courseStrategyList.size() ; ++i){
                if(!courseStrategyList.get(i).isValid(studentList)){
                    return false;
                }
            }
        }

        else if(this.strategyName.equals("TeamOrStrategy")){
            boolean orOne=true;
            boolean orTwo = true;
            if(courseStrategyList.size()!=0){
                orOne = courseStrategyList.get(0).isValid(studentList);
                if(courseStrategyList.size()>1){
                    orTwo = courseStrategyList.get(1).isValid(studentList);
                }
            }
            return (orOne || orTwo);
        }
        else{
            return courseStrategyList.get(0).isValid(studentList);
        }
        return true;
    }

    @Override
    public Long getMyStrategyId() {
        return this.strategyId;
    }

    @Override
    public String getMyStrategyName() {
        return this.strategyName;
    }

    @Override
    public void setMyStrategyName(String strategyName) {
        this.strategyName =strategyName;
    }

    @Override
    public void setMyStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public List<CourseStrategy> getCourseStrategyList() {
        return courseStrategyList;
    }

    @Override
    public void setCourseStrategyList(List<CourseStrategy> courseStrategyList) {
        this.courseStrategyList = courseStrategyList;
    }

    @Override
    public Long getMyCourseId() {
        return null;
    }
}