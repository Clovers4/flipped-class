package online.templab.flippedclass.common.email;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author wk
 */
@Data
@Accessors(chain = true)
public class Email implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 接收方邮件
     */
    private String toAddress;

    /**
     * 主题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String content;
}
