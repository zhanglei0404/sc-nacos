package com.example.scquartz.model.quartz;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Generated;
import org.quartz.JobDataMap;

import java.util.Date;

@Data
@ApiModel(value = "JobEntity", description = "任务模型")
public class JobEntity {

    @Generated
    @ApiModelProperty("任务id")
    private int jobId;

    @ApiModelProperty("任务类型")
    private String jobType;

    @ApiModelProperty("任务组")
    private String jobGroup;

    @ApiModelProperty("任务名称")
    private String jobName;

    @ApiModelProperty("触发器名称")
    private String triggerName;

    @ApiModelProperty("触发器组")
    private String triggerGroupName;

    @ApiModelProperty("cron表达式")
    private String cronExpr;

    @ApiModelProperty("上次执行时间")
    private Date previousFireTime;

    @ApiModelProperty("下次执行时间")
    private Date nextFireTime;

    @ApiModelProperty("任务状态")
    private String jobStatus;

    @ApiModelProperty("运行时间")
    private long runTimes;

    @ApiModelProperty("持续时间")
    private long duration;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;

    @ApiModelProperty("备注")
    private String jobMemo;

    @ApiModelProperty("任务类")
    private String jobClass;

    @ApiModelProperty("任务方法")
    private String jobMethod;

    private String jobObject;

    private int count;

    @ApiModelProperty("任务数据")
    private JobDataMap jobDataMap;

}
