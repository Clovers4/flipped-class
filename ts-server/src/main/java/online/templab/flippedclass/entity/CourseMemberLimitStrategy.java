package online.templab.flippedclass.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@Table(name = "`course_member_limit_strategy`")
public class CourseMemberLimitStrategy implements Serializable, CourseStrategy {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 课程id
     */
    @Column(name = "`course_id`")
    private Long courseId;

    /**
     * 队伍中选该课程最少人数
     */
    @Column(name = "`min_member`")
    private Integer min;

    /**
     * 队伍中选该课程最多人数
     */
    @Column(name = "`max_member`")
    private Integer max;

    private static final long serialVersionUID = 1L;

    @Override
    public Boolean isValid(List<Student> studentList) {
        Map<Long, Integer> maps = new HashMap<>();
        maps.put(this.courseId,0);
        for(Student student : studentList){
            for(Long cid : student.getCouseIdList()){
                if(maps.containsKey(cid)){
                    maps.put(cid,maps.get(cid)+1);
                }
            }
        }

        Integer memberNum = maps.get(this.courseId);

        if(min == null && max == null)
        {
            return true;
        }
        else if(min == null){
            if(memberNum <=max){
                return true;
            }
        }
        else if(max == null) {
            if (memberNum >= min) {
                return true;
            }
        }
        else {
            if (memberNum >= min && memberNum <=max){
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
        return this.courseId;
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