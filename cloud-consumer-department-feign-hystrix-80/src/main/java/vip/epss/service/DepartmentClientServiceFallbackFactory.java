package vip.epss.service;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import vip.epss.domain.Department;
import vip.epss.domain.MessageAndData;

/**
 * @创建人 epss[wangzhanf]
 * @创建时间 2020/12/1 0001
 * @描述
 */
@Component
public class DepartmentClientServiceFallbackFactory implements FallbackFactory {
    @Override
    public Object create(Throwable throwable) {
        return new DepartmentClientService() {
            @Override
            public MessageAndData insert(Department department) {
                return null;
            }

            @Override
            public void delete(Integer did) {

            }

            @Override
            public void update(Department department) {

            }

            @Override
            public MessageAndData queryAll() {
                return null;
            }

            /*本例中仅仅实现了该备选方法*/
            @Override
            public MessageAndData queryOne(Integer did) {
                return MessageAndData.success("").add("obj",new Department(did,"该服务器已停用","数据库中没有信息"));
            }
        };
    }
}
