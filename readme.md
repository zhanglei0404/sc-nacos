## 一、版本依赖
-- -
| spring boot   | spring cloud | spring cloud alibaba | nacos |
|:-------------:|:------------:|:--------------------:|:-----:|
| 2.2.5.RELEASE | Hoxton.SR1   | 2.2.0.RELEASE        | 1.1.4 |
-- -
## 二、nacos 服务器安装
#### Step 1: 下载
* [码云](https://gitee.com/mirrors/Nacos/releases)
* [github发行版](https://github.com/alibaba/nacos/releases)
---
**码云下载代码构建比较快**
```
mvn -Prelease-nacos -DskipTests clean install -U
```
#### Step 2: 启动

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
