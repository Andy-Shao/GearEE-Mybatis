package com.github.andyshao.mybatis.core.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

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
    public static enum Condition {
        NOT_LIKE("NOT LIKE"), LIKE("LIKE"), BETWEEN("BETWEEN"),
        NOT_BETWEEN("NOT BETWEEN"), EQUAL("="), NOT_EQUAL("&lt;&gt;"),
        IS_NULL("IS NULL"), IS_NOT_NULL("IS NOT NULL"), IN("IN"),
        NOT_IN("NOT IN"), GREATER("&gt;"), LESS("&lts"),
        GREATER_EQUAL("&gt;="), LESS_EQUAL("&lt;="), OR("OR");
        @Getter
        private final String expression;
        Condition(String exp) {
            this.expression = exp;
        }
    }
	private Condition condition;
    private String column;
    private Object value;
    private Object secondValue;
    private boolean noValue;
    private boolean singleValue;
    private boolean betweenValue;
    private boolean listValue;
    private boolean valid;
    private String typeHandler;
    private List<Criteria> criteriaList;
    private Conditional conditional;

    public static class CriteriaBuilder {
        private boolean valid = false;
        private List<Criteria> criteriaList = new ArrayList<>();
    }

    protected void addCriterion(Condition condition, Object value, String property) {
        if (value == null) {
            throw new RuntimeException("Value for " + property + " cannot be null");
        }
        if(!this.valid) {
            this.valid = true;
        }
        Criteria.CriteriaBuilder builder = Criteria.builder()
        		.conditional(this.conditional)
                .valid(true)
                .condition(condition)
                .value(value)
//                .column(this.conditional.getColumn(property))
                .column(property)
                .typeHandler(null);
        if(value instanceof List) {
            builder.listValue(true);
        } else {
            builder.singleValue(true);
        }
        this.criteriaList.add(builder.build());
    }

    protected void addCriterion(Condition condition, String property) {
        if (condition == null) {
            throw new RuntimeException("Value for condition cannot be null");
        }
        if(!this.valid) {
            this.valid = true;
        }
        this.criteriaList.add(Criteria.builder()
        		.conditional(this.conditional)
                .valid(true)
                .condition(condition)
                .typeHandler(null)
                .noValue(true)
                .column(this.conditional.getColumn(property))
                .build());
    }

    protected void addCriterion(Condition condition, Object value1, Object value2, String property) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("Between values for " + property + " cannot be null");
        }
        if(!this.valid) {
            this.valid = true;
        }
        this.criteriaList.add(Criteria.builder()
        		.conditional(this.conditional)
                .valid(true)
                .condition(condition)
                .value(value1)
                .secondValue(value2)
                .column(this.conditional.getColumn(property))
                .typeHandler(null)
                .betweenValue(true)
                .build());
    }

    public Criteria andIsNull(String property) {
        addCriterion(Condition.IS_NULL, property);
        return this;
    }

    public Criteria andIsNotNull(String property) {
        addCriterion(Condition.IS_NOT_NULL, property);
        return this;
    }

    public Criteria andEqualTo(Object value, String property) {
        addCriterion(Condition.EQUAL, value, property);
        return this;
    }

    public Criteria andNotEqualTo(Object value, String property) {
        addCriterion(Condition.NOT_EQUAL, value, property);
        return this;
    }

    public Criteria andGreaterThan(Object value, String property) {
        addCriterion(Condition.GREATER, value, property);
        return this;
    }

    public Criteria andGreaterThanOrEqualTo(Object value, String property) {
        addCriterion(Condition.GREATER_EQUAL, value, property);
        return this;
    }

    public Criteria andLessThan(Object value, String property) {
        addCriterion(Condition.LESS, value, property);
        return this;
    }

    public Criteria andLessThanOrEqualTo(Object value, String property) {
        addCriterion(Condition.LESS_EQUAL, value, property);
        return this;
    }

    public Criteria andIn(List<Object> values, String property) {
        addCriterion(Condition.IN, values, property);
        return this;
    }

    public Criteria andNotIn(List<Object> values, String property) {
        addCriterion(Condition.NOT_IN, values, property);
        return this;
    }

    public Criteria andBetween(Object value1, Object value2, String property) {
        addCriterion(Condition.BETWEEN, value1, value2, property);
        return this;
    }

    public Criteria andIdNotBetween(Object value1, Object value2, String property) {
        addCriterion(Condition.NOT_BETWEEN, value1, value2, property);
        return this;
    }

    public Criteria andLike(String value, String property) {
        addCriterion(Condition.LIKE, value, property);
        return this;
    }

    public Criteria andNotLike(String value, String property) {
        addCriterion(Condition.NOT_LIKE, value, property);
        return this;
    }
}
