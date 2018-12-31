package online.templab.flippedclass.dao;

import online.templab.flippedclass.FlippedClassApplicationTest;
import online.templab.flippedclass.entity.*;
import online.templab.flippedclass.mapper.*;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author chenr
 */
@Transactional
public class TeamDaoImplTest extends FlippedClassApplicationTest {

    @Autowired
    TeamDao teamDao;

    @Autowired
    MemberLimitStrategyMapper memberLimitStrategyMapper;

    @Autowired
    ConflictCourseStrategyMapper conflictCourseStrategyMapper;

    @Autowired
    CourseMemberLimitStrategyMapper courseMemberLimitStrategyMapper;

    @Autowired
    TeamAndStrategyMapper teamAndStrategyMapper;

    @Autowired
    TeamOrStrategyMapper teamOrStrategyMapper;

    @Autowired
    TeamStrategyMapper teamStrategyMapper;

    @Autowired
    TeamMapper teamMapper;



    public void createDataset(){
        memberLimitStrategyMapper.insert(new MemberLimitStrategy()
                                .setId((long)101)
                                .setMax(6)
                                .setMin(4)
        );
        courseMemberLimitStrategyMapper.insert(new CourseMemberLimitStrategy()
                                .setId((long)102)
                                .setCourseId((long)101)
                                .setMin(3)
        );
        courseMemberLimitStrategyMapper.insert(new CourseMemberLimitStrategy()
                .setId((long)103)
                .setCourseId((long)102)
                .setMin(3)
        );
        teamOrStrategyMapper.insert((new TeamOrStrategy()
                            .setId((long)102)
                            .setStrategyName("CourseMemberLimitStrategy")
                            .setStrategyId((long)102)
        ));
        teamOrStrategyMapper.insert((new TeamOrStrategy()
                .setId((long)102)
                .setStrategyName("CourseMemberLimitStrategy")
                .setStrategyId((long)103)
        ));
        teamAndStrategyMapper.insert((new TeamAndStrategy()
                .setId((long)103)
                .setStrategyName("TeamOrStrategy")
                .setStrategyId((long)102)
        ));
        teamAndStrategyMapper.insert((new TeamAndStrategy()
                .setId((long)103)
                .setStrategyName("MemberLimitStrategy")
                .setStrategyId((long)101)
        ));
        teamStrategyMapper.insert(new TeamStrategy()
                    .setCourseId((long)103)
                    .setStrategySerial(2)
                    .setStrategyName("TeamAndStrategy")
                    .setStrategyId((long)103)
        );
        teamMapper.insert(new Team()
                .setId((long)101)
                .setKlassId((long)101)
                .setCourseId((long)103)
                .setLeaderId((long)101)
                .setTeamName("a")
                .setSerial(1)
                .setStatus(0)
        );

    }

    @Test
    public void testSelectTeamValid() throws Exception{
        createDataset();
        Team team = teamDao.selectTeamValid((long)101);
        logger.info(team.toString());
        Assert.assertNotNull(team);
    }
}
