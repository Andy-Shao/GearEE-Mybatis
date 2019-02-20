package com.github.andyshao.mybatis.core.dto;

import java.io.Serializable;

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
public class Sort implements Serializable{
	private static final long serialVersionUID = 5840662984877280542L;
	public static final Direction DEFAULT_DIRECTION = Direction.ASC;

	boolean isSorted() {
		//TODO
		return false;
	}
	public static Sort unsorted() {
		//TODO 
		return null;
	}
	
	public static enum Direction {
		ASC, DESC;
		public boolean isAscending() {
			return this.equals(ASC);
		}
		public boolean isDescending() {
			return this.equals(DESC);
		}
	}
}
