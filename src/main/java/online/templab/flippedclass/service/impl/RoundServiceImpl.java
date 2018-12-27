package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.dao.RoundDao;
import online.templab.flippedclass.entity.KlassRound;
import online.templab.flippedclass.entity.Round;
import online.templab.flippedclass.service.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Round 业务 实现类
 *
 * @author chenr
 */
@Service
public class RoundServiceImpl implements RoundService {

    @Autowired
    private RoundDao roundDao;

    @Override
    public Boolean  insert(Round round) {
        return roundDao.insert(round) == 1;
    }

    @Override
    public Boolean delete(Long roundId) {
        return roundDao.delete(roundId)==1;
    }

    @Override
    public Boolean update(Round round) {
        int line = roundDao.updateByRoundIdSelective(round);
        return line == 1;
    }

    @Override
    public Boolean updateKlassRound(KlassRound klassRound) {
        return roundDao.updateKlassRoundByPrimaryKey(klassRound) == 1;
    }

    @Override
    public List<Round> listByCourseId(Long courseId) {
        return roundDao.selectByCourseId(courseId);
    }

    @Override
    public List<Round> listByCourseIdKlassId(Long courseId, Long klassId) {
        return roundDao.selectByCourseIdKlassId(courseId, klassId);
    }

    @Override
    public Round get(Long roundId, Long courseId) {
        return roundDao.getOne(roundId,courseId);
    }

    @Override
    public KlassRound getKlassRound(Long klassId, Long roundId) {
        return roundDao.getKlassRound(klassId,roundId);
    }
}
