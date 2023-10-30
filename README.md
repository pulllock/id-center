# id-center

ID生成中心

# 项目基础

依赖项目：

- parent-pom: https://github.com/pulllock/parent-pom
- general-starter: https://github.com/pulllock/general-starter

# 支持生成ID方式

- zookeeper-custom：使用zookeeper的持久化节点存储自定义的id，自定义id从1开始自增，生成ID需要使用分布式锁
- zookeeper-persistent：使用zookeeper的持久化顺序节点方式生成id
- zookeeper-ephemeral：使用zookeeper的临时顺序节点方式生成id
- redis：使用redis生成ID
- uuid：使用UUID
- mysql：使用mysql生成ID

# 切换生成ID的方式

在应用配置文件中修改`id-generator.type`属性，并根据选用具体类型来配置相应以来的中间件的配置。

## 使用zookeeper-custom的方式的配置

```yaml
# 生成ID的方式：zookeeper-custom、zookeeper-persistent、zookeeper-ephemeral、redis、uuid、mysql
id-generator:
  type: zookeeper-custom

# curator配置
curator:
  # zookeeper地址
  url: 127.0.0.1:2181
  # 命名空间
  namespace: ${app.name}
  # 重试次数
  retry-times: 1
  # 重试间隔时间，毫秒
  elapsed-time: 2000
  # session超时时间，毫秒
  session-timeout: 60000
  # 连接超时时间，毫秒
  connection-timeout: 10000
```

## 使用zookeeper-persistent的方式的配置

```yaml
# 生成ID的方式：zookeeper-custom、zookeeper-persistent、zookeeper-ephemeral、redis、uuid、mysql
id-generator:
  type: zookeeper-persistent

# curator配置
curator:
  # zookeeper地址
  url: 127.0.0.1:2181
  # 命名空间
  namespace: ${app.name}
  # 重试次数
  retry-times: 1
  # 重试间隔时间，毫秒
  elapsed-time: 2000
  # session超时时间，毫秒
  session-timeout: 60000
  # 连接超时时间，毫秒
  connection-timeout: 10000
```

## 使用zookeeper-ephemeral的方式的配置

```yaml
# 生成ID的方式：zookeeper-custom、zookeeper-persistent、zookeeper-ephemeral、redis、uuid、mysql
id-generator:
  type: zookeeper-ephemeral

# curator配置
curator:
  # zookeeper地址
  url: 127.0.0.1:2181
  # 命名空间
  namespace: ${app.name}
  # 重试次数
  retry-times: 1
  # 重试间隔时间，毫秒
  elapsed-time: 2000
  # session超时时间，毫秒
  session-timeout: 60000
  # 连接超时时间，毫秒
  connection-timeout: 10000
```