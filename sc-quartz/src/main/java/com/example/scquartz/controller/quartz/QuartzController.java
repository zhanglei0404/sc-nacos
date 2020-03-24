package com.example.scquartz.controller.quartz;

import com.alibaba.fastjson.JSONObject;
import com.example.scquartz.model.common.RtnResult;
import com.example.scquartz.model.quartz.JobEntity;
import com.example.scquartz.model.quartz.JobParam;
import com.example.scquartz.service.quartz.QuartzService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 定时任务Controller
 */
@RestController
@RequestMapping(value = "/quartz")
@Api(tags = "定时器控制器", value = "定时器控制器")
public class QuartzController {
    @Autowired
    private Scheduler quartzScheduler;
    @Autowired
    private QuartzService quartzService;

    @GetMapping(value = "queryAllJob")
    @ApiOperation(value = "查询所有定时任务")
    public RtnResult<List<JobEntity>> listJob() {
        return RtnResult.successInfo("查询成功", quartzService.queryAllJob());
    }

    @GetMapping(value = "listJob")
    @ApiOperation(value = "查询所有正在执行的定时任务")
    public RtnResult<List<JobEntity>> queryRunJob() {
        return RtnResult.successInfo("查询成功", quartzService.queryRunJob());
    }

    @PostMapping(value = "add")
    @ApiOperation(value = "增加定时任务")
    public RtnResult add(@RequestBody JobParam jobParam) {
        //任务名
        String jobName = jobParam.getJobName();
        //任务组名
        String jobGroup = jobParam.getJobGroup();
        //任务处理类
        String clazz = jobParam.getClazz();
        //json格式的参数
        String params = jobParam.getParams();
        Class cls = null;
        try {
            cls = Class.forName(clazz);
            //时间设置
            String cron = jobParam.getCronExpr();
            quartzService.addJob(cls, jobName, jobGroup, cron, JSONObject.parseObject(params, Map.class));
        } catch (Exception e) {
            e.printStackTrace();
            return RtnResult.errorInfo("添加任务失败!", "");
        }
        return RtnResult.successInfo("添加任务成功!", "");
    }


    @GetMapping(value = "toEdit")
    @ApiOperation(value = "定时任务详细信息")
    public RtnResult<JobParam> toEdit(@ApiParam(value = "任务名称", name = "jobName", required = true) @RequestParam("jobName") String jobName,
                                      @ApiParam(value = "任务组", name = "jobGroup", required = true) @RequestParam("jobGroup") String jobGroup) throws SchedulerException {

        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        JobDetail jd = quartzScheduler.getJobDetail(jobKey);
        List<CronTrigger> triggers = (List<CronTrigger>) quartzScheduler.getTriggersOfJob(jobKey);
        CronTrigger trigger = triggers.get(0);
        String cron = trigger.getCronExpression();
        JobParam jobParam = new JobParam();
        jobParam.setJobName(jobName);
        jobParam.setJobGroup(jobGroup);
        jobParam.setClazz(jd.getJobClass().getCanonicalName());
        jobParam.setParams(JSONObject.toJSONString(jd.getJobDataMap()));
        jobParam.setCronExpr(cron);
        return RtnResult.successInfo("查询任务成功!", jobParam);
    }


    @PostMapping(value = "updateJob")
    @ApiOperation(value = "定时任务修改cron")
    public RtnResult updateJob(@RequestBody JobParam jobParam) {
        String jobName = jobParam.getJobName();
        String jobGroup = jobParam.getJobGroup();
        String cron = jobParam.getCronExpr();
        try {
            quartzService.updateJob(jobName, jobGroup, cron);
        } catch (Exception e) {
            e.printStackTrace();
            return RtnResult.errorInfo("修改任务失败!", "");
        }

        return RtnResult.successInfo("修改任务成功!", "");
    }


    @PostMapping(value = "pauseJob")
    @ApiOperation(value = "暂停任务")
    public RtnResult pauseJob(@ApiParam(value = "任务名称", name = "jobName", required = true) @RequestParam("jobName") String jobName,
                              @ApiParam(value = "任务组", name = "jobGroup", required = true) @RequestParam("jobGroup") String jobGroup) {

        if (StringUtils.isBlank(jobName) || StringUtils.isBlank(jobGroup)) {
            return RtnResult.errorInfo("暂停任务失败，任务名为空!", "");
        } else {
            quartzService.pauseJob(jobName, jobGroup);
            return RtnResult.successInfo("暂停任务成功!", "");
        }

    }

    @PostMapping(value = "resumeJob")
    @ApiOperation(value = "恢复任务")
    public RtnResult resumeJob(@ApiParam(value = "任务名称", name = "jobName", required = true) @RequestParam("jobName") String jobName,
                               @ApiParam(value = "任务组", name = "jobGroup", required = true) @RequestParam("jobGroup") String jobGroup) {

        if (StringUtils.isBlank(jobName) || StringUtils.isBlank(jobGroup)) {
            return RtnResult.errorInfo("恢复任务失败，任务名为空!", "");
        } else {
            quartzService.resumeJob(jobName, jobGroup);
            return RtnResult.successInfo("恢复任务成功!", "");
        }
    }

    @PostMapping(value = "deleteJob")
    @ApiOperation(value = "删除任务")
    public RtnResult deleteJob(@ApiParam(value = "任务名称", name = "jobName", required = true) @RequestParam("jobName") String jobName,
                               @ApiParam(value = "任务组", name = "jobGroup", required = true) @RequestParam("jobGroup") String jobGroup) {
        if (StringUtils.isBlank(jobName) || StringUtils.isBlank(jobGroup)) {
            return RtnResult.errorInfo("删除任务失败，任务名为空!", "");
        } else {
            quartzService.deleteJob(jobName, jobGroup);
            return RtnResult.successInfo("删除任务成功!", "");
        }
    }

    @PostMapping(value = "startNow")
    @ApiOperation(value = "启动任务")
    public void startNow(@ApiParam(value = "任务名称", name = "jobName", required = true) @RequestParam("jobName") String jobName,
                         @ApiParam(value = "任务组", name = "jobGroup", required = true) @RequestParam("jobGroup") String jobGroup) throws Exception {
        quartzService.runAJobNow(jobName, jobGroup);
    }
}
