package com.github.andyshao.mybatis.core.mapping;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.andyshao.mybatis.core.dto.Conditional;
import com.github.andyshao.mybatis.core.dto.Sort;
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
 * @param <PK> primary key
 */
public interface ConditionalMapping<ENTITY, PK extends Serializable> {
	@Select(Mappers.GENERIC_DAO_QUERY)
    List<ENTITY> findByConditional(@Param(Mappers.DEFAULT_CONDITIONAL_NAME) Conditional conditional);
	@Select(Mappers.GENERIC_DAO_QUERY)
	List<ENTITY> findByConditionalAndSort(@Param(Mappers.DEFAULT_CONDITIONAL_NAME) Conditional conditional,
										  @Param(Mappers.DEFAULT_SORT_NAME) Sort sort);
	@Delete(Mappers.GENERIC_DAO_QUERY)
    int deleteByConditional(@Param(Mappers.DEFAULT_CONDITIONAL_NAME)Conditional conditional);
	@Update(Mappers.GENERIC_DAO_QUERY)
    int updateByConditional(@Param(Mappers.DEFAULT_SETS_NAME) Map<String, Object> sets,
                            @Param(Mappers.DEFAULT_CONDITIONAL_NAME)Conditional conditional);
}
