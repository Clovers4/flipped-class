package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.dao.RoundDao;
import online.templab.flippedclass.dao.RoundScoreDao;
import online.templab.flippedclass.dao.KlassSeminarDao;
import online.templab.flippedclass.dao.ScoreDao;
import online.templab.flippedclass.entity.*;
import online.templab.flippedclass.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author fj
 * @author jh
 */
@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    RoundScoreDao roundScoreDao;

    @Autowired
    ScoreDao scoreDao;

    @Autowired
    KlassSeminarDao klassSeminarDao;

    @Autowired
    RoundDao roundDao;

    @Override
    public Boolean updateRoundScore(Long roundId, Long klassId) {
        return roundScoreDao.updateByRoundIdKlassId(roundId, klassId);
    }

    @Override
    public List<Map<String, Object>> getRoundScores(List<Round> rounds, List<Team> teams) {
        return roundScoreDao.listRoundScores(rounds,teams);
    }

    @Override
    public List<Map<String, Object>> getByStudentIdCourseId(Long studentId, Long courseId) {
        return roundScoreDao.listByStudentId(studentId, courseId);
    }

    @Override
    public Boolean markerScore(SeminarScore seminarScore) {
        Boolean success = scoreDao.insert(seminarScore);
        KlassSeminar klassSeminar = klassSeminarDao.selectByPrimaryKey(seminarScore.getKlassSeminarId());
        roundScoreDao.updateByRoundIdKlassId(roundDao.getRoundIdByKlassSeminarId(seminarScore.getKlassSeminarId()),klassSeminar.getKlassId());
        return success;
    }

    @Override
    public Boolean update(SeminarScore seminarScore, Long seminarId, Long klassId) {
        return scoreDao.update(seminarScore, seminarId, klassId);
    }

    @Override
    public RoundScore getScoreOfRound(Long teamId, Long roundId) {
        return roundScoreDao.selectScoreOfRound(teamId,roundId);
    }

    @Override
    public SeminarScore getSeminarScore(Long klassSeminerId, Long teamId) {
        return scoreDao.selectSeminarScore(klassSeminerId,teamId);
    }
}
