package com.github.andyshao.mybatis.core.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

/**
 * 
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Feb 21, 2019<br>
 * Encoding: UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
@Builder(access = AccessLevel.PACKAGE)
@Getter
public class PageableImpl implements Pageable {
	private int pageNumber;
	private int pageSize;
	private long offset;
	@Builder.Default
	private boolean countTotalSize = true;
	@Override
	public Pageable next() {
		return PageRequest.builder(this.pageNumber, this.pageSize)
				.countTotalSize(this.countTotalSize)
				.build();
	}

	@Override
	public Pageable previousOrFirst() {
		if(hasPrevious()) {
			return PageRequest.builder(this.pageNumber, this.pageSize)
					.countTotalSize(this.countTotalSize)
					.build();
		}
		return this.first();
	}

	@Override
	public Pageable first() {
		if(this.pageNumber == 1) return this;
		return PageRequest.builder(1, this.pageSize)
				.countTotalSize(this.countTotalSize)
				.build();
	}
}
