package online.templab.flippedclass.service;

import com.github.pagehelper.Page;
import online.templab.flippedclass.entity.Student;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * Student 业务 接口类
 *
 * @author wk
 * @author fj
 */
public interface StudentService {

    /**
     * 插入一个 Student
     *
     * @param student
     * @return 成功则返回 true ,失败则返回 false
     */
    Boolean insert(Student student);

    /**
     * 删除某个 account 对应的学生
     *
     * @param studentNum
     * @return
     */
    Boolean delete(String studentNum);

    /**
     * 根据 id 更新一个 Student
     *
     * @param student
     * @return 成功则返回 true ,失败则返回 false
     */
    Boolean update(Student student);

    /**
     * 根据输入邮箱激活某个 student 的账户（激活账户部分、修改邮箱部分）
     *
     * @param id
     * @param password
     * @param email
     * @return
     */
    Boolean activate(Long id, String password, String email);

    /**
     * 根据 id 获取一个学生（账户设置部分）
     *
     * @param id
     * @return
     */
    Student getByPrimaryKey(Long id);

    /**
     * 根据 studentNum 重置某个 Student 的密码（管理员重置学生密码为默认密码）
     *
     * @param studentNum
     * @return
     */
    Boolean resetPassword(String studentNum);

    /**
     * 根据 StudentNum 修改某个账号的密码（学生修改密码部分）
     *
     * @param studentNum
     * @param password
     * @return
     */
    Boolean modifyPassword(String studentNum, String password);

    /**
     * 获得一个分页:
     * 传入查询条件,封装到 Student 对象,根据非 null 属性进行条件查询
     * 还要传入分页信息（）封装到 rowBounds(pageNum,limit) ,返回一个{@code List<Student>}
     *
     * @param target
     * @param rowBounds
     * @return
     */
    Page<Student> getPage(Student target, RowBounds rowBounds);

    /**
     * 根据 account 获得一个 Student
     *
     * @param studentNum
     * @return
     */
    Student getByStudentNum(String studentNum);
}
