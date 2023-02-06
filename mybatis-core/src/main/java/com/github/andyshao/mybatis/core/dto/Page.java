package com.github.andyshao.mybatis.core.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

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
public interface Page<T> {
	@SuppressWarnings("unchecked")
	static <T> Page<T> empty() {
		return (Page<T>) PageOperation.EMPTY;
	}

	int getPageNumber();

	int getPageSize();

	List<T> getContent();

	default  Stream<T> getContentStream() {
		return getContent().stream();
	}

	default boolean isFirst() {
		return getPageNumber() == 1;
	}

	default boolean isLast() {
		return getPageNumber() < getTotalPages();
	}

	default boolean hasNext(){
		return !isLast();
	}

	default boolean hasPrevious() {
		return !isFirst();
	}

	default Pageable nextPageable() {
		return PageRequest.of(getPageNumber(), getPageSize());
	}

	default Pageable previousPageable() {
		return PageRequest.of(getPageNumber()-1, getPageSize());
	}

	default int getTotalPages() {
		long totalPages = getTotalElements() / getPageSize();
		if(getTotalElements() % getPageSize() != 0) totalPages++;
		return (int) totalPages;
	}
	long getTotalElements();
	<U> Page<U> map(Function<? super T, ? extends U> converter);
}
