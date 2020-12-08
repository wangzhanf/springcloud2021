package vip.epss;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * @创建人 epss[wangzhanf]
 * @创建时间 2020/11/24 0024
 * @描述
 */
@SpringBootApplication
@EnableEurekaClient //将当前作为Eureka 的client 在启动后注册进入 Eureka Server
@EnableCircuitBreaker   //启动断路器
public class DepartmentProviderHystrixDashboard_8020 {
    public static void main(String[] args) {
        SpringApplication.run(DepartmentProviderHystrixDashboard_8020.class,args);
    }

    //增加一个Servlet
    @Bean
    public ServletRegistrationBean getRegistrationBean(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new HystrixMetricsStreamServlet());
        servletRegistrationBean.addUrlMappings("/actuator/hystrix.stream");
        return servletRegistrationBean;
    }
}
