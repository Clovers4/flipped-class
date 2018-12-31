package online.templab.flippedclass.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author wk
 */
@Data
@Accessors(chain = true)
@Table(name = "`admin`")
public class Admin implements Serializable {

    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 账号
     */
    @Column(name = "`account`")
    private String adminName;

    /**
     * 密码
     */
    @Column(name = "`password`")
    private String password;

    private static final long serialVersionUID = 1L;
}