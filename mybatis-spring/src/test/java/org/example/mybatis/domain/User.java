package org.example.mybatis.domain;

import com.github.andyshao.mybatis.core.annotation.Column;
import com.github.andyshao.mybatis.core.annotation.Entity;
import com.github.andyshao.mybatis.core.annotation.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.JdbcType;

import java.time.LocalDateTime;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright(c) 2020/9/17
 * Encoding: UNIX UTF-8
 *
 * @author Andy.Shao
 */
@Entity(tableName = "user")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class User {
    @Id
    @Column("user_name")
    @EqualsAndHashCode.Include
    private String username;
    private String password;
    @Column("create_user")
    private String createUser;
    @Column(value = "create_time", jdbcType = JdbcType.TIMESTAMP)
    private LocalDateTime createTime;
    @Column("update_user")
    private String updateUser;
    @Column(value = "update_time", jdbcType = JdbcType.TIMESTAMP)
    private LocalDateTime updateTime;
}
