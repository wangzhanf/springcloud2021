package vip.epss.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.epss.dao.DepartmentMapper;
import vip.epss.domain.Department;
import vip.epss.service.DepartmentService;

import java.util.List;

/**
 * @创建人 epss[wangzhanf]
 * @创建时间 2020/11/24 0024
 * @描述
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public int insert(Department record) {
        return departmentMapper.insert(record);
    }

    @Override
    public int delete(Integer key) {
        return departmentMapper.delete(key);
    }

    @Override
    public int update(Department record) {
        return departmentMapper.update(record);
    }

    @Override
    public Department selectByPrimaryKey(Integer key) {
        return departmentMapper.selectByPrimaryKey(key);
    }

    @Override
    public List<Department> select() {
        return departmentMapper.select();
    }
}
