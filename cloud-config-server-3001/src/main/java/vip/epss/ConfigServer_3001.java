package vip.epss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @创建人 epss[wangzhanf]
 * @创建时间 2020/12/11 0011
 * @描述
 */
@SpringBootApplication
@EnableConfigServer //开启配置服务器
public class ConfigServer_3001 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServer_3001.class,args);
    }
}
