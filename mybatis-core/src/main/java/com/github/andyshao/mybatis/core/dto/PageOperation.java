package com.github.andyshao.mybatis.core.dto;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * 
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Apr 30, 2019<br>
 * Encoding: UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public final class PageOperation {
	@SuppressWarnings("rawtypes")
	public static final Page<?> EMPTY = new Page() {

		@Override
		public int getPageNumber() {
			return 0;
		}

		@Override
		public int getPageSize() {
			return 0;
		}

		@Override
		public List getContent() {
			return Collections.emptyList();
		}

		@Override
		public Stream getContentStream() {
			return getContent().stream();
		}

		@Override
		public boolean isFirst() {
			return true;
		}

		@Override
		public boolean isLast() {
			return true;
		}

		@Override
		public boolean hasNext() {
			return false;
		}

		@Override
		public boolean hasPrevious() {
			return false;
		}

		@Override
		public Pageable nextPageable() {
			return null;
		}

		@Override
		public Pageable previousPageable() {
			return null;
		}

		@Override
		public int getTotalPages() {
			return 0;
		}

		@Override
		public long getTotalElements() {
			return 0;
		}

		@Override
		public Page map(Function converter) {
			return this;
		}
	};

	public static <T> Page<T> build(List<T> content, Pageable pageable) {
		final PageImpl.PageImplBuilder<T> page = PageImpl.builder();
		page.content(content);
		if(pageable instanceof UnPaged) {
			page.totalElements(content.size());
		} else {
			page.totalElements(pageable.getTotalSize())
				.pageNumber(pageable.getPageNumber())
				.pageSize(pageable.getPageSize());
		}
		return page.build();
	}
	
	private PageOperation() {}
}
