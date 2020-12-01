package vip.epss.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
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
    @LoadBalanced   //配置负载均衡实现RestTemplate
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
