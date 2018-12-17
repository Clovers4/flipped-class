package online.templab.flippedclass.dao;

import com.github.pagehelper.Page;
import online.templab.flippedclass.entity.Student;
import online.templab.flippedclass.mapper.StudentMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author fj
 */
@Component
public class StudentDao {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 插入一个 Student
     *
     * @param student
     * @return
     */
    public int insert(Student student){
        return studentMapper.insertSelective(student);
    }

    /**
     * 删除某个 account 对应的学生
     *
     * @param account
     * @return
     */
    public int delete(String account){
        return studentMapper.delete(new Student().setAccount(account));
    }

    /**
     * 根据 id 更新一个 Student
     *
     * @param student
     * @return
     */
    public int update(Student student){
        return studentMapper.updateByPrimaryKeySelective(student);
    }

    /**
     * 根据输入邮箱激活某个 student 的账户
     *
     * @param id
     * @param password
     * @param email
     * @return
     */
    public int activate(Long id, String password, String email){
        return studentMapper.updateByPrimaryKeySelective(
                new Student()
                        .setId(id)
                        .setPassword(passwordEncoder.encode(password))
                        .setEmail(email)
                        .setActive(true)
        );
    }

    /**
     * 根据 account 重置某个 Student 的密码
     *
     * @param account
     * @param password
     * @return
     */
    public int resetPassword(String account, String password){
        return studentMapper.updateByAccountSelective(
                new Student()
                        .setAccount(account)
                        .setPassword(passwordEncoder.encode(password))
        );
    }

    /**
     * 根据 id 修改某个账号的密码
     *
     * @param id
     * @param password
     * @return
     */
    public int modifyPassword(Long id, String password){
        return  studentMapper.updateByPrimaryKeySelective(
                new Student()
                        .setId(id)
                        .setPassword(passwordEncoder.encode(password))
        );
    }

    /**
     * 获得一个分页: 传入 rowBounds(pageNum,limit) ,返回一个 List<Student>
     *
     * @param rowBounds
     * @return
     */
    public Page<Student> getPage(RowBounds rowBounds){
        return (Page<Student>) studentMapper.selectByRowBounds(new Student(), rowBounds);
    }

}
