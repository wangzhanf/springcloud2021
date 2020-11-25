# Spring Cloud授课笔记

# 学习Spring Cloud的前置条件

JavaSE

数据库知识

前端知识

Servlet/JSP

Http

MyBatis

Spring/SpringMVC

Spring Boot

Dubbo,Zookeeper

分布式概念

Maven

Git

Ajax

Json



### 





# 微服务架构概述

## 微服务产生的背景

1	企业对外部用户提供业务,要求快速发布和迭代

2	单体应用造成缺陷

3	软件架构解耦的需求

## 微服务定义

微服务架构是一种架构模式，它提倡将单一应用程序划分成一组小的服务，服务之间互相协调、互相配合，为用户提供最终价值。每个服务运行在其独立的进程中，服务与服务间采用轻量级的通信机制互相沟通（通常是基于HTTP协议的RESTful API）。每个服务都围绕着具体业务进行构建，并且能够被独立的部署到生产环境、类生产环境等。另外，应当尽量避免统一的、集中式的服务管理机制，对具体的一个服务而言，应根据业务上下文，选择合适的语言、工具对其进行构建。

## 软件架构演进

### 单体架构

所有功能整体打包,应用和数据库分离,通过集群的方式提高系统性能

### 垂直架构

按照业务模块将一个大型项目分解为多个互为关联的子项目,项目间数据冗余高,耦合度高,各子系统间数据需要同步消耗资源多

### SOA架构

将公用功能抽取为组件,以服务方式给各系统提供服务,各子项目与服务通过webservice,rpc等方式通信[ESB企业服务总线作为通信的桥梁]

### 微服务

系统服务层完全独立,抽取成独立的微服务[模块化开发],采用RESTful等轻量协议传输

## 微服务架构需要解决的核心问题

1	服务有很多,客户如何访问

2	服务之间如何通信

3	服务如何治理

4	服务宕机怎么处理

## 微服务的设计原则

### 1.单一职责原则

指一个单元只应关注整个系统功能中单独、有界限的一部分。
单一职责原则可以帮助我们更优雅地开发、更敏捷地交付。

### 2.服务自治原则

指每个微服务应具备独立的业务能力、依赖与运行环境。
在微服务架构中，服务是独立的业务单元，应该与其他服务高度解耦。
每个微服务从开发、测试、构建、部署，都应可以独立运行，而不依赖其他的服务。

### 3.轻量级通信机制

微服务之间应该通过轻量级通信机制进行交互。
轻量级通信机制应具备两点：一是体量较轻，二是跨语言、跨平台。

### 4.微服务粒度

应使用合理的粒度划分微服务，而不是一味的把服务做小。
每一个架构师心中的粒度标准是不一样的，所以这块争论最多。

## 常见微服务解决方案

1	Spring Cloud NetFlix	一站式解决方案[开箱即用]

2	Dubbo Zookeeper	半自动化方案[自己组装,自由组合]

3	Spring Cloud Alibaba	一站式解决方案[观望中.....]

# Spring Cloud入门

## Spring Cloud简介

Spring Cloud提供了一系列工具, 协调分布式环境中各个系统，为各类服务提供模板性配置。 

Spring Cloud并不是一个项目，而是一组项目的集合。

Spring Cloud是将目前各家公司开发的比较成熟、经得起实际考验的服务框架组合起来，通过Spring Boot风格进行再封装屏蔽掉了复杂的配置和实现原理，提供了一套开发工具包。

## Spring Cloud技术栈

具体查看课件

或者查看官网    https://spring.io/projects/spring-cloud

## Spring Cloud的版本和Spring Boot的版本关系

Spring Boot版本使用数字命名 2.3.5.RELEASE

Spring Cloud版本使用英文首字母命名[伦敦地铁站的名称] 

SR的意义    Server  Release

 Spring Boot与Spring Cloud需要版本对应 

通过spring官网的推荐技术选型选择对应的版本

```http
https://start.spring.io/actuator/info
```



## 版本的选择[依据于spring官网推荐]

cloud	Hoxton.SR1

boot	2.2.2.RELEASE

cloud alibaba	2.1.0.RELEASE

Java	Java8

Maven	3.5以上

MySQL	5.7以上



## cloud组件

![image-20201109215559639](SpringCloud%E6%8E%88%E8%AF%BE%E7%AC%94%E8%AE%B0.assets/image-20201109215559639.png)

## 学习资料

spring官网

springcloud中文文档

```http
https://www.bookstack.cn/read/spring-cloud-docs/docs-index.md
```

​	https://www.springcloud.cc/spring-cloud-netflix.html

​	https://www.springcloud.cc/spring-cloud-dalston.html

​	http://springcloud.cn/

# 微服务架构构建

## 父工程创建[springcloud2021]

1 创建一个maven项目 ,可以选择适用 maven-archetype-site骨架,也可以不选择

2 修改file encoding为UTF-8 

3 Enable annotation processiong   支持注解

4 修改java版本,删除项目下的src等微调操作

5 修改pom文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>vip.epss</groupId>
    <artifactId>springcloud2021</artifactId>
    <version>1.0-SNAPSHOT</version>

    <!--    打包方式-->
    <packaging>pom</packaging>

    <!--    属性定义-->
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <junit.version>4.12</junit.version>
        <log4j.version>1.2.17</log4j.version>
        <lombok.version>1.16.18</lombok.version>
        <mysql.version>8.0.16</mysql.version>
        <druid.version>1.1.16</druid.version>
        <mybatis.spring.boot.version>1.3.0</mybatis.spring.boot.version>
    </properties>

    <!-- 
        子模块继承之后，提供作用：锁定版本+子modlue不用写groupId和version
        这里是不会真实导入依赖包的,在子模块引用时才会真实的导入
      -->
    <dependencyManagement>
        <dependencies>
            <!--spring boot 2.2.2-->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>2.2.2.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <!--spring cloud Hoxton.SR1-->
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>Hoxton.SR1</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <!--spring cloud alibaba 2.1.0.RELEASE-->
            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                <version>2.1.0.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <!--mysql-->

            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>${mysql.version}</version>
            </dependency>

            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid</artifactId>
                <version>${druid.version}</version>
            </dependency>

            <dependency>
                <groupId>org.mybatis.spring.boot</groupId>
                <artifactId>mybatis-spring-boot-starter</artifactId>
                <version>${mybatis.spring.boot.version}</version>
            </dependency>

            <dependency>
                <groupId>junit</groupId>
                <artifactId>junit</artifactId>
                <version>${junit.version}</version>
            </dependency>

            <dependency>
                <groupId>log4j</groupId>
                <artifactId>log4j</artifactId>
                <version>${log4j.version}</version>
            </dependency>

            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>${lombok.version}</version>
                <optional>true</optional>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <fork>true</fork>
                    <addResources>true</addResources>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```



## 通用的api 子模块创建[cloud-api-commons]

1 创建module

2 修改pom

3 编写配置文件

4 主启动

5 业务类



### 具体实现

具体实现根据需求改变

创建数据库以及数据表

```mysql
drop database if exists spring_cloud_test;
create database if not exists spring_cloud_test;

use spring_cloud_test;

# 创建数据表
create table department
(
    did       int auto_increment comment '部门编号'
        primary key,
    dname     varchar(50) null comment '部门名称',
    db_source varchar(50) null comment '数据源'
)
    comment '部门表';

# 插入测试数据
insert into department (dname, db_source) VALUES ('教学部',DATABASE()),
                                                 ('学生部',DATABASE()),
                                                 ('后勤部',DATABASE()),
                                                 ('财务部',DATABASE()),
                                                 ('运输部',DATABASE()),
                                                 ('采购部',DATABASE())
                                                 ;
```



## 服务提供者 子模块创建[cloud-provider-department-8001]

1 创建module

2 修改pom

3 编写配置文件

4 主启动

5 业务类



### 具体实现



使用postman进行测试



## 服务消费者 子模块创建[cloud-consumer-department-80]

1 创建module

2 修改pom

3 编写配置文件

4 主启动

5 业务类



### 具体实现

使用RestTemplate方式取代http.client

创建一个配置类[ConfigBean],注册RestTemplate,  用以使用RestTemplate

```java
package vip.epss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @创建人 epss[wangzhanf]
 * @创建时间 2020/11/24 0024
 * @描述
 */
@Configuration
public class ConfigBean {
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}

```

### 重要的事情说3遍

通过消费者调用服务提供者提供的服务时,消费者传递的数据在服务提供者获取过程中特别要注意的问题是封装为对象的实体类一定别忘记**使用  @RequestBody   注解**,否则获得的数据就是null

```java
public Integer add(@RequestBody Department department)
public Integer add(@RequestBody Department department)
public Integer add(@RequestBody Department department)
```



## 重构项目[cloud-api-commons]

将多个模块中相同的内容抽取出来

1 	创建一个公用的模块  cloud-api-commons

2	导入相关依赖包

3	将公用的内容抽取放在该模块中[主要是实体类和封装结果的类]

4	将公用的模块clean并且install到本地仓库

5	删除原有模块中重复的代码

6	在各个模块中引入我们自己的公共模块坐标

```xml
<!--        引入自己定义的通用包-->
<dependency>
    <groupId>vip.epss</groupId>
    <artifactId>cloud-api-commons</artifactId>
    <version>${project.version}</version>
</dependency>
```

7	测试

# Eureka服务注册与发现

## Eureka的两个组件: Eureka采用C/S架构模式

Eureka Server:	提供服务注册,存储可用服务节点
Eureka Client:	客户端启动后向Eureka Server发送心跳(30s),如果90s没有发送心跳则Eureka Server将会移除该客户端,同时客户端也具备一个轮询的负载均衡器.

## Eureka的三大角色

Eureka Server:提供服务的注册和发现

service Provider:服务方将自身服务注册到Eureka中,从而使消费方能够找到

Service Consumer:消费方从Eureka中获取注册服务的列表,从而找到服务并消费

## CAP理论

### RDBMS的ACID原则

Atomicity		  原子性

Consistency	  一致性

Isolation			隔离性

Durability		  持久性

### NoSQL的CAP原则[只能同时满足其中两项]

Consistency					一致性

Availability						可用性

Partition tolerance		分区容错性

### Eureka和zooKeeper

zooKeeper确保CP, 主节点挂掉之后需要重新选举

Eureka保证的是AP,所有节点平等

# Eureka单机配置

## 创建Eureka服务器[cloud-eureka-7001]

1 创建module

2 修改pom

3 编写配置文件	

4 主启动:添加    **@EnableEurekaServer**   注解主启动类

通过浏览器访问控制中心 http://localhost:7001/  测试是否成功



## 创建Eureka客户端[cloud-provider-department-8005]

1 创建module[也可以复制module后重命名]

2 修改pom  导入依赖

3 编写配置文件	

4 主启动:  添加    **@EnableEurekaClient**   注解主启动类     ,添加 **@EnableDiscoveryClient**  服务发现

5 测试    先启动Eureka Server 然后再启动 Eureka Client

6 通过浏览器访问控制中心 http://localhost:7001/  测试是否成功



### 自我保护机制

关闭8005服务,刷新 http://localhost:7001/  看到保护机制已被启用

自我保护: 当Eureka Server 在短时间内丢失过多客户端时(网络阻塞等) , 此时进入保护模式,  不再删除服务注册表中的数据,当他收到的心跳数重新恢复到阈值以上时,会自动退出保护模式

可以通过  eureka.server.enable-self-preservation=false   禁用自动保护



# Eureka集群配置

修改本机hosts文件进行本地域名映射或者真实DNS解析

```pro
127.0.0.1	eureka7004.com
127.0.0.1	eureka7005.com
127.0.0.1	eureka7006.com
```



## 创建Eureka服务器[cloud-eureka-7004]



## 创建Eureka服务器[cloud-eureka-7005]



## 创建Eureka服务器[cloud-eureka-7006]



## 创建服务提供者[cloud-provider-department-8008]

启动集群 7004 7005 7006  和服务提供者 8008 测试

访问  http://eureka7004.com:7004/      http://eureka7005.com:7005/    http://eureka7006.com:7006/ 











学习资料下载

## git@github.com:wangzhanf/springcloud2021.git

## 使用命令

## git  clone git@github.com:wangzhanf/springcloud2021.git

## 克隆

## wangzhanf     我的github账号     王占峰   

## 1	注册github账号

## 2	配置git

### git config --global user.name "wangzhanf"

### $ git config --global user.email wangzhanf@126.com

### 3	产生秘钥

###  ssh-keygen -t rsa -C "wangzhanf@126.com"

### 4	进入  用户宿主目录    找到  .ssh  下的id_rsa.pub   拷贝其中的内容  粘贴到   github上      

### 5	通过命令行方式访问远程仓库



# 开发平台的基本构建

IDEA    下载      https://www.jetbrains.com/idea/

## 基本的配置

### 配置maven

## STS的workspace相当于idea中的project

## STS的project 相当于 idea中的  module





