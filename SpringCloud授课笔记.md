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

...



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

## 本例涉及模块

cloud-eureka-7001

cloud-provider-department-8005

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

## 本例涉及模块

cloud-eureka-7004

cloud-eureka-7005

cloud-eureka-7006

cloud-provider-department-8008

## 修改本机hosts文件进行本地域名映射或者真实DNS解析

```pro
127.0.0.1	eureka7004.com
127.0.0.1	eureka7005.com
127.0.0.1	eureka7006.com
```



## 创建Eureka服务器[cloud-eureka-7004]



## 创建Eureka服务器[cloud-eureka-7005]



## 创建Eureka服务器[cloud-eureka-7006]



## 创建服务提供者[cloud-provider-department-8008]

## 测试

启动集群 7004 7005 7006  和服务提供者 8008 测试

访问  http://eureka7004.com:7004/      http://eureka7005.com:7005/    http://eureka7006.com:7006/ 



# Ribbon负载均衡

## 本例涉及模块

cloud-eureka-7004

cloud-eureka-7005

cloud-eureka-7006

cloud-consumer-department-ribbon-80

cloud-provider-department-8008

cloud-provider-department-8009

cloud-provider-department-8010

## 负载均衡基础

负载均衡概念

Loader Balance

### 负载均衡的分类

集中制:例如Nginx的反向代理

进程内LB:将LB逻辑集成到消费方,消费方从服务注册中心获知有哪些服务地址是可用的,然后选择一个合适的服务器, 例如Ribbon

### 负载均衡的方式

轮询

随机

### Ribbon

Spring Cloud Ribbon是基于Netflix Ribbon实现的**客户端负载均衡工具**

目的是提供系统的高可用性(HA)

我们也可以**自定义负载均衡的算法**

## 如何使用Ribbon[cloud-consumer-department-ribbon-80]

1	创建对应module

2	导入对应的坐标

```xml
<!--        Ribbon依赖,需要导入ribbon和Eureka Client依赖[因为Ribbon是消费端的负载均衡哦]-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-ribbon</artifactId>
    <version>1.4.6.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

3	修改配置文件

```yaml
server:
  port: 80
#Eureka配置
eureka:
  client:
    register-with-eureka: false     #false表示不向注册中心注册自己。
    service-url:
      #可以从以下服务中心列表中获取对应的服务
      defaultZone: http://eureka7004.com:7004/eureka/,http://eureka7005.com:7005/eureka/,http://eureka7006.com:7006/eureka/

```

4	修改主启动类,添加注解

```java
//Eureka和Ribbon整合后,消费端通过服务名调用具体的服务,而不需要指定具体哪个服务器和哪个端口提供具体服务
//具体参考DepartmentConsumerController的配置
@SpringBootApplication
@EnableEurekaClient //
```

5	修改配置类,让获得RestTemplate对象通过Ribbon实现

```java
@Configuration
public class ConfigBean {
    @Bean
    @LoadBalanced   //配置负载均衡实现RestTemplate
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
```

6	修改Controller,使得获得服务提供者使用服务名获得而不是固定地址获得

```java
//服务提供者的请求地址前缀,这是没有负载均衡时通过指定地址获得
//    private static final String REST_URL_PREFIX = "http://localhost:8001";
//这是通过Ribbon实现负载均衡时,通过服务名来访问实现负载均衡
private static final String REST_URL_PREFIX = "http://CLOUD-PROVIDER-DEPARTMENT";
```

7	创建一个新的数据库以及数据表

```mysql
drop database if exists spring_cloud_test_2;
create database if not exists spring_cloud_test_2;

use spring_cloud_test_2;

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

8	创建多个服务提供者,本例中添加2个[cloud-provider-department-8009,cloud-provider-department-8010]

​	记得要修改相应配置文件哦

9	开启测试

7004	7005	7006	8008	8009 	8010	ribbon-80

http://localhost/consumer/department/list



## Ribbon的算法

### 使用其他负载均衡算法,本例使用随机算法[cloud-consumer-department-ribbon-random-80]

1	将随机的算法注册进入IOC容器,具体内容参考对应模块的完整代码

```java
    //使用Ribbon提供的随机负载算法
    @Bean
    public IRule getRandomRule(){
        return new RandomRule();
    }
```

2	启动测试

7004	7005	7006	8008	8009 	8010	ribbon-random-80

验证结果,访问的服务提供者是**随机**出现的,而不是默认的轮询方式

### 自定义Ribbon负载算法[cloud-consumer-department-ribbon-myrule-80]

Ribbon的核心是通过  IRule  接口的具体实现类实现负载均衡的不同算法,自定义的组件会覆盖默认的算法规则,自定义的算法规则**不应该**放在Spring扫描的范围内.

1	创建一个自定义的负载算法类

```java
//自己定义的算法规则,简单的分配原则,每个服务访问3次后换下一个服务器
if(total<2){
    server = upList.get(current);
    total++;
}else{
    total = 0;
    current++;
    if(current>=upList.size()){
        current = 0;
    }
    server = upList.get(current);
}
```

2	给主启动类添加注解,使自定义的算法规则能够生效,或者使用配置类注入该Bean

```java
@RibbonClient(name = "CLOUD-PROVIDER-DEPARTMENT",configuration = MyRule.class)
```

3	启动所有相关服务测试

7004	7005	7006	8008	8009 	8010	ribbon-myrule-80

# Feign负载均衡

Feign是声明式的web service客户端[**面向接口编程**],这样可以弃用原来使用ribbon时通过微服务的名称访问服务这种硬编码方式.我们可以使用Feign提供负载均衡的HTTP客户端,Feign默认调用Ribbon做负载均衡

使用方式: 创建一个接口,添加注解

## Feign的配置

### 涉及模块

 cloud-consumer-department-feign-80



### 配置步骤

1	创建 cloud-consumer-department-feign-80,根据cloud-consumer-department-ribbon-80改造即可 

2	导入feign的依赖

3	给自定义的接口添加注解  @FeignClient

4	编写对应的接口方法

5	改造controller

6	给主启动类添加注解@EnableFeignClients    和   @ComponentScan[本例中无需添加,因为接口在主启动类能够扫描到的位置]



7	测试

7004	7005	7006	8008	8009 	8010	 cloud-consumer-department-feign-80

# 服务降级和熔断

## 分布式系统面临的问题

分布式系统环境下，服务间类似依赖非常常见，一个业务调用通常依赖多个基础服务,例如A需要调用B。某些服务B不可用时，调用该服务的A请求线程被阻塞，当有大批量A请求调用该服务时，最终可能导致整个A服务资源耗尽，无法继续对外提供服务。并且这种不可用可能**沿请求调用链向上传递**，这种现象被称为**雪崩效应**。[踩踏现象]

 ![img](SpringCloud%E6%8E%88%E8%AF%BE%E7%AC%94%E8%AE%B0.assets/887e7862-578a-3616-a15c-1ef1cb62f3c4.png) 

## 雪崩效应常见场景

硬件故障：如服务器宕机，机房断电，光纤被挖断等。
流量激增：如异常流量，重试加大流量等。
缓存穿透：一般发生在应用重启，所有缓存失效时，以及短时间内大量缓存失效时。大量的缓存不命中，使请求直击后端服务，造成服务提供者超负荷运行，引起服务不可用。
程序BUG：如程序逻辑导致内存泄漏，JVM长时间FullGC等。
同步等待：服务间采用同步调用模式，同步等待造成的资源耗尽。

## 应对的策略

针对造成雪崩效应的不同场景，可以使用不同的应对策略，没有一种通用所有场景的策略，参考如下：

硬件故障：多机房容灾、异地多活等。
流量激增：服务自动扩容、流量控制（限流、关闭重试）等。
缓存穿透：缓存预加载、缓存异步加载等。
程序BUG：修改程序bug、及时释放资源等。
同步等待：资源隔离、MQ解耦、不可用服务调用快速失败等。资源隔离通常指不同服务调用采用不同的线程池；不可用服务调用快速失败一般通过熔断器模式结合超时机制实现。
综上所述，如果一个应用不能对来自依赖的故障进行隔离，那该应用本身就处在被拖垮的风险中。 因此，为了构建稳定、可靠的分布式系统，我们的服务应当具有自我保护能力，当依赖服务不可用时，当前服务启动自我保护功能，从而避免发生雪崩效应。

## Hystrix是什么

Hystrix解决同步等待的雪崩问题,保证不会因为一个依赖出现问题而导致整体服务失败,避免级联故障,提高分布式系统的弹性.

## Hystrix配置服务熔断

通过采用备选方法提供业务流程,防止出现拥塞等待,服务熔断是在服务端做的工作

1	测试

没有熔断配置前先访问看看会出现何种问题[例如传入了不存在的did]

2	根据cloud-provider-department-8008 改造一个配置Hystrix的服务提供者cloud-provider-department-hystrix-8014

3	修改pom文件导入依赖

```xml
<!--        Hystrix的依赖-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-hystrix</artifactId>
    <version>1.4.6.RELEASE</version>
</dependency>
```

4	修改配置文件

5	修改Controller添加备用的处理方法

6	给主启动类添加  @EnableCircuitBreaker    断路器的注解

7	访问测试

cloud-provider-department-hystrix-8014

7004	7005	7006	cloud-consumer-department-feign-80

## Hystrix配置服务降级

当资源不足时,让客户不去访问某些服务,服务降级是在客户端做的工作

1	根据cloud-consumer-department-feign-80改造一个配置Hystrix的客户端消费者 cloud-consumer-department-feign-hystrix-80

2	客户端创建类实现降级的处理,该类实现FallbackFactory接口,并实现相应的备选方法

3	给客户端的service添加注解,使用创建的备选类

```java
@FeignClient(value = "CLOUD-PROVIDER-DEPARTMENT",fallbackFactory = DepartmentClientServiceFallbackFactory.class)
```

4	修改主配置文件启用服务降级

```yaml
#启用服务降级
feign:
  hystrix:
    enabled: true
```

5	访问测试

cloud-provider-department-hystrix-8014

7004	7005	7006	cloud-consumer-department-feign-hystrix-80

6	此时关闭cloud-provider-department-hystrix-8014 验证

# 配套学习资料下载

git@github.com:wangzhanf/springcloud2021.git

使用命令

git  clone git@github.com:wangzhanf/springcloud2021.git

克隆

wangzhanf     我的github账号    

# Git的基本使用

1	注册github账号

2	配置git

git config --global user.name "wangzhanf"

$ git config --global user.email wangzhanf@126.com

3	产生秘钥

ssh-keygen -t rsa -C "wangzhanf@126.com"

4	进入  用户宿主目录    找到  .ssh  下的id_rsa.pub   拷贝其中的内容  粘贴到   github上      

5	通过命令行方式访问远程仓库



# 开发平台的基本构建

IDEA    下载      https://www.jetbrains.com/idea/

基本的配置

配置maven

STS的workspace相当于idea中的project

STS的project 相当于 idea中的  module





