package online.templab.flippedclass.dao;

import online.templab.flippedclass.entity.Course;
import online.templab.flippedclass.entity.ShareSeminarApplication;
import online.templab.flippedclass.entity.ShareTeamApplication;

import java.util.List;

/**
 * @author jh
 * @author fj
 */
public interface ShareApplicationDao {
    /**
     * 主课程发送共享组队请求给从课程
     * @param shareTeamApplication
     * @return
     */
    Boolean insertShareTeamApplication(ShareTeamApplication shareTeamApplication);

    /**
     * 主课程发送共享讨论课请求给从课程
     * @param shareSeminarApplication
     * @return
     */
    Boolean insertShareSeminarApplication(ShareSeminarApplication shareSeminarApplication);

    /**
     * 从课程处理共享组队请求
     * @param shareTeamApplicationId
     * @param accept  true表示接受，false表示拒绝
     * @return
     */
    Boolean updateShareTeamApplication(Long shareTeamApplicationId,Boolean accept);

    /**
     * 从课程处理共享讨论课请求
     * @param shareSeminarApplicationId
     * @param accept  true表示接受，false表示拒绝
     * @return
     */
    Boolean updateShareSeminarApplication(Long shareSeminarApplicationId,Boolean accept);

    /**
     * 主课程删除共享组队请求
     * @param subCourseId
     * @return
     */
    Boolean deleteShareTeamApplication(Long subCourseId);

    /**
     * 主课程删除共享讨论课请求
     * @param subCourseId
     * @return
     */
    Boolean deleteShareSeminarApplication(Long subCourseId);

    /**
     * 传入要查询的 courseId,返回与这个 course 共享分组的 courses,并且这些 courses 是从课程（也可能自己就是从课程）
     *
     * @param id
     * @return
     */
    List<Course> selectShareTeamSubCourse(Long id);

    /**
     * 传入要查询的 courseId,返回与这个 course 共享讨论课的 courses,并且这些 courses 是从课程（也可能自己就是从课程）
     *
     * @param id
     * @return
     */
    List<Course> selectShareSeminarSubCourse(Long id);

    /**
     * 传入要查询的 courseId,返回与这个 course 共享分组的 course,并且这个 course 是主课程（也可能自己就是主课程）
     *
     * @param id
     * @return
     */
    Course selectShareTeamMainCourseByPrimaryKey(Long id);

    /**
     * 传入要查询的 courseId,返回与这个 course 共享讨论课的 course,并且这个 course 是主课程（也可能自己就是主课程）
     *
     * @param id
     * @return
     */
    Course selectShareSeminarMainCourseByPrimaryKey(Long id);

    /**
     * 得到当前老师的所有共享讨论课记录
     * @param teacherId
     * @return
     */
    List<ShareSeminarApplication> selectShareSeminarApplication(Long teacherId);

    /**
     * 得到当前老师的所有共享组队记录
     * @param teacherId
     * @return
     */
    List<ShareTeamApplication> selectShareTeamApplication(Long teacherId);
}
