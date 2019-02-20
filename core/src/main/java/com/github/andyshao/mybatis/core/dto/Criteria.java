package com.github.andyshao.mybatis.core.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

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
@Builder
public class Criteria {
	private String condition;
    private  String column;
    private Object value;
    private Object secondValue;
    private boolean noValue;
    private boolean singleValue;
    private boolean betweenValue;
    private boolean listValue;
    private boolean valid;
    private String typeHandler;
    private List<Criteria> criteriaList;

    public static class CriteriaBuilder {
        private boolean valid = false;
        private List<Criteria> criteriaList = new ArrayList<>();
    }

    protected void addCriterion(String condition, Object value, String property) {
        if (value == null) {
            throw new RuntimeException("Value for " + property + " cannot be null");
        }
        if(!this.valid) {
            this.valid = true;
        }
        Criteria.CriteriaBuilder builder = Criteria.builder()
                .valid(true)
                .condition(condition)
                .value(value)
                .column(property)
                .typeHandler(null);
        if(value instanceof List) {
            builder.listValue(true);
        } else {
            builder.singleValue(true);
        }
        this.criteriaList.add(builder.build());
    }

    protected void addCriterion(String condition, String property) {
        if (condition == null) {
            throw new RuntimeException("Value for condition cannot be null");
        }
        if(!this.valid) {
            this.valid = true;
        }
        this.criteriaList.add(Criteria.builder()
                .valid(true)
                .condition(condition)
                .typeHandler(null)
                .noValue(true)
                .column(property)
                .build());
    }

    protected void addCriterion(String condition, Object value1, Object value2, String property) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("Between values for " + property + " cannot be null");
        }
        if(!this.valid) {
            this.valid = true;
        }
        this.criteriaList.add(Criteria.builder()
                .valid(true)
                .condition(condition)
                .value(value1)
                .secondValue(value2)
                .column(property)
                .typeHandler(null)
                .betweenValue(true)
                .build());
    }

    public Criteria andIsNull(String property) {
        addCriterion("is null", property);
        return this;
    }

    public Criteria andIsNotNull(String property) {
        addCriterion("is not null", property);
        return this;
    }

    public Criteria andEqualTo(Object value, String property) {
        addCriterion("=", value, property);
        return this;
    }

    public Criteria andNotEqualTo(Object value, String property) {
        addCriterion("<>", value, property);
        return this;
    }

    public Criteria andGreaterThan(Object value, String property) {
        addCriterion(">", value, property);
        return this;
    }

    public Criteria andGreaterThanOrEqualTo(Object value, String property) {
        addCriterion(">=", value, property);
        return this;
    }

    public Criteria andLessThan(Object value, String property) {
        addCriterion("<", value, property);
        return this;
    }

    public Criteria andLessThanOrEqualTo(Object value, String property) {
        addCriterion("<=", value, property);
        return this;
    }

    public Criteria andIn(List<Object> values, String property) {
        addCriterion("in", values, property);
        return this;
    }

    public Criteria andNotIn(List<Object> values, String property) {
        addCriterion("not in", values, property);
        return this;
    }

    public Criteria andBetween(Object value1, Object value2, String property) {
        addCriterion("between", value1, value2, property);
        return this;
    }

    public Criteria andIdNotBetween(Object value1, Object value2, String property) {
        addCriterion("not between", value1, value2, property);
        return this;
    }

    public Criteria andLike(String value, String property) {
        addCriterion("like", value, property);
        return this;
    }

    public Criteria andNotLike(String value, String property) {
        addCriterion("not like", value, property);
        return this;
    }
}
