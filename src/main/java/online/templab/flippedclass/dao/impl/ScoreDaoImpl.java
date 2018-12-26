package online.templab.flippedclass.dao.impl;

import online.templab.flippedclass.dao.KlassSeminarDao;
import online.templab.flippedclass.dao.ScoreDao;
import online.templab.flippedclass.entity.Course;
import online.templab.flippedclass.entity.KlassSeminar;
import online.templab.flippedclass.entity.Round;
import online.templab.flippedclass.entity.SeminarScore;
import online.templab.flippedclass.mapper.KlassSeminarMapper;
import online.templab.flippedclass.mapper.RoundScoreMapper;
import online.templab.flippedclass.mapper.SeminarScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author fj
 */
@Component
public class ScoreDaoImpl implements ScoreDao {

    @Autowired
    SeminarScoreMapper seminarScoreMapper;

    @Autowired
    KlassSeminarMapper klassSeminarMapper;
    
    @Override
    public Boolean insert(SeminarScore seminarScore) {
        int updateLine = seminarScoreMapper.updateByKlassSeminarId(seminarScore);
        // 如果存在就更新
        if (updateLine == 1) {
            return true;
        }
        // 如果不存在就插入
        int insertLine = seminarScoreMapper.insert(seminarScore);
        return insertLine == 1;
    }

    @Override
    public Boolean update(SeminarScore seminarScore, Long seminarId, Long klassId) {
        seminarScore.setKlassSeminarId(klassSeminarMapper.selectOne(new KlassSeminar().setSeminarId(seminarId).setKlassId(klassId)).getId());
        int line = seminarScoreMapper.updateByKlassSeminarId(seminarScore);
        return line == 1;
    }

    @Override
    public SeminarScore selectSeminarScore(Long klassSeminarId, Long teamId) {
        return seminarScoreMapper.selectOne(new SeminarScore().setKlassSeminarId(klassSeminarId).setTeamId(teamId));
    }
}
