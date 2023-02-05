package com.github.andyshao.mybatis.core.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.github.andyshao.mybatis.core.mapping.impl.Mappers;

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
 */
public interface CoreMapping<ENTITY> {
	@Select(Mappers.GENERIC_DAO_QUERY)
	int countAll();
	
	@Select(Mappers.GENERIC_DAO_QUERY)
	List<ENTITY> findAll();
}
