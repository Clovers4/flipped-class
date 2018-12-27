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
    public Boolean cancelShareTeamApplication(Long subCourseId) {
        return shareApplicationDao.deleteShareTeamApplication(subCourseId);
    }

    @Override
    public Boolean cancelShareSeminarApplication(Long subCourseId) {
        return shareApplicationDao.deleteShareSeminarApplication(subCourseId);
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

    @Override
    public List<ShareSeminarApplication> listShareSeminarApplication(Long teacherId) {
        return shareApplicationDao.selectShareSeminarApplication(teacherId);
    }

    @Override
    public List<ShareTeamApplication> listShareTeamApplication(Long teacherId) {
        return shareApplicationDao.selectShareTeamApplication(teacherId);
    }
}
