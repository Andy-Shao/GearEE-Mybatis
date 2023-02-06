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
	
	public static Pageable of(int pageNumber, int pagSize, boolean countTotalSize) {
		return builder(pageNumber, pagSize)
				.countTotalSize(countTotalSize)
				.build();
	}

	public static Pageable of(int pageNumber, int pageSize) {
		return builder(pageNumber, pageSize).build();
	}

	static PageableImpl.PageableImplBuilder builder(int pageNumber, int pageSize) {
		return PageableImpl.builder()
				.pageNumber(pageNumber)
				.pageSize(pageSize)
				.offset((long) (pageNumber - 1) * pageSize);
	}
}
