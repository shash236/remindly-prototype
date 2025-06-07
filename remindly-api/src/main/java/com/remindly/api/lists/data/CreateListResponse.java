package com.remindly.api.lists.data;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateListResponse {
    private Long id;
    private String name;
    private String description;
    private List<ItemsDto> items;
}
