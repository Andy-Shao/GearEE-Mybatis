package com.github.andyshao.mybatis.core.dto;

import java.util.function.Function;

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
 * @param <T> data type
 */
public interface Page<T> extends Slice<T>{
	static <T> Page<T> empty() {
		return empty(Pageable.unpaged());
	}
	static <T> Page<T> empty(Pageable pageable) {
		//TODO 
		return null;
	}
	
	int getTotalPages();
	long getTotalElements();
	<U> Page<U> map(Function<? super T, ? extends U> converter);
}
