package vip.epss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @创建人 epss[wangzhanf]
 * @创建时间 2020/11/24 0024
 * @描述
 */
@SpringBootApplication
@EnableEurekaClient //将当前作为Eureka 的client 在启动后注册进入 Eureka Server
@EnableCircuitBreaker   //启动断路器
public class DepartmentProviderHystrix_8014 {
    public static void main(String[] args) {
        SpringApplication.run(DepartmentProviderHystrix_8014.class,args);
    }
}
