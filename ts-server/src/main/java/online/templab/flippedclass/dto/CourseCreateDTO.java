package online.templab.flippedclass.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import online.templab.flippedclass.entity.Course;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author wk
 */
@Data
@Accessors(chain = true)
public class CourseCreateDTO {

    private String courseName;

    private String intro;

    private Integer prePer;

    private Integer quePer;

    private Integer repPer;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date teamStartDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date teamEndDate;

    private Integer teamMax;
    private Integer teamMin;

    public Course getCourse() {
        return new Course()
                .setCourseName(courseName)
                .setIntroduction(intro)
                .setPrePercentage(prePer)
                .setQuesPercentage(quePer)
                .setReportPercentage(repPer)
                .setTeamStartDate(teamStartDate)
                .setTeamEndDate(teamEndDate);
    }

   /* TODO:待完整
   public MaxMinRegulation getMaxMinReg() {
        MaxMinRegulation maxMinReg = new MaxMinRegulation();
        maxMinReg.setMax(teamMax);
        maxMinReg.setMin(teamMin);
        return maxMinReg;
    }*/

}
