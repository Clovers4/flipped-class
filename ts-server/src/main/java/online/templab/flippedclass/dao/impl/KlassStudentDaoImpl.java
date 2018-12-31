package online.templab.flippedclass.dao.impl;

import online.templab.flippedclass.dao.KlassStudentDao;
import online.templab.flippedclass.entity.KlassStudent;
import online.templab.flippedclass.mapper.KlassStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KlassStudentDaoImpl implements KlassStudentDao {

    @Autowired
    KlassStudentMapper klassStudentMapper;

    @Override
    public KlassStudent selectOne(KlassStudent klassStudent) {
        return klassStudentMapper.selectOne(klassStudent);
    }

    @Override
    public List<KlassStudent> select(KlassStudent klassStudent) {
        return klassStudentMapper.select(klassStudent);
    }

    @Override
    public Integer delete(KlassStudent klassStudent) {
        return klassStudentMapper.delete(klassStudent);
    }

    @Override
    public Integer insert(KlassStudent klassStudent) {
        return klassStudentMapper.insert(klassStudent);
    }

    @Override
    public KlassStudent selectOneByStudentNum(Long klassId,String studentNum) {
        return klassStudentMapper.selectOneByStudentNum(klassId,studentNum);
    }
}
