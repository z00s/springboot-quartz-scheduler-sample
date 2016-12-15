package team.pi.sample.scheduler.quartz;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import team.pi.sample.scheduler.job.SampleJob;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created on 2016/12/8
 * [Function]
 *
 * @author zhangshuai
 */
//@Service
public class QuartzLauncher {

//    @Autowired
    SchedulerFactory schedulerFactory;

    public void run() throws SchedulerException {
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail job = newJob(SampleJob.class).withIdentity("job1", "group1").build();


        Trigger trigger = newTrigger().withIdentity("job1", "group1").build();
        scheduler.scheduleJob(job, trigger);

        scheduler.start();

        try {
            // wait 65 seconds to show job
            Thread.sleep(65L * 1000L);
            // executing...
        } catch (Exception e) {
            //
        }

        scheduler.shutdown();

    }

}
