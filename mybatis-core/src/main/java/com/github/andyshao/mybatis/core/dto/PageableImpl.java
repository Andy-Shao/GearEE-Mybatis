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
@Builder
@Getter
public class PageableImpl implements Pageable {
	private int pageNumber;
	private int pageSize;
	private long offset;
	@Builder.Default
	private Sort sort = Sort.unsorted();
	@Builder.Default
	private Conditional conditional = Conditional.excludeCondition();
	@Builder.Default
	private boolean countTotalSize = true;
	
//	public static class PageableImplBuilder {
//		private Sort sort = Sort.unsorted();
//		private Conditional conditional = Conditional.excludeCondition();
//	}
	
	@Override
	public Pageable next() {
		return new PageableImplBuilder()
				.pageNumber(this.pageNumber + 1)
				.pageSize(this.pageSize)
				.sort(this.sort)
				.conditional(this.conditional)
				.build();
	}

	@Override
	public Pageable previousOrFirst() {
		if(hasPrevious()) {
			return new PageableImplBuilder()
					.pageNumber(this.pageNumber - 1)
					.pageSize(this.pageSize)
					.sort(this.sort)
					.conditional(this.conditional)
					.build();
		}
		return this.first();
	}

	@Override
	public Pageable first() {
		if(this.pageNumber == 1) return this;
		return new PageableImplBuilder()
				.pageNumber(1)
				.pageSize(this.pageSize)
				.sort(this.sort)
				.conditional(this.conditional)
				.build();
	}

	@Override
	public boolean hasPrevious() {
		if(this.pageNumber > 1) return true;
		return false;
	}
}
