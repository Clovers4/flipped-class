package online.templab.flippedclass.dto;
import online.templab.flippedclass.entity.ConflictCourseStrategy;
import online.templab.flippedclass.entity.CourseMemberLimitStrategy;
import online.templab.flippedclass.entity.MemberLimitStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xinyu Shi
 */
public class StrategyCreateDTO {

    private Integer min;
    private Integer max;
    private List<String> courseMemberLimitCourseId;
    private List<Integer> courseMemberLimitMax;
    private List<Integer> courseMemberLimitMin;
    private List<String> conflictCourses;

    public MemberLimitStrategy getMemberLimitStrategy()
    {
        MemberLimitStrategy memberLimitStrategy = new MemberLimitStrategy();
        memberLimitStrategy.setMax(max);
        memberLimitStrategy.setMin(min);
        return memberLimitStrategy;
    }

    public List<CourseMemberLimitStrategy> getCourseMemberLimitStrategy()
    {
        List<CourseMemberLimitStrategy> strategies = new ArrayList<>();
        int items = courseMemberLimitCourseId.size();
        for(int i=0;i<items; i++)
        {
            CourseMemberLimitStrategy courseMemberLimitStrategy = new CourseMemberLimitStrategy();
            courseMemberLimitStrategy.setCourseId(Long.valueOf(courseMemberLimitCourseId.get(i)));
            courseMemberLimitStrategy.setMax(courseMemberLimitMax.get(i));
            courseMemberLimitStrategy.setMin(courseMemberLimitMin.get(i));
            strategies.add(courseMemberLimitStrategy);
        }
        return strategies;
    }

    public ConflictCourseStrategy getConflictCourseStrategy()
    {
        ConflictCourseStrategy conflictCourseStrategy = new ConflictCourseStrategy();
        // TODO 不知道是什么东西
        //conflictCourseStrategy.setConflictCourses(conflictCourses);
        return conflictCourseStrategy;
    }

}
