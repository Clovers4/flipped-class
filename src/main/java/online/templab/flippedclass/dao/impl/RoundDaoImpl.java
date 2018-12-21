package online.templab.flippedclass.dao.impl;

import online.templab.flippedclass.dao.RoundDao;
import online.templab.flippedclass.entity.KlassRound;
import online.templab.flippedclass.entity.Round;
import online.templab.flippedclass.mapper.KlassRoundMapper;
import online.templab.flippedclass.mapper.RoundMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * Round Dao 实现类
 *
 * @author chenr
 */
@Mapper
@Component
public class RoundDaoImpl implements RoundDao {

    @Autowired
    RoundMapper roundMapper;

    @Autowired
    KlassRoundMapper klassRoundMapper;

    @Override
    public int insert(Round round) {
        boolean success =true;
        for(KlassRound klassRound:round.getKlassRounds()){
            int successLoop = klassRoundMapper.insertSelective(klassRound);
            if(successLoop != 1){
                success = false;
                break;
            }
        }
        if(roundMapper.insertSelective(round)==1 && success == true){
            return 1;
        }
        return 0;
    }

    @Override
    public int updateByRoundIdSelective(Round round) {
        List<KlassRound> klassRoundList = round.getKlassRounds();
        boolean success = true;
        for(KlassRound klassRound:klassRoundList){
            int successLoop = klassRoundMapper.updateByRoundIdKlassIdSelective(klassRound);
            if( successLoop < 1){
                success = false;
                break;
            }
        }

        if((roundMapper.updateByRoundIdSelective(round))==1 &&success==true){
            return 1;
        }
        return 0;
    }

    @Override
    public List<Round> selectByCourseId(Long courseId) {
        return roundMapper.selectByCourseId(courseId);
    }

    @Override
    public List<Round> selectByCourseIdKlassId(Long courseId, Long klassId) {
        List<Round> roundList = roundMapper.selectByCourseId(courseId);
        for(int i = 0 ; i < roundList.size();++i){
            roundList.get(i).setKlassRounds(klassRoundMapper.selectByRoundIdKlassId(roundList.get(i).getId(),klassId));
        }
        return roundList;
    }

    @Override
    public Round getOne(Long id) {
        Round round = roundMapper.getOne(id);
        round.setKlassRounds(klassRoundMapper.selectByRoundId(id));
        return round;
    }
}
