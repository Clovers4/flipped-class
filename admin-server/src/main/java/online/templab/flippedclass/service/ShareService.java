package online.templab.flippedclass.service;

import online.templab.flippedclass.entity.Course;
import online.templab.flippedclass.entity.ShareSeminarApplication;
import online.templab.flippedclass.entity.ShareTeamApplication;

import java.util.List;

/**
 * @author jh
 * @author fj
 */
public interface ShareService {

    /**
     * 主课程发送共享组队请求给从课程
     * @param shareTeamApplication
     * @return
     */
    Boolean sendShareTeamApplication(ShareTeamApplication shareTeamApplication);

    /**
     * 主课程发送共享讨论课请求给从课程
     * @param shareSeminarApplication
     * @return
     */
    Boolean sendShareSeminarApplication(ShareSeminarApplication shareSeminarApplication);

    /**
     * 从课程处理共享组队请求
     * @param shareTeamApplicationId
     * @param accept  true表示接受，false表示拒绝
     * @return
     */
    Boolean processShareTeamApplication(Long shareTeamApplicationId,Boolean accept);

    /**
     * 从课程处理共享讨论课请求
     * @param shareSeminarApplicationId
     * @param accept  true表示接受，false表示拒绝
     * @return
     */
    Boolean processShareSeminarApplication(Long shareSeminarApplicationId,Boolean accept);

    /**
     * 主课程取消共享组队
     * @param subCourseId
     * @return
     */
    Boolean cancelShareTeamApplication(Long subCourseId);

    /**
     * 主课程取消共享讨论课
     * @param subCourseId
     * @return
     */
    Boolean cancelShareSeminarApplication(Long subCourseId);

    /**
     * 传入要查询的 courseId,返回与这个 course 共享分组的 courses,并且这些 courses 是从课程（也可能自己就是从课程）
     *
     * @param id
     * @return
     */
    List<Course> listShareTeamSubCourse(Long id);

    /**
     * 传入要查询的 courseId,返回与这个 course 共享讨论课的 courses,并且这些 courses 是从课程（也可能自己就是从课程）
     *
     * @param id
     * @return
     */
    List<Course> listShareSeminarSubCourse(Long id);

    /**
     * 传入要查询的 courseId,返回与这个 course 共享分组的 course,并且这个 course 是主课程（也可能自己就是主课程）
     *
     * @param id 即要查询的courseId
     * @return
     */
    Course getShareTeamMainCourse(Long id);

    /**
     * 传入要查询的 courseId,返回与这个 course 共享讨论课的 course,并且这个 course 是主课程（也可能自己就是主课程）
     *
     * @param id
     * @return
     */
    Course getShareSeminarMainCourse(Long id);

    /**
     * 根据 courseId 查看该课程可以共享给的课程list
     *
     * @param id
     * @param type ( 0代表讨论课，1代表组队)
     * @return
     */
    List<Course> listCanShareCourses(Long id, int type);

    /**
     * 得到当前老师的所有共享讨论课记录
     * @param teacherId
     * @return
     */
    List<ShareSeminarApplication> listShareSeminarApplication(Long teacherId);

    /**
     * 得到当前老师的所有共享组队记录
     * @param teacherId
     * @return
     */
    List<ShareTeamApplication> listShareTeamApplication(Long teacherId);
}
