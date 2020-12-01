package vip.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @创建人 epss[wangzhanf]
 * @创建时间 2020/11/29 0029
 * @描述
 */

public class MyRule  extends AbstractLoadBalancerRule {

    private int total = 0;//访问的总次数
    private int current = 0;//当前访问次数


    public MyRule() {
    }

    @SuppressWarnings({"RCN_REDUNDANT_NULLCHECK_OF_NULL_VALUE"})
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        } else {
            Server server = null;

            while(server == null) {
                if (Thread.interrupted()) {
                    return null;
                }

                List<Server> upList = lb.getReachableServers();
                List<Server> allList = lb.getAllServers();
                int serverCount = allList.size();
                if (serverCount == 0) {
                    return null;
                }

//                int index = this.chooseRandomInt(serverCount);
//                server = (Server)upList.get(index);

                //自己定义的算法规则,简单的分配原则,每个服务访问3次后换下一个服务器
                if(total<2){
                    server = upList.get(current);
                    total++;
                }else{
                    total = 0;
                    current++;
                    if(current>=upList.size()){
                        current = 0;
                    }
                    server = upList.get(current);
                }



                if (server == null) {
                    Thread.yield();
                } else {
                    if (server.isAlive()) {
                        return server;
                    }

                    server = null;
                    Thread.yield();
                }
            }

            return server;
        }
    }

    protected int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }

    public Server choose(Object key) {
        return this.choose(this.getLoadBalancer(), key);
    }

    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }
}
