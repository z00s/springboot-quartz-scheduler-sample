package team.pi.sample.scheduler.controller;

import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.Map;

/**
 * Created on 2016/12/15
 * [Function]
 *
 * @author zhangshuai
 */
public abstract class BaseResource {

    /**
     * job detail map
     */
    @Autowired
    protected Map<String, JobDetail> jobDetailMap;

    /**
     * scheduler
     */
    @Autowired
    protected SchedulerFactoryBean schedulerFactoryBean;

    protected JobDetail getJobDetail(String jobId) {

        JobDetail jobDetail = null;

        for (Map.Entry<String , JobDetail> entry : jobDetailMap.entrySet()) {

            if (entry.getKey().equals(jobId)) {

                jobDetail = entry.getValue();
                break;
            }
        }

        return jobDetail;

    }
}
