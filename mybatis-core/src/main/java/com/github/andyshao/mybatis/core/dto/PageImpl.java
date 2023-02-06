package com.github.andyshao.mybatis.core.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright(c) 2023/2/6
 * Encoding: UNIX UTF-8
 *
 * @author Andy.Shao
 */
@Builder
@Getter
public class PageImpl<T> implements Page<T>{
    private int pageSize;
    private int pageNumber;
    private List<T> content;
    private long totalElements;

    @Override
    public <U> Page<U> map(Function<? super T, ? extends U> converter) {
        final List<U> content = this.getContent().stream()
                .map(converter)
                .collect(Collectors.toList());
        return PageImpl.<U>builder()
                .pageSize(getPageSize())
                .pageNumber(getPageNumber())
                .content(content)
                .totalElements(getTotalElements())
                .build();
    }
}
