package com.github.andyshao.mybatis.core.mapping;

import com.github.andyshao.mybatis.core.mapping.impl.Mappers;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.io.Serializable;
import java.util.List;

/**
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
public interface CurdMapping<ENTITY, PK extends Serializable> extends CoreMapping<ENTITY> {
    @Select(Mappers.GENERIC_DAO_QUERY)
    ENTITY findByPrimaryKey(@Param(Mappers.DEFAULT_PK_NAME) PK pk);
    @Insert(Mappers.GENERIC_DAO_QUERY)
    int save(@Param(Mappers.DEFAULT_ENTITY_NAME) ENTITY entity);
    @Insert(Mappers.GENERIC_DAO_QUERY)
    int saveList(@Param(Mappers.DEFAULT_ENTITY_LIST_NAME) List<ENTITY> entity);
    @Insert(Mappers.GENERIC_DAO_QUERY)
    int saveSelective(@Param(Mappers.DEFAULT_ENTITY_NAME) ENTITY entity);
    @Update(Mappers.GENERIC_DAO_QUERY)
    int deleteByPrimaryKey(@Param(Mappers.DEFAULT_PK_NAME) PK pk);
    @Update(Mappers.GENERIC_DAO_QUERY)
    int deleteByPrimaryKeyList(@Param(Mappers.DEFAULT_PK_LIST_NAME) List<PK> pk);
    @Update(Mappers.GENERIC_DAO_QUERY)
    int updateByPrimaryKey(@Param(Mappers.DEFAULT_ENTITY_NAME) ENTITY entity);
    @Update(Mappers.GENERIC_DAO_QUERY)
    int updateByPrimaryKeyList(@Param(Mappers.DEFAULT_ENTITY_LIST_NAME) List<ENTITY> entity);
    @Update(Mappers.GENERIC_DAO_QUERY)
    int updateByPrimaryKeySelective(@Param(Mappers.DEFAULT_ENTITY_NAME) ENTITY entity);
}
