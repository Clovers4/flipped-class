package online.templab.flippedclass.entity;

import java.io.Serializable;
import java.util.List;
import javax.annotation.security.DenyAll;
import javax.persistence.*;
import javax.xml.ws.soap.Addressing;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Table(name = "`team_or_strategy`")
public class TeamOrStrategy implements Serializable , CourseStrategy{
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 策略所属类的类名
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
        if("TeamAndStrategy".equals(this.strategyName)){
            for(int i = 0 ; i < this.courseStrategyList.size() ; ++i){
                if(!courseStrategyList.get(i).isValid(studentList)){
                    return false;
                }
            }
        }
        else if("TeamOrStrategy".equals(this.strategyName)){
            int orStrategyCount = this.courseStrategyList.size();
            if(orStrategyCount != 0){
                for(int i = 0 ; i < this.courseStrategyList.size(); ++i){
                    if(!this.courseStrategyList.get(i).isValid(studentList)){
                        --orStrategyCount;
                    }
                }
            }
            else{
                return true;
            }
            if(orStrategyCount == 0){
                return false;
            }
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

    @Override
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