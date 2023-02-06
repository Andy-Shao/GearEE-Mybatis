package com.github.andyshao.mybatis.core.dto;

import com.github.andyshao.lang.StringOperation;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

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
@Deprecated
public class Sort implements Serializable{
	private static final long serialVersionUID = 5840662984877280542L;
	public static final Direction DEFAULT_DIRECTION = Direction.ASC;
	private static final Sort UNSORTED = Sort.by(null, DEFAULT_DIRECTION);
	private final List<Order> orders;
	private final Class<?> domainClass;
	
	public Sort(Class<?> domainClass, Direction direction, String...properties) {
		this(domainClass, direction, properties == null ? new ArrayList<>() : Arrays.asList(properties));
	}
	
	public Sort(Class<?> domainClass, Direction direction, List<String> properties) {
		if (properties == null || properties.isEmpty()) {
			throw new IllegalArgumentException("You have to provide at least one property to sort by!");
		}

		this.orders = new ArrayList<>(properties.size());
		this.domainClass = domainClass;

		for (String property : properties) {
			this.orders.add(new Order(this.domainClass, direction, property));
		}
	}
	
	public Sort(Class<?> domainClass, List<Order> orders) {
		Objects.requireNonNull(orders, "Orders must not be null!");
		this.orders = Collections.unmodifiableList(orders);
		this.domainClass = domainClass;
	}

	boolean isSorted() {
		return !orders.isEmpty();
	}
	
	private Sort withDirection(Direction direction) {
		return Sort.by(this.domainClass, orders.stream()
				.map(it -> new Order(this.domainClass, direction, it.getProperty()))
				.collect(Collectors.toList()));
	}
	
	public Sort descending() {
		return withDirection(Direction.DESC);
	}
	
	public Sort ascending() {
		return withDirection(Direction.ASC);
	}
	
	public static Sort unsorted() {
		return UNSORTED;
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
	
	public static Sort by(Class<?> domainClass, Direction direction, String...properties) {
		Objects.requireNonNull(properties, "Properties must not be null!");
		return properties.length == 0 ? Sort.unsorted() : new Sort(domainClass, direction, properties);
	}
	
	public static Sort by(Class<?> domainClass, Order... orders) {
		Objects.requireNonNull(orders, "Orders must not be null!");
		return new Sort(domainClass, Arrays.asList(orders));
	}
	
	public static Sort by(Class<?> domainClass, List<Order> orders) {

		Objects.requireNonNull(orders, "Orders must not be null!");

		return orders.isEmpty() ? Sort.unsorted() : new Sort(domainClass, orders);
	}
	
	@Getter
	@EqualsAndHashCode
	public static class Order implements Serializable {
		private static final long serialVersionUID = 1927098285908243830L;
		private static final boolean DEFAULT_IGNORE_CASE = false;
		private static final NullHandling DEFAULT_NULL_HANDLING = NullHandling.NATIVE;
		
		private final Direction direction;
		private final String property;
		private final boolean ignoreCase;
		private final NullHandling nullHandling;
		private final Class<?> domainClass;
		
		public Order(Class<?> domainClass, Direction direction, String property) {
			this(domainClass, direction, property, DEFAULT_IGNORE_CASE, DEFAULT_NULL_HANDLING);
		}
		
		public Order(Class<?> domainClass, Direction direction, String property, NullHandling nullHandlingHint) {
			this(domainClass, direction, property, DEFAULT_IGNORE_CASE, nullHandlingHint);
		}
		
		private Order(Class<?> domainClass, Direction direction, String property, boolean ignoreCase, NullHandling nullHandling) {

			if (!StringOperation.isTrimEmptyOrNull(property)) {
				throw new IllegalArgumentException("Property must not null or empty!");
			}

			this.direction = direction == null ? DEFAULT_DIRECTION : direction;
			this.property = property;
			this.ignoreCase = ignoreCase;
			this.nullHandling = nullHandling;
			this.domainClass = domainClass;
		}
		
		public static Order by(Class<?> domainClass, String property) {
			return new Order(domainClass, DEFAULT_DIRECTION, property);
		}
		
		public static Order asc(Class<?> domainClass, String property) {
			return new Order(domainClass, Direction.ASC, property, DEFAULT_NULL_HANDLING);
		}
		
		public static Order desc(Class<?> domainClass, String property) {
			return new Order(domainClass, Direction.DESC, property, DEFAULT_NULL_HANDLING);
		}
		
		public Order withProperty(String property) {
			return new Order(this.domainClass, this.direction, property, this.ignoreCase, this.nullHandling);
		}
		
		public Sort withProperties(String...properties) {
			return Sort.by(this.domainClass, this.direction, properties);
		}
		
		public Order ignoreCase() {
			return new Order(this.domainClass, direction, property, true, nullHandling);
		}
		
		public Order with(NullHandling nullHandling) {
			return new Order(this.domainClass, this.direction, this.property, this.ignoreCase, nullHandling);
		}
		
		public Order nullsFirst() {
			return with(NullHandling.NULLS_FIRST);
		}
		
		public Order nullsLast() {
			return with(NullHandling.NULLS_LAST);
		}
		
		public Order nullsNative() {
			return with(NullHandling.NATIVE);
		}
	}
	
	public static enum NullHandling {
		NATIVE, NULLS_FIRST, NULLS_LAST;
	}
}
