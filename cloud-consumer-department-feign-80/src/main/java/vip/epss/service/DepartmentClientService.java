package vip.epss.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import vip.epss.domain.Department;
import vip.epss.domain.MessageAndData;

/**
 * @创建人 epss[wangzhanf]
 * @创建时间 2020/11/29 0029
 * @描述
 */

/*
* @FeignClient(value = "微服务的名称")
* */
@FeignClient(value = "CLOUD-PROVIDER-DEPARTMENT")
@Component
public interface DepartmentClientService {
    @PostMapping(value = "/department/opt")
    public MessageAndData insert(Department department);

    @DeleteMapping(value = "/department/opt/{id}")
    public void delete(@PathVariable("id")Integer did);

    @PutMapping(value = "/department/opt")
    public void update(Department department);

    @GetMapping(value = "/department/list")
    public MessageAndData queryAll();

    @GetMapping("/department/opt/{did}")
    public MessageAndData queryOne(@PathVariable("did") Integer did);
}
