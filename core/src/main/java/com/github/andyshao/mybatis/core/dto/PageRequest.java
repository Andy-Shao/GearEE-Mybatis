package com.github.andyshao.mybatis.core.dto;

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
public final class PageRequest {
	private PageRequest() {}
	
	public static Pageable of(int pageNumber, int pageSize, Conditional conditional, Sort sort) {
		return new PageableImpl.PageableImplBuilder()
				.pageNumber(pageNumber)
				.pageSize(pageSize)
				.conditional(conditional)
				.sort(sort)
				.build();
	}
	
	public static Pageable of(int pageNumber, int pagSize) {
		return of(pageNumber, pagSize, Conditional.excludeCondition(), Sort.unsorted());
	}
}
