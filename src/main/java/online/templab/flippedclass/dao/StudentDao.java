package online.templab.flippedclass.dao;

import com.github.pagehelper.Page;
import online.templab.flippedclass.entity.Student;
import online.templab.flippedclass.mapper.StudentMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author fj
 */
@Component
public interface StudentDao {

    /**
     * 插入一个 Student
     *
     * @param student
     * @return
     */
    int insert(Student student);

    /**
     * 删除某个 account 对应的学生
     *
     * @param studentNum
     * @return
     */
    int deleteByStudentNum(String studentNum);

    /**
     * 根据 primary key 更新学生（非空属性才更新）
     *
     * @param student
     * @return
     */
    int updateByPrimaryKeySelective(Student student);

    /**
     * 根据 account 更新学生（非空属性才更新）
     *
     * @param student
     * @return
     */
    int updateByStudentNumSelective(Student student);

    /**
     * 获得一个分页: 传入 rowBounds(pageNum,limit) ,返回一个 List<Student>
     *
     * @param record
     * @param rowBounds
     * @return
     */
    Page<Student> selectByRowBounds(Student record, RowBounds rowBounds);

    /**
     * 根据 studentNum 获取一个学生
     *
     * @param studentNum
     * @return
     */
    Student selectByStudentNum(String studentNum);

    /**
     * 根据 id 获取 一个学生信息
     *
     * @param id
     * @return
     */
    Student selectByPrimaryKey(Long id);
}
