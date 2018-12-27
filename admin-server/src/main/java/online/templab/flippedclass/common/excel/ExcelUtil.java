package online.templab.flippedclass.common.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author wk
 */
public class ExcelUtil {

    public static boolean isExcel(String filePath) {
        return isExcel2003(filePath) || isExcel2007(filePath);
    }

    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    public static Workbook loadFile(MultipartFile file) {
        Workbook workbook = null;
        try {
            if (ExcelUtil.isExcel2007(file.getOriginalFilename())) {
                workbook = new XSSFWorkbook(file.getInputStream());
            } else if (ExcelUtil.isExcel2003(file.getOriginalFilename())) {
                workbook = new HSSFWorkbook(file.getInputStream());
            }
        } catch (IOException e) {
            throw new LoadExcelException(e.getMessage());
        }
        return workbook;
    }

}
