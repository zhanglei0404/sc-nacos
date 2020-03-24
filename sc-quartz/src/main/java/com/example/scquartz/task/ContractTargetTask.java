package com.example.scquartz.task;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * 指标抽取任务
 */
@Slf4j
@Component
public class ContractTargetTask extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.debug("ContractTargetTask任务执行了");
    }
}
