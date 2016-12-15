package team.pi.sample.scheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Created on 2016/12/15
 * [Function]
 *
 * @author zhangshuai
 */
public class BaseResource {

    /**
     * scheduler
     */
    @Autowired
    protected SchedulerFactoryBean schedulerFactoryBean;
}
