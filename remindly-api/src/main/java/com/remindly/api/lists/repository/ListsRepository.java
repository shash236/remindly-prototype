package com.remindly.api.lists.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.remindly.api.lists.domain.Lists;

public interface ListsRepository extends JpaRepository<Lists, Long> {
    
}
