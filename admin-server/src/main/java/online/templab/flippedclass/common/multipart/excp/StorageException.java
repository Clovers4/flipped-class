package online.templab.flippedclass.common.multipart.excp;

/**
 * 文件存储 异常
 *
 * @author wk
 */
public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
