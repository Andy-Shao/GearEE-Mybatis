package org.example.mybatis.service;

import com.github.andyshao.mybatis.core.dto.Page;
import com.github.andyshao.mybatis.core.dto.PageOperation;
import com.github.andyshao.mybatis.core.dto.Pageable;
import org.example.mybatis.domain.User;
import org.example.mybatis.mapping.UserMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright(c) 2023/2/9
 * Encoding: UNIX UTF-8
 *
 * @author Andy.Shao
 */
@Service
public class UserService {
    @Autowired
    private UserMapping userMapping;

    @Transactional
    public Page<User> findAllPage(Pageable pageable) {
        final List<User> users = this.userMapping.findAllPage(pageable);
        return PageOperation.build(users, pageable);
    }
}
