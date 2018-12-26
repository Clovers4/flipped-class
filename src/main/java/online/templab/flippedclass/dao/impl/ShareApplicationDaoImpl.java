package online.templab.flippedclass.dao.impl;

import online.templab.flippedclass.dao.ShareApplicationDao;
import online.templab.flippedclass.entity.*;
import online.templab.flippedclass.mapper.*;
import org.apache.commons.collections4.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

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
    KlassTeamMapper klassTeamMapper;

    @Autowired
    KlassMapper klassMapper;

    @Autowired
    TeamStudentMapper teamStudentMapper;

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
        if (courseMapper.selectByPrimaryKey(shareTeamApplication.getMainCourseId()).getTeamMainCourseId() != null) {
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
            //更新组队信息
            //删除从课程旧的队伍信息
            List<Long> klassIds = klassMapper.selectIdByCourseId(shareTeamApplication.getSubCourseId());
            for (Long klassId : klassIds) {
                klassTeamMapper.delete(new KlassTeam().setKlassId(klassId));
            }
            //新建队伍关系
            List<Team> teams = teamMapper.select(new Team().setCourseId(shareTeamApplication.getMainCourseId()));
            Map<Long,Integer> teamNumsOfKlass= new LinkedMap<>();
            for(Long klassId:klassIds){
                teamNumsOfKlass.put(klassId,0);
            }
            for (Team team : teams) {
                Map<Long,Integer> studentNumsOfKlass= new LinkedMap<>();
                int maxStudentNums=0;
                for(Long klassId:klassIds){
                    //判断team在各班的人数
                    int studentNum=teamStudentMapper.selectCountByKlassIdTeamId(klassId,team.getId()).intValue();
                    studentNumsOfKlass.put(klassId,studentNum);
                    if(maxStudentNums<studentNum) {
                        maxStudentNums=studentNum;
                    }
                }
                if(maxStudentNums==0){
                    continue;
                }
                //找出各班队伍数最少的且队伍人数最多的
                int min=Integer.MAX_VALUE;
                Long minKlassId=0L;
                for(Long klassId:klassIds){
                    if(studentNumsOfKlass.get(klassId)==maxStudentNums){
                        if(min>teamNumsOfKlass.get(klassId)){
                            min=teamNumsOfKlass.get(klassId);
                            minKlassId=klassId;
                        }
                    }
                }
                //建立该team与klass的联系
                klassTeamMapper.insert(new KlassTeam().setKlassId(minKlassId).setTeamId(team.getId()));
                //该班的team数加一
                teamNumsOfKlass.put(minKlassId,teamNumsOfKlass.get(minKlassId)+1);
            }
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
        Long subCourseId = 0L;
        try {
            subCourseId = shareTeamApplicationMapper.selectByPrimaryKey(shareTeamApplicationId).getSubCourseId();
        } catch (Exception e) {
            return false;
        }
        //删除从课程队伍信息
        List<Long> klassIds = klassMapper.selectIdByCourseId(subCourseId);
        for (Long klassId : klassIds) {
            klassTeamMapper.delete(new KlassTeam().setKlassId(klassId));
        }
        return true;
    }

    @Override
    public Boolean deleteShareSeminarApplication(Long shareSeminarApplicationId) {
        ShareSeminarApplication shareSeminarApplication;
        try {
            shareSeminarApplication = shareSeminarApplicationMapper.selectByPrimaryKey(shareSeminarApplicationId);
        } catch (Exception e) {
            return false;
        }
        //删除从课程讨论课信息
        Course course = courseMapper.selcetByCourseId(shareSeminarApplication.getSubCourseId());
        for (Klass klass : course.getKlassList()) {
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
