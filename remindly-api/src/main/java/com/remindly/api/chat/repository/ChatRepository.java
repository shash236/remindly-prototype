package com.remindly.api.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.remindly.api.chat.domain.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    
}
