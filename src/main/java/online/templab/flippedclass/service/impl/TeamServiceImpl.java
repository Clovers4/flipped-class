package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.dao.TeamDao;
import online.templab.flippedclass.entity.Team;
import online.templab.flippedclass.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Team 业务 实现类
 *
 * @author jh
 */
@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamDao teamDao;

    @Override
    public List<Team> listByCourseId(Long courseId){
        return teamDao.selectByCourseId(courseId);
    }
}
