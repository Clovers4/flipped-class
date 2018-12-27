package online.templab.flippedclass.dao;

import com.github.pagehelper.Page;
import online.templab.flippedclass.entity.Teacher;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author wk
 */
public interface TeacherDao  {

    /**
     * 根据 account 更新属性不为 null 的值
     *
     * @param record
     * @return
     */
    int updateByAccountSelective(Teacher record);

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param record
     * @return
     */
    int insertSelective(Teacher record);

    /**
     * 根据account作为条件进行删除，查询条件使用等号
     * 并且级联删除相关的表的数据
     *
     * @param account
     * @return
     */
    int deleteByAccount(String  account);

    /**
     * 根据主键更新属性不为null的值
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Teacher record);

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param key
     * @return
     */
    Teacher selectByPrimaryKey(Object key);

    /**
     * 根据 teacherNum(工号,又名account) 查询出一个Teacher
     *
     * @param teacherNum
     * @return
     */
    Teacher selectByTeacherNum(String teacherNum);

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     *
     * @param record
     * @return
     */
    List<Teacher> select(Teacher record);

    /**
     * 根据实体属性和RowBounds进行分页查询
     *
     * @param record
     * @param rowBounds
     * @return
     */
    Page<Teacher> selectByRowBounds(Teacher record, RowBounds rowBounds);
}
