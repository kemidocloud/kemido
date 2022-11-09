# Kemido
Spring Framework based library.

## Sub projects
```txt
kemido
├── kemido-dependencies -- 工程Maven顶级依赖，统一控制版本和依赖
├── kemido-access -- 外部登录接入模块
├    ├── access-core -- 外部登录通用代码组件
├    ├── access-sdk-all -- 外部登录集成组件
├    ├── access-sdk-justauth -- JustAuth登录组件
├    ├── access-sdk-wxapp -- 微信小程序登录组件
├    ├── access-sdk-wxmpp -- 微信公众号登录组件
├    └── access-spring-boot-starter -- 外部登录模块统一 Starter
├── kemido-assistant -- 核心通用代码包
├    ├── assistant-core -- 核心通用代码组件
├    └── assistant-spring-boot-starter -- Assistant 模块统一 Starter
├── kemido-cache -- 缓存模块
├    ├── cache-core -- 缓存通用代码组件
├    ├── cache-sdk-caffeine -- Caffeine 缓存配置相关代码组件模块
├    ├── cache-sdk-jetcache -- JetCache 组件相关代码模块
├    ├── cache-sdk-redis -- Caffeine 缓存配置相关代码组件模块
├    ├── cache-sdk-redisson -- Redisson 组件相关代码模块
├    └── cache-spring-boot-starter -- Cache 模块统一 Starter
├── kemido-captcha -- 验证码模块
├    ├── captcha-core -- 验证码共性通用代码
├    ├── captcha-sdk-behavior -- 行为验证码组件（包括拼图滑块、文字点选）
├    ├── captcha-sdk-graphic -- 传统图形验证码组件（包括算数类型、中文类型、字母类型、GIF类型）
├    ├── captcha-sdk-hutool -- Hutool验证码组件（包括圆圈干扰、扭曲干扰、线段干扰）
├    └── captcha-spring-boot-starter -- Captcha  模块统一 Starter
├── kemido-data -- 数据访问模块
├    ├── data-core -- 数据访问共性通用代码
├    ├── data-sdk-jpa -- JPA 及Hibernate 组件相关代码模块
├    ├── data-sdk-mybatis-plus -- MybatisPlus 组件相关代码模块
├    ├── data-sdk-p6spy -- P6spy 组件相关代码模块
├    └── data-spring-boot-starter -- Data 模块统一 Starter
├── kemido-event -- Spring 事件模块
├    ├── event-core -- 事件组件共性代码模块
├    ├── event-pay-spring-boot-starter -- 支付事件统一 Starter
├    └── event-security-spring-boot-starter --安全事件统一 Starter
├── kemido-facility -- 微服务基础设施模块
├    ├── facility-core -- 基础设施共性通用代码
├    ├── facility-sdk-log -- 微服务日志中心组件模块
├    ├── facility-sdk-sentinel -- Sentinel 组件模块
├    └── facility-spring-boot-starter -- Facility 模块统一 Starter
├── kemido-message -- 消息模块
├    ├── message-core -- 消息共性通用代码
├    └── message-spring-boot-starter -- Message  模块统一 Starter
├── kemido-nosql -- Nosql 数据库接入管理模块
├    ├── nosql-core -- nosql基础共性通用代码
├    ├── nosql-sdk-couchdb -- Couchdb Nosql 数据库接入管理组件模块
├    └── nosql-sdk-influxdb -- Influxdb 时序数据库接入管理组件模块
├── kemido-oauth2 -- OAuth2 认证模块
├    ├── oauth2-core -- OAuth2 共性通用代码
├    ├── oauth2-sdk-authorization -- Spring Authorization Server Granter 扩展组件模块
├    ├── oauth2-sdk-authorization-server -- Spring Authorization Server 管理代码模块
├    ├── oauth2-sdk-compliance -- Spring Authorization Server 应用安全合规支撑组件模块
├    ├── oauth2-sdk-data-jpa -- 基于 Spring Data JPA 封装的 Spring Authorization Server 数据访问代码模块
├    ├── oauth2-sdk-metadata -- 鉴权元数据处理代码模块
├    └── oauth2-sdk-resource-server -- OAuth2 资源服务器通用代码模块
├── kemido-oss -- 对象存储模块
├    ├── oss-core -- 对象存储共性通用代码
├    ├── oss-sdk-minio -- Minio 组件模块
├    └── oss-spring-boot-starter -- Oss 模块统一 Starter
├── kemido-pay -- 支付模块
├    ├── pay-core -- 支付共性通用代码
├    ├── pay-sdk-alipay -- 支付宝支付组件模块
├    ├── pay-sdk-all -- 支付方式整合组件模块
├    ├── pay-sdk-wxpay -- 微信支付组件模块
├    └── pay-spring-boot-starter -- Pay 模块统一 Starter
├── kemido-protect -- Rest API 防护组件
├    ├── protect-core -- Rest API 防护共性代码模块组件
├    ├── protect-sdk-web -- 前后端数据加密、接口幂等、防刷、Xss和SQL注入防护组件模块
├    └── protect-sdk-spring-boot-starter -- Protect 模块统一 Starter
├── kemido-rest -- 服务Rest接口模块
├    ├── rest-core -- 服务Rest接口共性通用代码
├    └── rest-spring-boot-starter -- Rest 模块统一 Starter(包括通用CRUD代码)
├── kemido-sms -- 短信接入模块
├    ├── sms-core -- 短信共性通用代码模块
├    ├── sms-sdk-aliyun -- 阿里云短信发送组件模块
├    ├── sms-sdk-all -- 短信整合组件模块
├    ├── sms-sdk-chinamobile -- 移动短信发送组件模块
├    ├── sms-sdk-huawei -- 华为短信发送组件模块
├    ├── sms-sdk-jd -- 京东短信发送组件模块
├    ├── sms-sdk-netease -- 网易短信发送组件模块
├    ├── sms-sdk-qiniu -- 七牛短信发送组件模块
├    ├── sms-sdk-tencent -- 腾讯短信发送组件模块
├    ├── sms-sdk-upyun -- 又拍短信发送组件模块
├    ├── sms-sdk-yunpian -- 云片短信发送组件模块
├    └── sms-spring-boot-starter -- SMS 模块统一 Starter(包括通用CRUD代码)
├── kemido-web -- Web处理模块
├    ├── web-core -- Web 应用共性通用代码模块组件
├    ├── web-sdk-rest -- Web 应用基础支撑模块组件
├    ├── web-sdk-scan -- 接口权限扫描组件模块
├    └── web-spring-boot-starter -- Web 模块统一 Starter
├── kemido-websocket -- Websocket模块
├    ├── websocket-core -- Websocket模块共性通用代码
├    ├── websocket-sdk-accelerator -- Websocket基础逻辑组件模块
└──  └── websocket-spring-boot-starter -- Websocket 模块统一 Starter
```
