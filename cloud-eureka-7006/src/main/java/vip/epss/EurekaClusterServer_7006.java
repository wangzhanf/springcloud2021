package vip.epss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @创建人 epss[wangzhanf]
 * @创建时间 2020/11/24 0024
 * @描述
 */
@SpringBootApplication
@EnableEurekaServer     //添加该注解意味着这是一个Eureka的服务器,可以接受客户端注册进来
@EnableDiscoveryClient  //服务发现
public class EurekaClusterServer_7006 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClusterServer_7006.class,args);
    }
}
