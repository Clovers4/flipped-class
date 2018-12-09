package online.templab.flippedclass.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Table(name = "`teacher`")
public class Teacher implements Serializable {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`account`")
    private String account;

    @Column(name = "`password`")
    private String password;

    @Column(name = "`name`")
    private String name;

    @Column(name = "`email`")
    private String email;

    @Column(name = "`activative`")
    private Boolean activative;

    private static final long serialVersionUID = 1L;
}