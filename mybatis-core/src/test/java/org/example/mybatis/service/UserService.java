package org.example.mybatis.service;

import com.github.andyshao.mybatis.core.dto.Page;
import com.github.andyshao.mybatis.core.dto.PageOperation;
import com.github.andyshao.mybatis.core.dto.Pageable;
import lombok.RequiredArgsConstructor;
import org.example.mybatis.domain.User;
import org.example.mybatis.mapping.UserMapping;

import java.util.List;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright(c) 2023/2/8
 * Encoding: UNIX UTF-8
 *
 * @author Andy.Shao
 */
@RequiredArgsConstructor
public class UserService {
    private final UserMapping userMapping;

    Page<User> findByCreateUserPage(String createUser, Pageable pageable) {
        final List<User> users = this.userMapping.findByCreateUserPage(createUser, pageable);
        return PageOperation.build(users, pageable);
    }
}
