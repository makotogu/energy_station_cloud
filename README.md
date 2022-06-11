# energy_station_cloud
分布式能源站后端

每个子文件夹都是一个模块
使用idea导入该文件夹 `energy_station_cloud`

如果识别不出内容 导入pom.xml文件

通过每个模块的 application启动模块
修改`src/main/resources/`中`application.yml`实现配置文件的修改

``` yaml
server:
  # 开放的端口
  port: 8082
#eureka: 不再使用
#  client:
#    service-url:
#      defaultZone: http://127.0.0.1:8081/eureka
spring:
  application:
    # 模块名
    name: user-service
  datasource:
    # 数据库配置
    driver-class-name: com.mysql.cj.jdbc.Driver
    # 数据库路径
    url: jdbc:mysql://localhost:3306/energy_station?serverTimezone=UTC
    # 数据库用户名密码
    username: root
    password: admin
  # nacos配置文件
  cloud:
    nacos:
      discovery:
      # 我的服务器的地址 后续可能不会租了，建议部署新的
        server-addr: 101.35.83.193:8848
mybatis-plus:
  global-config:
    db-config:
      id-type: auto
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```

### nacos 
可以看spring cloud alibaba的网课操作
我这里使用的是docker部署的 比较简单，了解一下docker 然后官网找个镜像就行

## 资料

* 狂神说Java】Docker最新超详细版教程通俗易懂 [看前13p](https://www.bilibili.com/video/BV1og4y1q7M4?share_source=copy_web)
* 微服务的课[前60p](https://www.bilibili.com/video/BV1LQ4y127n4?share_source=copy_web)
* 黑马程序员SpringBoot2全套视频教程，springboot零基础到项目实战（spring boot2完整版）[基础篇全部](https://www.bilibili.com/video/BV15b4y1a7yG?share_source=copy_web)

