package online.templab.flippedclass.common.excel;

import online.templab.flippedclass.entity.Student;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author wk
 */
public interface ExcelService {

    /**
     * 加载学生名单文件(excel),返回{@code List<Student>},student只包含studentNum(学号,又称account)
     *
     * @param file
     * @return
     */
    List<Student> loadStudentList(MultipartFile file);

    /**
     * 创建学生分数表（测试状态）,已废弃的功能点
     *
     * @param students
     * @return
     */
    void generateStudentExcel(List<Student> students,String filename);

}
