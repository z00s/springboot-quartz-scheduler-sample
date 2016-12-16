package team.pi.sample.scheduler.job;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.pi.sample.scheduler.service.SampleService;

/**
 * Created on 2016/12/8
 * [Function]
 *
 * @author zhangshuai
 */

@Slf4j
@Getter
@Component
public class SampleJob{

    @Autowired
    private SampleService service;

    public void execute(JobExecutionContext context) throws JobExecutionException {

        service.doSomething();
    }
}
