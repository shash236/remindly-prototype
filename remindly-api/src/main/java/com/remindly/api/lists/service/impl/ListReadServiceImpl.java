package com.remindly.api.lists.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.remindly.api.lists.data.CreateListResponse;
import com.remindly.api.lists.data.ItemsDto;
import com.remindly.api.lists.domain.Lists;
import com.remindly.api.lists.repository.ItemsRepository;
import com.remindly.api.lists.repository.ListsRepository;
import com.remindly.api.lists.service.ListReadService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListReadServiceImpl implements ListReadService {

    private final ListsRepository listsRepository;
    private final ItemsRepository itemsRepository;

    @Override
    public List<CreateListResponse> getAllLists() {
        return listsRepository.findAll().stream()
                .map(list -> getListById(list.getId()))
                .toList();
    }

    @Override
    public CreateListResponse getListById(Long id) {
        Lists list = listsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("List not found with ID: " + id));
        List<ItemsDto> items = itemsRepository.findByListId(id).stream()
                .map(item -> ItemsDto.builder()
                        .id(item.getId())
                        .item(item.getItem())
                        .completed(item.isCompleted())
                        .build())
                .toList();
        return CreateListResponse.builder()
                .id(list.getId())
                .name(list.getName())
                .description(list.getDescription())
                .items(items)
                .build();
    }
    
}
