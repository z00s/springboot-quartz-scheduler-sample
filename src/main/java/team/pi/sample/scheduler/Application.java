package team.pi.sample.scheduler;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * Created on 2016/12/8
 * [Function]
 *
 * @author zhangshuai
 */
@SpringBootApplication
@Import({
    SchedulerConfig.class
})
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
            .bannerMode(Banner.Mode.OFF)
            .run(args);
    }
}
