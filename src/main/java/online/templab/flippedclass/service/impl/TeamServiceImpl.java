package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.dao.TeamDao;
import online.templab.flippedclass.entity.Team;
import online.templab.flippedclass.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamDao teamDao;

    @Override
    public List<Team> listByCourseId(Long courseId){
        return teamDao.selectByCourseId(courseId);
    }
}
