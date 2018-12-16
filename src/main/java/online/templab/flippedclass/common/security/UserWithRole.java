package online.templab.flippedclass.common.security;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用于存放username,password,role，进而方便了UserDetailService去区分登录用户的身份。
 *
 * @author wk
 */
@Data
@Accessors(chain = true)
public class UserWithRole {

    private String username;

    private String password;

    private String role;
}
