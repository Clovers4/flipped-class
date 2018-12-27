package online.templab.flippedclass.dao;

import online.templab.flippedclass.entity.KlassStudent;

import java.util.List;

/**
 * @author jh
 */
public interface KlassStudentDao {

    /**
     * 查找所需的klassStudent
     * @param klassStudent
     * @return
     */
    KlassStudent selectOne(KlassStudent klassStudent);

    /**
     * 查找所需的klassStudent
     * @param klassStudent
     * @return
     */
    List<KlassStudent> select(KlassStudent klassStudent);

    /**
     * 删除目标klassStudent
     * @param klassStudent
     * @return
     */
    Integer delete(KlassStudent klassStudent);

    /**
     * 插入目标klassStudent
     * @param klassStudent
     * @return
     */
    Integer insert(KlassStudent klassStudent);

    /**
     * 通过kalssId和studenNum找到klassStudent
     * @param klassId
     * @param studentNum
     * @return
     */
    KlassStudent selectOneByStudentNum(Long klassId,String studentNum);
}
