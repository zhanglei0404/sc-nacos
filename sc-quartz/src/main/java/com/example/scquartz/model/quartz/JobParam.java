package com.example.scquartz.model.quartz;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("任务参数")
public class JobParam implements Serializable {
    @ApiModelProperty("任务组")
    private String jobGroup;

    @ApiModelProperty("任务名称")
    private String jobName;

    @ApiModelProperty("完整类名")
    private String clazz;

    @ApiModelProperty("参数（json）")
    private String params;

    @ApiModelProperty("cron表达式")
    private String cronExpr;
}
