package online.templab.flippedclass.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Table(name = "`team_strategy`")
public class TeamStrategy implements Serializable , CourseStrategy{
    /**
     * 课程id
     */
    @Id
    @Column(name = "`course_id`")
    private Long courseId;

    /**
     * 课程组队策略序号
     */
    @Id
    @Column(name = "`strategy_serial`")
    private Integer strategySerial;

    /**
     * 策略所属类的类名
     */
    @Column(name = "`strategy_name`")
    private String strategyName;

    /**
     * 策略id
     */
    @Column(name = "`strategy_id`")
    private Long strategyId;

    private List<CourseStrategy> courseStrategyList;

    private static final long serialVersionUID = 1L;

    @Override
    public Boolean isValid(List<Student> studentList) {

        if("ConflictCourseStrategy".equals(this.strategyName)){
            Map<Long, Integer> maps = new HashMap<>();

            for(CourseStrategy courseStrategy :this.courseStrategyList){
                maps.put(courseStrategy.getMyCourseId(),0);
            }


            for(Student student : studentList){
                for(Long cid : student.getCouseIdList()){
                    if(maps.containsKey(cid)){
                        maps.put(cid,maps.get(cid)+1);
                    }
                }
            }

            int countCourse = 0;
            for (Map.Entry<Long, Integer> entry : maps.entrySet()) {
                if(entry.getValue()>0) {
                    ++countCourse;
                }
            }
            if(countCourse > 1){
                return false;
            }

        }
        else if("TeamAndStrategy".equals(this.strategyName)){
            for(int i = 0 ; i < this.courseStrategyList.size() ; ++i){
                if(!this.courseStrategyList.get(i).isValid(studentList)){
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
            return this.courseStrategyList.get(0).isValid(studentList);
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
        return this.courseStrategyList;
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
        return null;
    }

    @Override
    public void setMyId(Long id) {
    }

}