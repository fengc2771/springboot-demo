# springboot-demo

一个基于 Spring Boot 3.2.5 + Java 17 的示例项目，提供 **3 个 REST 接口**，用于演示 MVC 分层、参数校验与统一异常处理。

## 技术栈

| 组件 | 版本 |
| --- | --- |
| Java | 17 |
| Spring Boot | 3.2.5 |
| 构建工具 | Maven 3.8+ |
| 校验 | spring-boot-starter-validation |

## 接口列表

| 序号 | 方法 | 路径 | 说明 |
| --- | --- | --- | --- |
| 1 | POST | `/api/tasks` | 创建任务，请求体校验 `title` 必填 |
| 2 | GET | `/api/tasks` | 查询任务列表（按 id 升序） |
| 3 | GET | `/api/tasks/{id}` | 按 id 查询任务详情，不存在返回 404 |

数据存储在内存 `ConcurrentHashMap` 中，应用重启后清空，无需任何外部依赖。

## 快速开始

```bash
# 构建（含单元测试）
mvn clean package

# 运行
mvn spring-boot:run
# 或
java -jar target/springboot-demo-0.0.1-SNAPSHOT.jar
```

服务默认监听 `http://localhost:8080`。

## 调用示例

```bash
# 1. 创建任务
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{"title":"Learn Spring Boot","description":"verify 3 endpoints"}'
# => 201 {"id":1,"title":"Learn Spring Boot","description":"verify 3 endpoints","completed":false,"createdAt":"..."}

# 2. 查询列表
curl http://localhost:8080/api/tasks
# => 200 [{"id":1,...}]

# 3. 查询详情
curl http://localhost:8080/api/tasks/1
# => 200 {"id":1,...}

# 不存在的 id
curl http://localhost:8080/api/tasks/999
# => 404 {"status":404,"error":"Not Found","message":"任务不存在: id=999"}
```

## 项目结构

```
src/main/java/com/example/springbootdemo/
├── SpringbootDemoApplication.java     # 启动类
├── controller/TaskController.java     # 3 个接口
├── service/TaskService.java           # 内存存储的业务逻辑
├── model/Task.java                    # 实体
├── dto/CreateTaskRequest.java         # 请求体 + 参数校验
└── exception/                         # 404 与参数校验的全局异常处理
```

## 测试

`TaskControllerTest` 覆盖创建成功、列表查询、404 与参数校验四条用例：

```bash
mvn test
```

## License

MIT
