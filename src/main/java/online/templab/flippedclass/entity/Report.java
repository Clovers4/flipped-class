package online.templab.flippedclass.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.experimental.Accessors;
import tk.mybatis.mapper.annotation.KeySql;

@Data
@Accessors(chain = true)
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    private Integer presentationId;

    private String filename;

    private String url;

    private Long score;

}