package vip.epss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @创建人 epss[wangzhanf]
 * @创建时间 2020/11/24 0024
 * @描述
 */
@SpringBootApplication
@EnableEurekaClient //将当前作为Eureka 的client 在启动后注册进入 Eureka Server
public class DepartmentProvider_8008 {
    public static void main(String[] args) {
        SpringApplication.run(DepartmentProvider_8008.class,args);
    }
}
