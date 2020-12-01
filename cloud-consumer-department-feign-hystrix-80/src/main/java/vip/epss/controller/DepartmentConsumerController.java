package vip.epss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import vip.epss.domain.Department;
import vip.epss.domain.MessageAndData;
import vip.epss.service.DepartmentClientService;

import javax.annotation.Resource;

/**
 * @创建人 epss[wangzhanf]
 * @创建时间 2020/11/24 0024
 * @描述
 */
@RestController
public class DepartmentConsumerController {
    //通过RestTemplate请求服务提供者
    //首先注册一个RestTemplate,通过源码会发现RestTemplate没有提供Bean哦
    //注册的方式: 创建一个配置类获取Bean即可

    //服务提供者的请求地址前缀,这是没有负载均衡时通过指定地址获得
    //    private static final String REST_URL_PREFIX = "http://localhost:8001";
    //这是通过Ribbon实现负载均衡时,通过服务名来访问实现负载均衡
    //    private static final String REST_URL_PREFIX = "http://CLOUD-PROVIDER-DEPARTMENT";


    //通过feign的接口方式实现负载均衡,自动注入
//    @Resource
    @Autowired
    private DepartmentClientService departmentClientService;

    @RequestMapping("/consumer/department/insert")
    public MessageAndData add(Department department) {
        return departmentClientService.insert(department);
    }

    @RequestMapping("/consumer/department/delete/{id}")
    public void remove(@PathVariable("id") Integer did) {
        departmentClientService.delete(did);
    }

    @RequestMapping("/consumer/department/update")
    public void update(Department department) {
        departmentClientService.update(department);
    }

    @RequestMapping("/consumer/department/list")
    public MessageAndData queryAll() {
        return departmentClientService.queryAll();
    }

    @RequestMapping("/consumer/department/select/{did}")
    public MessageAndData queryOne(@PathVariable("did") Integer did) {
        return departmentClientService.queryOne(did);
    }
}
