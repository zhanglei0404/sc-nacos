## 零、版本依赖
-- -
| spring boot   | spring cloud | spring cloud alibaba | nacos |
|:-------------:|:------------:|:--------------------:|:-----:|
| 2.2.5.RELEASE | Hoxton.SR1   | 2.2.0.RELEASE        | 1.1.4 |

### 端口说明
| service   | port |
|:-------------:|:-----:|
| gateway | 10000   |
| dashboard | 11000   |
| uaa | 12000   |
| quartz | 13000   |
| nacos | 8848(default)   |
| zipkin | 9411(default)   |
| otherService | 87/97**   |
-- -

更多版本对应关系参考：[版本说明 Wiki](https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E)
## 一、注册与配置中心（nacos）
#### Step 1: 下载
* [码云（速度快）](https://gitee.com/mirrors/Nacos/releases)
* [github发行版](https://github.com/alibaba/nacos/releases)
#### 打包 ```mvn -Prelease-nacos -DskipTests clean install -U```

#### Step 2: 启动 (nacos/bin/)

On **Linux/Unix/Mac** start server with standalone: 
```
sh startup.sh -m standalone
```

On **Windows** start server with standalone:
```
cmd startup.cmd -m standalone
```

#### Step 3: 登录
访问： [http://localhost:8848/nacos](http://localhost:8848/nacos)    

用户名/密码：nacos/nacos

## 二、分布式链路追踪（sleuth+zipkin）
#### Step 1: 安装 Zipkin 服务端
```
curl -sSL https://zipkin.io/quickstart.sh | bash -s
java -jar zipkin.jar
```
访问 [http://localhost:9411/zipkin/](http://localhost:9411/zipkin/) 

#### Step 2: 微服务应用改造 (sc-consumer,sc-consumer)
**pom.xml** 增加以下依赖
```
        <!--分布式链路追踪 spring-cloud-sleuth + zipkin-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-sleuth</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zipkin</artifactId>
        </dependency>
```
**bootstrap.yml** 增加以下配置
```
spring:
  sleuth:
    web:
      client:
        enabled: true
    sampler:
      probability: 1.0 # 将采样比例设置为 1.0，也就是全部都需要。默认是 0.1
    zipkin:
      base-url: http://localhost:9411/ # 指定了 Zipkin 服务器的地址
```
**增加一个测试接口，为了链路明显**
```
SERVICE-CONSUMER-OTHER 转发 SERVICE-CONSUMER 转发 SERVICE-PROVIDER
```

实际测试请求：http://localhost:9766/sleuth/hi?name=zl

## 三、统一认证授权（security+oauth2.0+jwt）
这里遇到了问题，在纠结网关使用zuul还是gateway，最后还是割舍不下gateway：
```
选择springcloud-gateway作为网关：
    优点：响应式的、非阻塞式的 API。开发模式简单（得先熟悉webfulx）（ps：亲儿子）
    缺点，无法兼容security，需要由uaa和每一个resource做权限判定
选择springcloud-zuul作为网关：
    优点，可以由zuul统一过滤访问权限（只限于访问resource的权限，方法权限仍需每个resource单独判定）
    缺点：springcloud-zuul（Zuul1.x）采用阻塞式的 API，不支持长连接（ps，cloud没用Zuul2.x）
```    
#### 参考
* [在单独的UAA上而不是在gateway上进行操作](https://github.com/spring-cloud/spring-cloud-gateway/issues/179#event-1447267153)
* [java进阶教程2天快速入门Spring Security OAuth2.0认证授权](https://www.bilibili.com/video/av73730658)

## 四、 gateway聚合集成swagger2
[访问地址：http://localhost:10000/doc.html](http://localhost:10000/doc.html)
[knife4j官方文档：基于Spring Cloud Gateway](https://doc.xiaominfo.com/guide/ui-front-gateway.html)

## 五、 springboot集成quartz，附带定时任务调度界面
[访问地址：http://localhost:13000/JobManager.html](http://localhost:13000/JobManager.html)