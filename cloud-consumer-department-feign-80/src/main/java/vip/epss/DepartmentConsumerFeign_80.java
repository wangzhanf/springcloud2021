package vip.epss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @创建人 epss[wangzhanf]
 * @创建时间 2020/11/24 0024
 * @描述
 */

//Eureka和Ribbon整合后,消费端通过服务名调用具体的服务,而不需要指定具体哪个服务器和哪个端口提供具体服务
//具体参考DepartmentConsumerController的配置
@SpringBootApplication
@EnableEurekaClient //
@EnableFeignClients(basePackages = {"vip.epss.service"})
//@ComponentScan("vip.epss")    //本例中将接口放在了主启动类扫描的范围内了,所以不需要单独设置扫描
public class DepartmentConsumerFeign_80 {
    public static void main(String[] args) {
        SpringApplication.run(DepartmentConsumerFeign_80.class,args);
    }
}
