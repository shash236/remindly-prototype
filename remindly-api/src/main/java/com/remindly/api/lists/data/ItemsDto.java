package com.remindly.api.lists.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemsDto {
    private Long id;
    private String item;
    private boolean completed;
}
