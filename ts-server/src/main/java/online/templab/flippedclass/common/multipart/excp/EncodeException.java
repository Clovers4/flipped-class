package online.templab.flippedclass.common.multipart.excp;

/**
 * 编码操作异常
 * 用于包装执行new String(file.getFilename().getBytes("UTF-8"), "ISO-8859-1");
 * 抛出的UnsupportedEncodingException,
 *
 * @author wk
 */
public class EncodeException extends RuntimeException {

    public EncodeException(String message) {
        super(message);
    }

    public EncodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
