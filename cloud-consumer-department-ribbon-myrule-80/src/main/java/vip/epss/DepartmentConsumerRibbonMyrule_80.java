package vip.epss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import vip.rule.MyRule;

/**
 * @创建人 epss[wangzhanf]
 * @创建时间 2020/11/24 0024
 * @描述
 */

//Eureka和Ribbon整合后,消费端通过服务名调用具体的服务,而不需要指定具体哪个服务器和哪个端口提供具体服务
//具体参考DepartmentConsumerController的配置
@SpringBootApplication
@EnableEurekaClient //
/*使用自定义的负载均衡算法,本例中自定义的算法在vip.rule包中,和主启动类的包属于兄弟关系,所以主启动类是不能扫描到该配置的,需要手工指定ribbon的算法bean
*   使得微服务启动的时候就能加载自定义的算法规则
*   @RibbonClient(name = "服务的名称",configuration = 自定义算法规则的类)
* */
//@RibbonClient(name = "CLOUD-PROVIDER-DEPARTMENT",configuration = MyRule.class)
public class DepartmentConsumerRibbonMyrule_80 {
    public static void main(String[] args) {
        SpringApplication.run(DepartmentConsumerRibbonMyrule_80.class,args);
    }
}
