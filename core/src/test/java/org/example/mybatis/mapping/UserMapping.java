package org.example.mybatis.mapping;

import com.github.andyshao.mybatis.core.mapping.CoreMapping;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.example.mybatis.domain.User;

import java.util.List;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright(c) 2020/9/17
 * Encoding: UNIX UTF-8
 *
 * @author Andy.Shao
 */
@Mapper
public interface UserMapping extends CoreMapping<User> {

    @Select("SELECT * FROM user WHERE user_name=#{username}")
    @ResultMap("DEFAULT_RESULT_MAP")
    User findByUsername(@Param("username") String username);

    @Select("SELECT * FROM user")
    @ResultMap("DEFAULT_RESULT_MAP")
    List<User> findAllUsers();

    @Select("SELECT count(*) FROM user")
    int userNumber();
}
