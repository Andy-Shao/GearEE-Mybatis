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
	@SuppressWarnings("unchecked")
	static <T> Page<T> empty() {
		return (Page<T>) PageOperation.EMPTY;
	}
	
	int getTotalPages();
	long getTotalElements();
	<U> Page<U> map(Function<? super T, ? extends U> converter);
}
