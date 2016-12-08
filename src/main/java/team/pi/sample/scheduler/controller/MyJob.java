package team.pi.sample.scheduler.controller;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created on 2016/12/8
 * [Function]
 *
 * @author zhangshuai
 */

@Slf4j
public class MyJob implements Job {

    private static int counter = 0;

    public MyJob() {

    }

    @Override public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("Hello World!  MyJob is executing {}", ++counter);
//        System.out.print("Hello World!  MyJob is executing" + ++counter);
    }
}
