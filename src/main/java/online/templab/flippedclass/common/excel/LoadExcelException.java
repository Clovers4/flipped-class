package online.templab.flippedclass.common.excel;

/**
 * excel文件加载失败异常
 *
 * @author wk
 */
public class LoadExcelException extends RuntimeException {
    public LoadExcelException(String message) {
        super(message);
    }

    public LoadExcelException(String message, Throwable cause) {
        super(message, cause);
    }
}
