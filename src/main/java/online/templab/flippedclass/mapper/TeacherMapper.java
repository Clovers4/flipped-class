package online.templab.flippedclass.mapper;

import online.templab.flippedclass.entity.Teacher;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@Component
public interface TeacherMapper extends Mapper<Teacher> {

    /**
     * 通过账号查询密码
     *
     * @param account
     * @return
     */
    String getPassword(String account);
}