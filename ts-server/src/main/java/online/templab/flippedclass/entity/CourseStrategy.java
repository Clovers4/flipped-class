package online.templab.flippedclass.entity;

import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.List;

@Accessors(chain = true)
public interface CourseStrategy {

    /**
     * 通过 team 下的所有学生判断是否合法
     * @param studentList
     * @return
     */
    Boolean isValid(List<Student> studentList);

    Long getMyStrategyId();

    String getMyStrategyName();

    void setMyStrategyName(String strategyName);

    void setMyStrategyId(Long strategyId);

    void setCourseStrategyList(List<CourseStrategy> courseStrategyList);

    List<CourseStrategy> getCourseStrategyList();

    Long getMyCourseId();

    Integer getMyMax();

    Integer getMyMin();

    Long getMyId();

    void setMyId(Long id);

}
