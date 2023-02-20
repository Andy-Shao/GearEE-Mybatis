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
		return PageableImpl.builderTemple(pageNumber, pagSize)
				.countTotalSize(countTotalSize)
				.build();
	}

	public static Pageable of(int pageNumber, int pageSize) {
		return PageableImpl.builderTemple(pageNumber, pageSize).build();
	}

}
