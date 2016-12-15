package team.pi.sample.scheduler.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created on 2016/12/15
 * [Function]
 *
 * @author zhangshuai
 */
@Service
@Slf4j
public class SampleService {

    private static int counter = 0;

    /**
     * Sample method
     */
    public void doSomething() {
        log.info("Hello World! SampleJob is executing {}", ++counter);
    }
}
