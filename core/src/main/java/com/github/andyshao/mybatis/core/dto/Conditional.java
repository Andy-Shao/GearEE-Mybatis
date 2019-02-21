package com.github.andyshao.mybatis.core.dto;

import java.util.ArrayList;
import java.util.List;

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
public class Conditional {
    @Setter
    protected boolean distinct = false;
    protected final Class<?> domainClass;
    protected List<Criteria> oredCriteria = new ArrayList<>();
    
    public Conditional(Class<?> domainClass) {
    	this.domainClass = domainClass;
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
    	// TODO
    	return null;
    }
}
