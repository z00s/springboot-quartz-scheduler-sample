package team.pi.sample.scheduler.test;

import org.junit.Test;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import team.pi.sample.scheduler.controller.MyJob;

/**
 * Created on 2016/12/8
 * [Function]
 *
 * @author zhangshuai
 */
public class QuartzTest {

    @Test
    public void hello() throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        scheduler.shutdown();
    }

    @Test
    public void quartzWithJob() throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        JobDetail job = JobBuilder
            .newJob(MyJob.class)
            .withIdentity("trigger1", "group1")
            .build();
        Trigger trigger = TriggerBuilder
            .newTrigger()
            .withIdentity("trigger1", "group1")
            .startNow()
            .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(1)
                .withRepeatCount(10))
            .build();

        scheduler.scheduleJob(job, trigger);
        scheduler.start();

        try {
            // wait 65 seconds to show job
            Thread.sleep(20L * 1000L);
            // executing...
        } catch (Exception e) {
            //
        }
        scheduler.shutdown();
    }
}
