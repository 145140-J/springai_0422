package com.llsstt.lstai.repository;

import java.util.List;

public interface ChatHistoryRepository {
    // 保存会话记录
    void save(String type, String chaiId);
    List<String> getChatIds(String type);
}
