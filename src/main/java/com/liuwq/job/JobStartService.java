package com.liuwq.job;

import com.dangdang.ddframe.job.api.ElasticJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.liuwq.utils.PropertiesUtil;
import com.liuwq.utils.ZKUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/7/23 0023 下午 4:37
 * @version: V1.0
 */
@Slf4j
@Component
public class JobStartService implements ApplicationListener<ContextRefreshedEvent> {

    private static JobScheduler jobScheduler = null;
    private static CoordinatorRegistryCenter regCenter = null;
    @Autowired
    private MyTaskJob myTaskJob;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println(">>>>JobStartService...onApplicationEvent()...");
//        myTaskJobStart();
    }

    /**
     * 启动渠道信息同步作业
     */
    private void myTaskJobStart() {
        try {
            log.info("启动渠道信息同步作业");
            if (null == regCenter) {
                regCenter = ZKUtils.getRegCenter();
            }
            jobScheduler = getSpringJobScheduler(myTaskJob, "myTaskJob",
                    MyTaskJob.class.getCanonicalName(),
                    PropertiesUtil.getProperty("time.task.myTask.cron"),
                    PropertiesUtil.getIntProperty("time.task.myTask.shardingcount"));

            jobScheduler.init();
            log.info("启动渠道信息同步作业完毕");
        } catch (Exception e) {
            log.error("启动渠道信息同步作业失败：" + e.getMessage());
        }

    }

    private JobScheduler getSpringJobScheduler(ElasticJob job, String jobName, String canonicalName, String cron,
                                               Integer totalPartNO) {
        return new SpringJobScheduler(job, regCenter, createJobConfiguration(jobName, canonicalName, cron, totalPartNO));
    }

    /**
     * 创建作业配置
     */
    private LiteJobConfiguration createJobConfiguration(String jobName, String canonicalName, String cron, Integer totalPartNO) {
        log.info("创建作业配置开始");
        log.info("定义作业核心配置");
        JobCoreConfiguration simpleCoreConfig = JobCoreConfiguration.newBuilder(jobName, cron, totalPartNO).build();
        log.info("定义SIMPLE类型配置");
        SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(simpleCoreConfig, canonicalName);
        log.info("定义Lite作业根配置");
        LiteJobConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig).build();
        log.info("创建作业配置结束");
        return simpleJobRootConfig;
    }

}
