package vip.epss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vip.epss.domain.Department;
import vip.epss.service.DepartmentService;

import java.util.List;

/**
 * @创建人 epss[wangzhanf]
 * @创建时间 2020/11/24 0024
 * @描述
 */
@RestController
@RequestMapping(value = "/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping(value = "/opt")
    public Integer add(@RequestBody Department department){
        return departmentService.insert(department);
    }

    @DeleteMapping(value = "/opt/{id}")
    public Integer remove(@PathVariable("id")Integer did){
        return departmentService.delete(did);
    }

    @PutMapping(value = "/opt")
    public Integer set(@RequestBody Department department){
        return departmentService.update(department);
    }

    @GetMapping(value = "/opt/{id}")
    public Department getOne(@PathVariable("id")Integer did){
        return departmentService.selectByPrimaryKey(did);
    }

    @GetMapping(value = "/list")
    public List<Department> getAll(){
        return departmentService.select();
    }
}
