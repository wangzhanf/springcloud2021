package vip.epss.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @创建人 epss[wangzhanf]
 * @创建时间 2020/12/11 0011
 * @描述
 */
@RestController
public class ConfigClientController {
    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${server.port}")
    private String port;
    @Value("${eureka.client.service-url.defaultZone}")
    private String eurekaServer;

    @RequestMapping(value = "/config")
    public String getConfig(){
        return "[完整的信息]" +
                "<br />applicationName : " + applicationName +
                "<br />eurekaServer : " + eurekaServer +
                "<br />port : " + port
                ;
    }
}
