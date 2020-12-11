package vip.epss.service;

import vip.epss.domain.Department;

import java.util.List;

/**
 * @创建人 epss[wangzhanf]
 * @创建时间 2020/11/24 0024
 * @描述
 */
public interface DepartmentService {
    public int insert(Department record);
    public int delete(Integer key);
    public int update(Department record);
    public Department selectByPrimaryKey(Integer key);
    public List<Department> select();
}
