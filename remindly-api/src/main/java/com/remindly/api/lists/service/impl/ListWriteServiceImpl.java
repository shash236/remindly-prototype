package com.remindly.api.lists.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.remindly.api.lists.data.CreateItemResponse;
import com.remindly.api.lists.data.CreateListRequest;
import com.remindly.api.lists.data.CreateListResponse;
import com.remindly.api.lists.data.ItemsDto;
import com.remindly.api.lists.domain.Items;
import com.remindly.api.lists.domain.Lists;
import com.remindly.api.lists.repository.ItemsRepository;
import com.remindly.api.lists.repository.ListsRepository;
import com.remindly.api.lists.service.ListWriteService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListWriteServiceImpl implements ListWriteService {
    private final ListsRepository listsRepository;
    private final ItemsRepository itemsRepository;
    private static final Long DEFAULT_USER_ID = 1L; // Replace with actual user ID retrieval logic

    @Override
    public CreateListResponse createList(CreateListRequest request) {
        Lists list = Lists.builder()
                .name(request.getName())
                .userId(DEFAULT_USER_ID)
                .description(request.getDescription())
                .build();
        listsRepository.save(list);
        return CreateListResponse.builder()
                .id(list.getId())
                .name(list.getName())
                .description(list.getDescription())
                .build();
    }

    @Override
    public CreateItemResponse addItemsToList(final Long listId, final List<String> items) {
        Lists list = listsRepository.findById(listId)
                .orElseThrow(() -> new IllegalArgumentException("List not found with ID: " + listId));

        List<Items> newItemsList = new ArrayList<>();

        items.forEach(item -> {
            Items newItem = Items.builder()
                    .listId(list.getId())
                    .item(item)
                    .completed(false)
                    .build();
            newItemsList.add(newItem);
        });

        itemsRepository.saveAll(newItemsList);

        List<ItemsDto> itemsDtoList = newItemsList.stream()
                .map(item -> ItemsDto.builder()
                        .id(item.getId())
                        .item(item.getItem())
                        .completed(item.isCompleted())
                        .build())
                .collect(Collectors.toList());

        return CreateItemResponse.builder()
                .listId(listId)
                .items(itemsDtoList)
                .build();
    }

    @Override
    public void completeItemInList(Long listId, Long itemId) {
        Items item = itemsRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found with ID: " + itemId));
        item.setCompleted(true);
        itemsRepository.save(item);
    }

}
