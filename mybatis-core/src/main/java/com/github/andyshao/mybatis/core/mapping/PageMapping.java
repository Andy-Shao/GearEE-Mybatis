package com.github.andyshao.mybatis.core.mapping;

import com.github.andyshao.mybatis.core.dto.Page;
import com.github.andyshao.mybatis.core.dto.Pageable;
import com.github.andyshao.mybatis.core.mapping.impl.Mappers;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * 
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Feb 20, 2019<br>
 * Encoding: UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 * @param <ENTITY> entity
 * @param <PK> primary key
 */
public interface PageMapping<ENTITY, PK extends Serializable> {
	Page<ENTITY> findByPage(@Param(Mappers.DEFAULT_PAGE_NAME) Pageable page);
//	Slice<ENTITY> findBySlice(@Param(Mappers.DEFAULT_PAGE_NAME) Pageable page);
}
