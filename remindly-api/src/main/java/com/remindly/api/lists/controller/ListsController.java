package com.remindly.api.lists.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.remindly.api.lists.data.CreateItemRequest;
import com.remindly.api.lists.data.CreateItemResponse;
import com.remindly.api.lists.data.CreateListRequest;
import com.remindly.api.lists.data.CreateListResponse;
import com.remindly.api.lists.service.ListReadService;
import com.remindly.api.lists.service.ListWriteService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/v1/lists")
@RequiredArgsConstructor
public class ListsController {

    private final ListWriteService listWriteService;
    private final ListReadService listReadService;

    @PostMapping
    public CreateListResponse createList(@RequestBody CreateListRequest request) {
        return listWriteService.createList(request);
    }

    @GetMapping
    public List<CreateListResponse> getAllLists() {
        return listReadService.getAllLists();
    }

    @GetMapping("/{id}")
    public CreateListResponse getList(@PathVariable Long id) {
        return listReadService.getListById(id);
    }

    @PostMapping("/{id}/items")
    public CreateItemResponse addItemToList(@PathVariable Long id, @RequestBody CreateItemRequest request) {
        return listWriteService.addItemsToList(id, request.getItems());
    }

    @PostMapping("/{id}/items/{itemId}/complete")
    public Long completeItemInList(@PathVariable Long id, @PathVariable Long itemId) {
        listWriteService.completeItemInList(id, itemId);
        return itemId;
    }

}
