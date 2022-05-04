# 自定义RPC

# 项目结构

├── custom-rpc-api          // api
├── custom-rpc-consumer     // 消费者/客户端
├── custom-rpc-provider     // 生产者/服务端

# 改进版需求

1. 客户端集成spring boot

2. 客户端提供Controller层,在浏览器发起用户查询功能

3. 服务端提供2个或2个以上的服务

4. 客户端完成对服务端的负载轮询调用