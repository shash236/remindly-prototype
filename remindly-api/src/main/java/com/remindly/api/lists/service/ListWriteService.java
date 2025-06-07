package com.remindly.api.lists.service;

import java.util.List;

import com.remindly.api.lists.data.CreateItemResponse;
import com.remindly.api.lists.data.CreateListRequest;
import com.remindly.api.lists.data.CreateListResponse;

public interface ListWriteService {
    // Define methods for writing operations, e.g., create, update, delete lists
    // Example:
    CreateListResponse createList(CreateListRequest request);
    CreateItemResponse addItemsToList(Long listId, final List<String> items);
    void completeItemInList(Long listId, Long itemId);

}
