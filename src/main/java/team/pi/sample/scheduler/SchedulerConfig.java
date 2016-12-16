package team.pi.sample.scheduler;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import team.pi.sample.scheduler.job.SampleJob;
import team.pi.sample.scheduler.spring.AutowiringSpringBeanJobFactory;

import java.io.IOException;
import java.util.Properties;


/**
 * Created on 2016/12/8
 * [Function]
 *
 * @author zhangshuai
 */
@Configuration
@ConditionalOnProperty(name = "quartz.enabled")
public class SchedulerConfig {

    /**
     * create scheduler bean
     *
     * @return schedulerFactoryBean
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(
        JobFactory jobFactory,
        @Qualifier(value = "sampleJobTrigger") Trigger sampleJobTrigger,
        @Qualifier(value = "job1Trigger") Trigger sampleCronTrigger
    ) throws IOException {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        // You can set a series of triggers here
        schedulerFactoryBean.setJobFactory(jobFactory);
        schedulerFactoryBean.setQuartzProperties(quartzProperties());
        schedulerFactoryBean.setTriggers(sampleJobTrigger, sampleCronTrigger);

        return schedulerFactoryBean;
    }

    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    @Bean(name = "job1")
    public JobDetailFactoryBean jobDetailFactoryBean() {
        return createJobDetail(SampleJob.class);
    }

    @Bean(name = "sampleJobTrigger")
    public SimpleTriggerFactoryBean simpleTriggerFactoryBean (
        @Qualifier("job1") JobDetail jobDetail
    ) {
        return createTrigger(jobDetail);
    }

    @Bean(name = "job1Trigger")
    public CronTriggerFactoryBean cronTriggerFactoryBean (
        @Qualifier("job1") JobDetail jobDetail
    ) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetail);
        factoryBean.setStartDelay(3000);
        factoryBean.setName("cronTrigger");
        factoryBean.setGroup("testGroup");
        factoryBean.setCronExpression("0/10 * * * * ?");
        return factoryBean;
    }

    /**
     * helper to create JobDetails
     *
     * @param jobDetailClazz class you want to create
     * @return new jobDetail instance
     */
    private JobDetailFactoryBean createJobDetail(Class jobDetailClazz){
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(jobDetailClazz);
        factoryBean.setName("helloJob");
        factoryBean.setGroup("myGroup");
        return factoryBean;
    }

    /**
     * helper to create trigger
     *
     * @param jobDetail
     * @return
     */
    private SimpleTriggerFactoryBean createTrigger(JobDetail jobDetail) {
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();

        factoryBean.setJobDetail(jobDetail);

        factoryBean.setStartDelay(3000);        // 3s
        factoryBean.setRepeatInterval(3000);    // 3s
        factoryBean.setRepeatCount(5);          // 5 + 1 times

        return factoryBean;
    }

}
