package org.example.mybatis.domain;

import com.github.andyshao.mybatis.core.annotation.Column;
import com.github.andyshao.mybatis.core.annotation.Entity;
import com.github.andyshao.mybatis.core.annotation.Id;

import java.time.LocalTime;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright(c) 2020/9/17
 * Encoding: UNIX UTF-8
 *
 * @author Andy.Shao
 */
@Entity
public class User {
    @Id
    @Column("user_name")
    private String userName;
    private String password;
    @Column("create_user")
    private String createUser;
    @Column("create_time")
    private LocalTime createTime;
    @Column("update_user")
    private String updateUser;
    @Column("update_time")
    private LocalTime updateTime;
}
