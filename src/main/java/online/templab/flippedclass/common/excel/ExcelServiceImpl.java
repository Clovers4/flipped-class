package online.templab.flippedclass.common.excel;

import online.templab.flippedclass.entity.Student;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wk
 */
@Service
public class ExcelServiceImpl implements ExcelService {

    @Override
    public List<Student> loadStudentList(MultipartFile file) {
        List<Student> students = new ArrayList<>();

        try (Workbook workbook = ExcelUtil.loadFile(file)) {
            // 只获取第一张表
            Sheet sheet = workbook.getSheetAt(0);

            // 从第三行开始是数据
            for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);
                String account = row.getCell(0).getStringCellValue();
                String name = row.getCell(1).getStringCellValue();

                Student record = new Student()
                        .setStudentNum(account)
                        .setStudentName(name);
                students.add(record);
            }
        } catch (IOException e) {
            throw new LoadExcelException(e.getMessage());
        }
        return students;
    }

    @Override
    public void generateStudentExcel(List<Student> students, String filename) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        // TODO:是否生效?
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        for (int i = 0; i < students.size() + 1; i++) {
            Row row = sheet.createRow(i);
            if (i == 0) {
                row.createCell(0).setCellValue("学号");
                row.createCell(1).setCellValue("姓名");
                continue;
            }
            Student student = students.get(i - 1);
            row.createCell(0).setCellValue(student.getStudentNum());
            row.createCell(1).setCellValue(student.getStudentName());
        }

        // 将数据写入文件
        try (FileOutputStream out = new FileOutputStream(filename)) {
            workbook.write(out);
        } catch (IOException e) {
            throw new GenerateExcelException(e.getMessage());
        }
    }

}
