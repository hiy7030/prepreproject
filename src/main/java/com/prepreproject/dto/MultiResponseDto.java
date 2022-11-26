package com.prepreproject.dto;

import com.prepreproject.response.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
@Getter
@AllArgsConstructor
public class MultiResponseDto<T> {
    private List<T> data;
    private PageInfo pageInfo;
}
