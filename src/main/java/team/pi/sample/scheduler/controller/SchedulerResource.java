package team.pi.sample.scheduler.controller;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team.pi.sample.scheduler.job.SampleJob;

/**
 * Created on 2016/12/15
 * SchedulerResource
 *
 * @author zhangshuai
 */

@Controller
@RequestMapping("/scheduler")
public class SchedulerResource extends BaseResource{


    @GetMapping("/trigger")
    @ResponseBody
    public String trigger() throws SchedulerException {

        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey key = new JobKey("cronTrigger", "testGroup");
        SampleJob job = (SampleJob) scheduler.getJobDetail(key);
        job.getService().reset();
        scheduler.triggerJob(key);
        return "Successfully triggered job...";
    }
}
