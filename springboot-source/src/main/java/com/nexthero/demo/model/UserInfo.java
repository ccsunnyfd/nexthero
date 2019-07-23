package com.nexthero.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Date;

/**
 * UserInfo
 *
 * @version 1.0
 */
@Getter
@Setter
@Entity
public class UserInfo {
//    //没有password字段的视图
//    public interface WithoutPasswordView {};
//    //有password字段的视图
//    public interface WithPasswordView extends WithoutPasswordView {};


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,
            generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Column(name = "username", nullable = false, length = 40)
    private String username;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "nickname", nullable = false, length = 30)
    private String nickname;

    @Column(name = "mpWxOpenId", insertable = false, length = 200)
    private String mpWxOpenId;

    @Column(name = "appQqOpenId", insertable = false, length = 200)
    private String appQqOpenId;

    @Column(name = "appWxOpenId", insertable = false, length = 200)
    private String appWxOpenId;

    @Column(name = "appWeiboUId", insertable = false, length = 200)
    private String appWeiboUId;

    @Column(name = "sex", insertable = false, length = 1)
    private String sex;    // "m": male  "f": female

    @Column(name = "birthday", insertable = false)
    private Date birthday;

    @Column(name = "faceImage", insertable = false, columnDefinition = "varchar(255) default 'http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png'")
    private String faceImage;

    @Column(name = "isCertified", length = 1, insertable = false, columnDefinition = "int default 0")
    private Integer isCertified;

    @CreatedDate
    @Column(name = "registTime")
    private Date registTime;

    @Column(name = "userUniqueToken", length = 40, nullable = false)
    private String userUniqueToken;   // 使用UUID插入时生成
}
