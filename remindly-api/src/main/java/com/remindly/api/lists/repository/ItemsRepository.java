package com.remindly.api.lists.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.remindly.api.lists.domain.Items;

public interface ItemsRepository extends JpaRepository<Items, Long> {
    // Define custom query methods if needed
    // For example, find items by list ID, etc.
    List<Items> findByListId(Long listId);
}
