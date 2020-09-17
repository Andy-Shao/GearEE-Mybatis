package com.github.andyshao.mybatis.core.dto;

import java.util.Optional;

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
 */
public interface Pageable {
	static Pageable unPaged() {
		return UnPaged.INSTANCE;
	}
	default boolean isPaged() {
		return true;
	}
	
	default boolean isUnPaged() {
		return !isPaged();
	}
	
	int getPageNumber();
	int getPageSize();
	long getOffset();
	Sort getSort();
	Conditional getConditional();
	default Sort getSortOr(Sort sort) {
		return getSort().isSorted() ? getSort() : sort;
	}
	Pageable next();
	Pageable previousOrFirst();
	Pageable first();
	boolean hasPrevious();
	boolean isCountTotalSize();

	default Optional<Pageable> toOptional() {
		return isUnPaged() ? Optional.empty() : Optional.of(this);
	}
}
