package online.templab.flippedclass.common.excel;

import online.templab.flippedclass.entity.Student;

import java.io.File;
import java.util.List;

/**
 * @author wk
 */
public interface ExcelService {

    /**
     * 加载学生名单文件(excel),返回List<Student>
     *
     * @param file
     * @return
     */
    List<Student> loadStudentList(File file);

    /**
     * TODO:测试用
     *
     * @param students
     * @return
     */
    void generateStudentExcel(List<Student> students,String filename);

}
