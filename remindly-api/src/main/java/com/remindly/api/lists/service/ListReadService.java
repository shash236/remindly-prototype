package com.remindly.api.lists.service;

import java.util.List;

import com.remindly.api.lists.data.CreateListResponse;

public interface ListReadService {
    List<CreateListResponse> getAllLists();
    CreateListResponse getListById(Long id);
}
