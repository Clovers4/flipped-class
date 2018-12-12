package online.templab.flippedclass.config;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author wk
 */
@Data
@Accessors(chain = true)
public class UserWithRole {

    private String username;

    private String password;

    private String role;
}
