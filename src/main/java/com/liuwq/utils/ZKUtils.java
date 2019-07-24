package com.liuwq.utils;

import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.liuwq.constants.Constants;
import com.liuwq.exception.ResourceException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <br>
 * 标题: zookeeper相关工具类<br>
 * 描述: <br>
 * 公司: www.tydic.com<br>
 *
 * @author linyujia
 * @time 19:22 2018/6/13
 */
@Component
public class ZKUtils implements ApplicationListener<ContextRefreshedEvent> {

    private static final Log log = LogFactory.getLog(ZKUtils.class);

    private static CoordinatorRegistryCenter regCenter = null;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        init();
    }

    private static void init() {
        // 定义Zookeeper注册中心
        log.info("初始化zookeeper注册中心");
        regCenter = createRegistryCenter();
        log.info("初始化zookeeper注册中心完毕");
        try {
            removeAllJobs();
        } catch (Exception e) {
            log.error("清除定时任务失败：" + e.getMessage());
        }

    }

    public static CoordinatorRegistryCenter getRegCenter() {
        return regCenter;
    }

    /**
     * 连接注册中心
     */
    private static CoordinatorRegistryCenter createRegistryCenter() {
        log.info("连接注册中心");
        regCenter = new ZookeeperRegistryCenter(new ZookeeperConfiguration(PropertiesUtil.getProperty("TASK_SERVER_LISTS"), PropertiesUtil.getProperty("TASK_NAMESPACE")));
        regCenter.init();
        log.info("注册中心连接完毕");
        return regCenter;
    }

    /**
     * 移除作业节点
     **/
    private static void removerJob(String jobName) {
        try {
            String jobNode = "/" + jobName;
            log.info("移除zk节点:" + jobNode);

            if (null != regCenter) {
                log.info("all keys=" + regCenter.getChildrenKeys("/"));
                List<String> node = regCenter.getChildrenKeys(jobNode);
                log.info("node=" + node);
                if (!CollectionUtils.isEmpty(node)) {
                    regCenter.remove(jobNode);
                } else {
                    log.info("节点" + jobNode + "不存在");
                }

            }
            log.info("移除zk节点" + jobNode + "成功");
        } catch (Exception e) {
            log.error("移除zk节点失败：" + e.getMessage());
            throw new ResourceException(Constants.RESPCODE_ERROR, "移除zk节点失败：" + e.getMessage());
        }
    }

    /**
     * 获取zk所有作业节点名称
     **/
    private static List<String> getAllJobsName() {
        log.info("获取zk所有作业节点名称");
        List<String> jobNames = null;
        try {
            if (null != regCenter) {
                jobNames = regCenter.getChildrenKeys("/");
            }
        } catch (Exception e) {
            log.error("获取所有作业节点名称失败");
        }

        log.info("jobNames=" + jobNames);
        return jobNames;
    }

    private static void removeAllJobs() {
        log.info("清除所有作业");
        try {
            List<String> jobNames = getAllJobsName();
            if (CollectionUtils.isEmpty(jobNames)) {
                log.info("没有需要清除的作业");
                return;
            }
            for (String job : jobNames) {
                log.info("清除作业：" + job);
                removerJob(job);
            }
        } catch (Exception e) {
            log.error("清除所有作业失败:" + e.getMessage());
        }

        log.info("清除完毕");
    }

}
