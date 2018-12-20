package online.templab.flippedclass.common.excel;

import online.templab.flippedclass.FlippedClassApplicationTest;
import online.templab.flippedclass.entity.Student;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelServiceTest extends FlippedClassApplicationTest {

    @Autowired
    ExcelService excelService;

    @Test
    public void loadExcel() {
        ClassPathResource resource = new ClassPathResource("excel/J2EE.xlsx");

//        File file = null;
//        try {
//            file = resource.getFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        List<Student> students = excelService.loadStudentList(file);
//        for (Student record : students) {
//            logger.info(record.toString());
//        }
    }

    @Test
    public void generateExcel() {
        List<Student> students = new ArrayList<>();
        students.add(new Student().setStudentNum("" + random.nextInt(100)).setStudentName("张三"));
        students.add(new Student().setStudentNum("" + random.nextInt(100)).setStudentName("张三"));
        students.add(new Student().setStudentNum("" + random.nextInt(100)).setStudentName("张三"));
        students.add(new Student().setStudentNum("" + random.nextInt(100)).setStudentName("张三"));
        String filename = "D:/students.xlsx";
        logger.info(filename);
        excelService.generateStudentExcel(students, filename);
    }
}
