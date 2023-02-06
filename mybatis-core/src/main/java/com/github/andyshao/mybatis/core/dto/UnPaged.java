package com.github.andyshao.mybatis.core.dto;

/**
 * 
 * 
 * Title: 代表不分页<br>
 * Descript:<br>
 * Copyright: Copryright(c) Feb 20, 2019<br>
 * Encoding: UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public enum UnPaged implements Pageable {
	INSTANCE;

	@Override
	public int getPageNumber() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getPageSize() {
		throw new UnsupportedOperationException();
	}

	@Override
	public long getOffset() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Pageable next() {
		return this;
	}

	@Override
	public Pageable previousOrFirst() {
		return this;
	}

	@Override
	public Pageable first() {
		return this;
	}

	@Override
	public boolean hasPrevious() {
		return false;
	}

	@Override
	public boolean isCountTotalSize() {
		return false;
	}

	@Override
	public boolean isPaged() {
		return false;
	}

}
