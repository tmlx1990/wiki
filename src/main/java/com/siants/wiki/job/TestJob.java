package com.siants.wiki.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class TestJob {

    /**
     * 固定时间间隔，fixedRate单位毫秒
     * @throws InterruptedException
     */
    @Scheduled(fixedRate = 5000)
    public void simple() throws InterruptedException {
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
        String dateString = formatter.format(new Date());
        //Thread.sleep(2000);
        log.info("每隔5秒执行一次: {}", dateString);
    }

    /**
     * 自定义cron表达式跑批
     * 只有等上一次执行完成，下一次才会下一个时间点执行，错过就错过
     */
    @Scheduled(cron = "0/2 * * * * ?")
    public void cron() throws InterruptedException {
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss:SSS");
        String dateString = formatter.format(new Date());
        //Thread.sleep(1500);
        log.info("每隔2秒钟执行一次: {}", dateString);
    }
}
