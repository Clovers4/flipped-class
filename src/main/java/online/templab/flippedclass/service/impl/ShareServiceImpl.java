package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.dao.CourseDao;
import online.templab.flippedclass.dao.ShareApplicationDao;
import online.templab.flippedclass.entity.Course;
import online.templab.flippedclass.entity.ShareSeminarApplication;
import online.templab.flippedclass.entity.ShareTeamApplication;
import online.templab.flippedclass.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShareServiceImpl implements ShareService {

    @Autowired
    private ShareApplicationDao shareApplicationDao;

    @Autowired
    private CourseDao courseDao;

    @Override
    public Boolean sendShareTeamApplication(ShareTeamApplication shareTeamApplication) {
        return shareApplicationDao.insertShareTeamApplication(shareTeamApplication);
    }

    @Override
    public Boolean sendShareSeminarApplication(ShareSeminarApplication shareSeminarApplication) {
        return shareApplicationDao.insertShareSeminarApplication(shareSeminarApplication);
    }

    @Override
    public Boolean processShareTeamApplication(Long shareTeamApplicationId, Boolean accept) {
        return shareApplicationDao.updateShareTeamApplication(shareTeamApplicationId,accept);
    }

    @Override
    public Boolean processShareSeminarApplication(Long shareSeminarApplicationId, Boolean accept) {
        return shareApplicationDao.updateShareSeminarApplication(shareSeminarApplicationId,accept);
    }

    @Override
    public Boolean cancelShareTeamApplication(Long shareTeamApplicationId) {
        return shareApplicationDao.deleteShareTeamApplication(shareTeamApplicationId);
    }

    @Override
    public Boolean cancelShareSeminarApplication(Long shareSeminarApplicationId) {
        return shareApplicationDao.deleteShareSeminarApplication(shareSeminarApplicationId);
    }

    @Override
    public List<Course> listShareTeamSubCourse(Long id){
        return shareApplicationDao.selectShareTeamSubCourse(id);
    }

    @Override
    public List<Course> listShareSeminarSubCourse(Long id){
        return shareApplicationDao.selectShareSeminarSubCourse(id);
    }

    @Override
    public Course getShareTeamMainCourse(Long id){
        return shareApplicationDao.selectShareTeamMainCourseByPrimaryKey(id);
    }

    @Override
    public Course getShareSeminarMainCourse(Long id){
        return shareApplicationDao.selectShareSeminarMainCourseByPrimaryKey(id);
    }

    @Override
    public List<Course> listCanShareCourses(Long id, int type) {
        return courseDao.selectCanShareCourseByPrimaryKey(id,type);
    }
}
