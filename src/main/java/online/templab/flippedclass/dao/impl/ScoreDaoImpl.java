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
    public Boolean insert(SeminarScore seminarScore, Long seminarId) {
        seminarScore.setKlassSeminarId(klassSeminarMapper.selectOne(new KlassSeminar().setSeminarId(seminarId)).getId());
        int line = seminarScoreMapper.insert(seminarScore);
        return line == 1;
    }

    @Override
    public Boolean update(SeminarScore seminarScore, Long seminarId) {
        seminarScore.setKlassSeminarId(klassSeminarMapper.selectOne(new KlassSeminar().setSeminarId(seminarId)).getId());
        int line = seminarScoreMapper.updateByKlassSeminarId(seminarScore);
        return line == 1;
    }
}
