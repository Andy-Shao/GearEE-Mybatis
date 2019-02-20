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
 * @param <T> data type
 * 
 * @author Andy.Shao
 *
 */
public interface Slice<T> {
	int getNumber();
	int getSize();
	int getNumberOfElements();
	List<T> getContent();
	Stream<T> getContentStream();
	boolean isFirst();
	boolean isLast();
	boolean hasNext();
	boolean hasPrevious();
	Sort getSort();
	Conditional getConditional();
	default Pageable getPageable() {
		return PageRequest.of(this.getNumber(), this.getSize(), this.getConditional(), this.getSort());
	}
	Pageable nexPageable();
	Pageable previousPageable();
	<U> Slice<U> map(Function<? super T, ? extends U> converter);
}
