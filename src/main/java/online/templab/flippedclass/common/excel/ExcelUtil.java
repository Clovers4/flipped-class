package online.templab.flippedclass.common.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author wk
 */
public class ExcelUtil {

    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    public static Workbook loadFile(File file) {
        Workbook workbook = null;
        try {
            if (ExcelUtil.isExcel2007(file.getPath())) {
                workbook = new XSSFWorkbook(new FileInputStream(file));
            } else if (ExcelUtil.isExcel2003(file.getPath())) {
                workbook = new HSSFWorkbook(new FileInputStream(file));
            }
        } catch (IOException e) {
            throw new LoadExcelException(e.getMessage());
        }
        return workbook;
    }

}
