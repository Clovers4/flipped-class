package online.templab.flippedclass.entity;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@Table(name = "`member_limit_strategy`")
public class MemberLimitStrategy implements Serializable {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 最少人数
     */
    @Column(name = "`min_member`")
    private Integer minMember;

    /**
     * 最多人数
     */
    @Column(name = "`max_member`")
    private Integer maxMember;

    private static final long serialVersionUID = 1L;
}