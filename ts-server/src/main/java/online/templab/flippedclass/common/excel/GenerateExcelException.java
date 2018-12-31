package online.templab.flippedclass.common.excel;

/**
 * excel文件创建失败异常
 *
 * @author wk
 */
public class GenerateExcelException extends RuntimeException {
    public GenerateExcelException(String message) {
        super(message);
    }

    public GenerateExcelException(String message, Throwable cause) {
        super(message, cause);
    }
}
