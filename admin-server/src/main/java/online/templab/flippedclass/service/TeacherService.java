package online.templab.flippedclass.service;

import com.github.pagehelper.Page;
import online.templab.flippedclass.entity.Teacher;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * Teacher 业务 接口类
 * <br/>传入的密码均为未加密的,由service来进行加密
 *
 * @author wk
 */
public interface TeacherService {

    /**
     * 插入一个 Teacher
     *
     * @param teacher
     * @return 成功则返回 true ,失败则返回 false
     */
    Boolean insert(Teacher teacher);

    /**
     * 删除某个 account 对应的教师
     *
     * @param account
     * @return
     */
    Boolean delete(String account);

    /**
     * 根据 id 更新一个 Teacher
     *
     * @param teacher
     * @return 成功则返回 true ,失败则返回 false
     */
    Boolean updateById(Teacher teacher);

    /**
     * 激活某个 Teacher 账号
     *
     * @param id
     * @param password
     * @param email
     * @return
     */
    Boolean activate(Long id, String password, String email);

    /**
     * 根据 account 重置某个 Teacher 的密码
     *
     * @param account
     * @return
     */
    Boolean resetPassword(String account);

    /**
     * 根据 teacherNum 修改某个账号的密码
     *
     * @param teacherNum
     * @param password
     * @return
     */
    Boolean modifyPassword(String teacherNum, String password);

    /**
     * 获得一个分页:
     * 传入查询条件,封装到 Teacher 对象,根据非 null 属性进行条件查询
     * 还要传入分页信息（）封装到 rowBounds(pageNum,limit) ,返回一个{@code List<Teacher>}
     *
     * @param target
     * @param rowBounds
     * @return
     */
    Page<Teacher> getPage(Teacher target,RowBounds rowBounds);

    /**
     * 根据 teacherNum 获得一个 Teacher
     *
     * @param teacherNum
     * @return
     */
    Teacher getByTeacherNum(String teacherNum);

}
