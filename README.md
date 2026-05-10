# Spring AI 实战项目合集
基于 Spring AI 实现的 4 个企业级 AI 应用案例，用于展示 Java 后端 + AI 大模型工程化落地能力。

涵盖：本地模型部署与会话记忆、Prompt 工程、Function Calling 工具调用、RAG 知识库问答。

---

# 项目信息
- 开发语言：Java 26
- 构建工具：Maven 4.0.0+
- 框架：Spring Boot 3.x + Spring AI
- 模型平台：Ollama (deepseek-r1:7b)、阿里云百炼 (qwen-max-latest)
- 核心能力：多轮会话记忆、Prompt 角色控制、函数调用、RAG 嵌入检索、流式对话

---

# 功能模块介绍

## 1. 本地部署 DeepSeek-R1:7b + 会话记忆模块
- 基于 Ollama 本地运行 deepseek-r1:7b
- 实现会话历史、会话记忆、会话记录管理
- 支持多轮对话上下文保持
- 技术：ChatModel、ChatMemory、MessageChatMemoryAdvisor

## 2. 纯 Prompt 实现：生气女友对话小游戏
- 完全依靠 System Prompt 完成角色设定与情绪控制
- 用户需要用语言哄“生气女友”开心
- 展示 Prompt 工程、情绪交互、状态引导能力
- 技术：Prompt 设计、多轮对话、角色模拟

## 3. 基于 Function Calling 的智能客服（教育课程场景）
- 使用阿里云百炼 qwen-max-latest
- 根据用户知识水平智能推荐课程
- 支持查询课程、校区、上课时间
- 支持课程预约（工具调用真实业务）
- 技术：Function Calling、Tool 注册、意图识别、参数解析

## 4. 基于 RAG Embedding 的私有知识库问答
- 上传知识库 → 分块 → Embedding 向量化 → 向量存储
- 基于文档内容精准回答，不胡编
- 适合企业内部文档问答、私有知识问答
- 技术：RAG、EmbeddingModel、VectorStore、QuestionAnswerAdvisor

---

# 环境要求
- Java 26
- Maven 4.0.0+
- Ollama（用于本地模型）
- 阿里云百炼 API-KEY（用于智能客服）

---

# 快速启动
1. 配置 application.yml
2. 填入 Ollama 地址、百炼 AK/SK
3. 运行：根目录执行：`mvn spring-boot:run`

