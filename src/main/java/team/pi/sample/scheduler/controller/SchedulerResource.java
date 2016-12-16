package team.pi.sample.scheduler.controller;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2016/12/15
 * SchedulerResource
 *
 * @author zhangshuai
 */

@Controller
@RequestMapping("/scheduler")
public class SchedulerResource extends BaseResource{


    @GetMapping("/trigger/{jobId}")
    @ResponseBody
    public String trigger(
        @PathVariable(value="jobId") String jobId
    ) throws SchedulerException {

        if (schedulerFactoryBean == null) {
            return "Sorry. Scheduler system is down...";
        }
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        JobDetail jobDetail = getJobDetail(jobId);
        scheduler.triggerJob(jobDetail.getKey());
        return "Successfully triggered job...";
    }
}
