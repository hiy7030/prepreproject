package com.prepreproject.dto;

import lombok.Getter;

import java.util.List;
@Getter
public class MultiResponseDto<T> {
    private List<T> dataList;
}
