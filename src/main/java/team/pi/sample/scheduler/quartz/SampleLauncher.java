package team.pi.sample.scheduler.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import team.pi.sample.scheduler.job.SampleJob;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created on 2016/12/8
 * Wrapper class holds a sample job
 *
 * @author zhangshuai
 */
@Slf4j
public class SampleLauncher extends QuartzJobBean {

    @Autowired
    SampleJob sampleJob;

    @Override protected void executeInternal(JobExecutionContext context)
        throws JobExecutionException {

        if (log.isInfoEnabled()) {
            log.info("[START] {} : {}", context.getJobDetail().getKey(), context.getFireTime());
        }

        sampleJob.execute(context);

        if (log.isInfoEnabled()) {
            log.info("[END] {} : {}", context.getJobDetail().getKey(), context.getFireTime());
        }

    }
}
