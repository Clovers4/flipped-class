package online.templab.flippedclass.common.multipart.excp;

/**
 * 无法找到存储文件 异常
 *
 * @author wk
 */
public class StorageFileNotFoundException extends StorageException {

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}