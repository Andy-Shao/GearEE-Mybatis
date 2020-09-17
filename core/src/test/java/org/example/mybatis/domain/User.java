package org.example.mybatis.domain;

import com.github.andyshao.mybatis.core.annotation.Column;
import com.github.andyshao.mybatis.core.annotation.Entity;
import com.github.andyshao.mybatis.core.annotation.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright(c) 2020/9/17
 * Encoding: UNIX UTF-8
 *
 * @author Andy.Shao
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "username")
public class User {
    @Id
    @Column("user_name")
    private String username;
    private String password;
    @Column("create_user")
    private String createUser;
    @Column("create_time")
    private LocalDate createTime;
    @Column("update_user")
    private String updateUser;
    @Column("update_time")
    private LocalDate updateTime;
}
