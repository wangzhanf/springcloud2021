package vip.epss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import vip.epss.domain.Department;

import java.util.List;

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

    //服务提供者的请求地址前缀
    private static final String REST_URL_PREFIX = "http://localhost:8001";
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/department/insert")
    public Integer add(Department department) {
        return restTemplate.postForObject(REST_URL_PREFIX + "/department/opt", department, Integer.class);
    }

    @RequestMapping("/consumer/department/delete/{id}")
    public void remove(@PathVariable("id") Integer did) {
        restTemplate.delete(REST_URL_PREFIX + "/department/opt/" + did);
    }

    @RequestMapping("/consumer/department/update")
    public void update(Department department) {
        restTemplate.put(REST_URL_PREFIX + "/department/opt", department);
    }

    @RequestMapping("/consumer/department/list")
    public List<Department> queryAll() {
        return restTemplate.getForObject(REST_URL_PREFIX + "/department/list", List.class);
    }

    @RequestMapping("/consumer/department/select/{id}")
    public Department queryOne(@PathVariable("id") Integer did) {
        return restTemplate.getForObject(REST_URL_PREFIX + "/department/opt/" + did, Department.class);
    }
}
