package vip.epss.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import vip.epss.domain.Department;
import vip.epss.domain.MessageAndData;
import vip.epss.service.DepartmentService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @创建人 epss[wangzhanf]
 * @创建时间 2020/11/24 0024
 * @描述
 */
@RestController
@Slf4j
@RequestMapping(value = "/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/opt")
    public MessageAndData add(@RequestBody Department department) {
        int i = departmentService.insert(department);
        return MessageAndData.success("").add("num", i);
        //return departmentService.insert(department);
    }

    @DeleteMapping(value = "/opt/{id}")
    public MessageAndData remove(@PathVariable("id") Integer did) {
        int i = departmentService.delete(did);
        return MessageAndData.success("").add("num", i);
//        return departmentService.delete(did);
    }

    @PutMapping(value = "/opt")
    public MessageAndData set(@RequestBody Department department) {
        int i = departmentService.update(department);
        return MessageAndData.success("").add("num", i);
    }

    @GetMapping(value = "/opt/{id}")
    public MessageAndData getOne(@PathVariable("id") Integer did) {
        Department department = departmentService.selectByPrimaryKey(did);
        return MessageAndData.success("").add("obj", department);
    }

    @GetMapping(value = "/list")
    public MessageAndData getAll() {
        List<Department> departmentList = departmentService.select();
        return MessageAndData.success("").add("obj", departmentList).add("serverPort",serverPort);
    }


    /*提供微服务信息给调用者查阅时*/
    @GetMapping(value = "/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println("*****element: " + element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-DEPARTMENT");
        for (ServiceInstance instance : instances) {
            System.out.println(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }

        return this.discoveryClient;
    }
}
