package online.templab.flippedclass.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import online.templab.flippedclass.entity.Klass;

/**
 * @author wk
 */
@Data
@Accessors(chain = true)
public class KlassCreateDTO {

    private Integer gradeNum;

    private Integer klassNum;

    private String klassTime;

    private String location;

    private Long courseId;

    public Klass getKlass() {
        return new Klass()
                .setGrade(gradeNum)
                .setSerial(klassNum)
                .setTime(klassTime)
                .setLocation(location)
                .setCourseId(courseId);
    }


}
