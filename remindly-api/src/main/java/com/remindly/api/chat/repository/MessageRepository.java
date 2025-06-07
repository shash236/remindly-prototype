package com.remindly.api.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.remindly.api.chat.domain.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
    
}
