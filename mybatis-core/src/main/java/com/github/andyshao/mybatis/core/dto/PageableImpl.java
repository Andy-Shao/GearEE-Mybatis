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
	@Builder.Default
	private long totalSize = 0;

	static PageableImplBuilder builderTemple(int pageNumber, int pageSize) {
		return PageableImpl.builder()
				.pageNumber(pageNumber)
				.pageSize(pageSize)
				.offset((long) (pageNumber - 1) * pageSize);
	}

	public static class PageableImplBuilder {}

	@Override
	public Pageable next() {
		return builderTemple(this.pageNumber, this.pageSize)
				.countTotalSize(this.countTotalSize)
				.build();
	}

	@Override
	public Pageable previousOrFirst() {
		if(hasPrevious()) {
			return builderTemple(this.pageNumber, this.pageSize)
					.countTotalSize(this.countTotalSize)
					.build();
		}
		return this.first();
	}

	@Override
	public Pageable first() {
		if(this.pageNumber == 1) return this;
		return builderTemple(1, this.pageSize)
				.countTotalSize(this.countTotalSize)
				.build();
	}

	@Override
	public long getTotalSize() {
		return this.totalSize;
	}

	@Override
	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}
}
