package online.templab.flippedclass.dao.impl;

import online.templab.flippedclass.dao.ShareApplicationDao;
import online.templab.flippedclass.entity.*;
import online.templab.flippedclass.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Component
public class ShareApplicationDaoImpl implements ShareApplicationDao {

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    ShareTeamApplicationMapper shareTeamApplicationMapper;

    @Autowired
    ShareSeminarApplicationMapper shareSeminarApplicationMapper;

    @Autowired
    SeminarMapper seminarMapper;

    @Autowired
    KlassSeminarMapper klassSeminarMapper;

    @Autowired
    TeamMapper teamMapper;

    @Autowired
    KlassStudentMapper klassStudentMapper;

    @Override
    public Boolean insertShareTeamApplication(ShareTeamApplication shareTeamApplication) {
        //判断是否有效请求
        if (courseMapper.selectByPrimaryKey(shareTeamApplication.getMainCourseId()).getTeamMainCourseId() != null) {
            return false;
        }
        return shareTeamApplicationMapper.insert(shareTeamApplication) == 1;
    }

    @Override
    public Boolean insertShareSeminarApplication(ShareSeminarApplication shareSeminarApplication) {
        //判断是否有效请求
        if (courseMapper.selectByPrimaryKey(shareSeminarApplication.getMainCourseId()).getSeminarMainCourseId() != null) {
            return false;
        }
        return shareSeminarApplicationMapper.insert(shareSeminarApplication) == 1;
    }

    @Override
    public Boolean updateShareTeamApplication(Long shareTeamApplicationId, Boolean accept) {
        ShareTeamApplication shareTeamApplication = shareTeamApplicationMapper.selectByPrimaryKey(shareTeamApplicationId);
        //判断是否有效请求,主课程是否变为从课程
        if (shareTeamApplication.getMainCourse().getTeamMainCourseId() != null) {
            return false;
        }
        int line = 0;
        //如果接受，更新请求记录和course表，更新队伍信息
        if (accept) {
            line = shareTeamApplicationMapper.updateByPrimaryKeySelective(new ShareTeamApplication()
                    .setId(shareTeamApplicationId)
                    .setStatus(1));
            courseMapper.updateByPrimaryKeySelective(new Course()
                    .setId(shareTeamApplication.getSubCourseId())
                    .setTeamMainCourseId(shareTeamApplication.getMainCourseId()));

            //TODO：更新组队信息
        }
        //如果不接受，更新请求记录
        else {
            line = shareTeamApplicationMapper.updateByPrimaryKeySelective(new ShareTeamApplication()
                    .setId(shareTeamApplicationId)
                    .setStatus(0));
        }
        return line == 1;
    }

    @Override
    public Boolean updateShareSeminarApplication(Long shareSeminarApplicationId, Boolean accept) {
        ShareSeminarApplication shareSeminarApplication = shareSeminarApplicationMapper.selectByPrimaryKey(shareSeminarApplicationId);
        //判断是否有效请求,主课程是否变为从课程
        if (courseMapper.selectByPrimaryKey(shareSeminarApplication.getMainCourseId()).getTeamMainCourseId() != null) {
            return false;
        }
        int line = 0;
        //如果接受，更新请求记录和course表，更新队伍信息
        if (accept) {
            line = shareSeminarApplicationMapper.updateByPrimaryKeySelective(new ShareSeminarApplication()
                    .setId(shareSeminarApplicationId)
                    .setStatus(1));
            courseMapper.updateByPrimaryKeySelective(new Course()
                    .setId(shareSeminarApplication.getSubCourseId())
                    .setSeminarMainCourseId(shareSeminarApplication.getMainCourseId()));
            //更新讨论课信息
            Course course = courseMapper.selcetByCourseId(shareSeminarApplication.getSubCourseId());
            seminarMapper.delete(new Seminar().setCourseId(shareSeminarApplication.getSubCourseId()));
            for (Klass klass : course.getKlassList()) {
                klassSeminarMapper.delete(new KlassSeminar().setKlassId(klass.getId()));
            }
            //加入讨论课
            List<Seminar> seminars = seminarMapper.select(new Seminar().setCourseId(shareSeminarApplication.getMainCourseId()));
            for (Seminar seminar : seminars) {
                for (Klass klass : course.getKlassList()) {
                    klassSeminarMapper.insert(new KlassSeminar()
                            .setKlassId(klass.getId())
                            .setSeminarId(seminar.getId())
                            .setState(0));
                }
            }
        }
        //如果不接受，更新请求记录
        else {
            line = shareSeminarApplicationMapper.updateByPrimaryKeySelective(new ShareSeminarApplication()
                    .setId(shareSeminarApplicationId)
                    .setStatus(0));
        }
        return line == 1;
    }

    @Override
    public Boolean deleteShareTeamApplication(Long shareTeamApplicationId) {
        Long subCourseId=0L;
        try{
        subCourseId = shareTeamApplicationMapper.selectByPrimaryKey(shareTeamApplicationId).getSubCourseId();
        }
        catch (Exception e){
            return false;
        }
        //删除从课程队伍信息
        teamMapper.delete(new Team().setCourseId(subCourseId));
        Example example=new Example(KlassStudent.class);
        example.createCriteria().andCondition("course_id=",subCourseId);
        klassStudentMapper.updateByExample(new KlassStudent().setTeamId(null),example);
        return true;
    }

    @Override
    public Boolean deleteShareSeminarApplication(Long shareSeminarApplicationId) {
        ShareSeminarApplication shareSeminarApplication;
        try {
        shareSeminarApplication = shareSeminarApplicationMapper.selectByPrimaryKey(shareSeminarApplicationId);
        }
        catch (Exception e){
            return false;
        }
        //删除从课程讨论课信息
        Course course=courseMapper.selcetByCourseId(shareSeminarApplication.getSubCourseId());
        for(Klass klass:course.getKlassList()){
            klassSeminarMapper.delete(new KlassSeminar().setKlassId(klass.getId()));
        }
        return true;
    }

    @Override
    public List<Course> selectShareTeamSubCourse(Long id) {
        List<Course> courseList = courseMapper.select(new Course().setTeamMainCourseId(id));
        return courseList;
    }

    @Override
    public List<Course> selectShareSeminarSubCourse(Long id) {
        List<Course> courseList = courseMapper.select(new Course().setSeminarMainCourseId(id));
        return courseList;
    }

    @Override
    public Course selectShareTeamMainCourseByPrimaryKey(Long id) {
        // 如果传入 id 是从课程id 直接通过从课程拿到主课程id， 再去拿主课程
        Course subCourse = courseMapper.selectByPrimaryKey(id);
        Long teamMainCourseId = subCourse.getTeamMainCourseId();
        return courseMapper.selectByPrimaryKey(teamMainCourseId);
    }

    @Override
    public Course selectShareSeminarMainCourseByPrimaryKey(Long id) {
        // 如果传入 id 是从课程id 直接通过从课程拿到主课程id， 再去拿主课程
        Course subCourse = courseMapper.selectByPrimaryKey(id);
        Long seminarMainCourseId = subCourse.getSeminarMainCourseId();
        return courseMapper.selectByPrimaryKey(seminarMainCourseId);
    }
}
