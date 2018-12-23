package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.dao.RoundScoreDao;
import online.templab.flippedclass.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author jh
 */
@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    RoundScoreDao roundScoreDao;

    @Override
    public Boolean updateRoundScore(Long roundId,Long klassId) {
        return roundScoreDao.updateByRoundIdKlassId(roundId,klassId);
    }

    @Override
    public List<Map<String, Object>> getRoundScore(Long roundId, Long klassId) {
        return roundScoreDao.listRoundScore(roundId,klassId);
    }

    @Override
    public List<Map<String, Object>> getByStudentIdCourseId(Long studentId,Long courseId) {
        return roundScoreDao.listByStudentId(studentId,courseId);
    }
}
