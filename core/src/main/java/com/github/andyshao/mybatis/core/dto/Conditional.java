package com.github.andyshao.mybatis.core.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.andyshao.mybatis.core.mapping.impl.Mappers;

import lombok.Getter;
import lombok.Setter;

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
@Getter
public class Conditional implements Serializable{
	private static final long serialVersionUID = -8757030312995023290L;
	@Setter
    protected boolean distinct = false;
    protected final Class<?> domainClass;
    protected List<Criteria> oredCriteria = new ArrayList<>();
    private static volatile Conditional NO_CONDITIONAL;
    private volatile Map<String, String> columns;
    
    public Conditional(Class<?> domainClass) {
    	this.domainClass = domainClass;
    }
    
    private Conditional() {
    	this(null);
    }
    
    public static Conditional excludeCondition() {
    	if(NO_CONDITIONAL == null) {
    		synchronized (Conditional.class) {
    			if(NO_CONDITIONAL == null) NO_CONDITIONAL = new Conditional();
			}
    	}
    	return NO_CONDITIONAL;
    }

    public void or(Criteria criteria) {
        this.oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        this.oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (this.oredCriteria.size() == 0) {
            this.oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = Criteria.builder()
        		.conditional(this)
        		.build();
        return criteria;
    }

    public void clear() {
        this.oredCriteria.clear();
        this.distinct = false;
    }
    
    public String getColumn(String property) {
    	if(columns == null) {
    		synchronized (this) {
				if(columns == null) {
					this.columns = Mappers.getColumnNames(this.domainClass);
				}
			}
    	}
    	return columns.get(property);
    }
}
