package com.liuwq.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/7/23 0023 下午 4:43
 * @version: V1.0
 */
@Component
public class MyTaskJob implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println(">>>MyTaskJob...execute()..." + new Date().toLocaleString());
    }
}
