package vip.epss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @创建人 epss[wangzhanf]
 * @创建时间 2020/11/24 0024
 * @描述
 */

//Eureka和Ribbon整合后,消费端通过服务名调用具体的服务,而不需要指定具体哪个服务器和哪个端口提供具体服务
//具体参考DepartmentConsumerController的配置
@SpringBootApplication
@EnableEurekaClient //
public class DepartmentConsumerRibbonRandom_80 {
    public static void main(String[] args) {
        SpringApplication.run(DepartmentConsumerRibbonRandom_80.class,args);
    }
}
