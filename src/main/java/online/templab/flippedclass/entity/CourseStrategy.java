package online.templab.flippedclass.entity;

import java.util.List;

public interface CourseStrategy {
    /**
     * 通过 team 下的所有学生判断是否合法
     * @param studentList
     * @return
     */
    public Boolean isValid(List<Student> studentList);
}
