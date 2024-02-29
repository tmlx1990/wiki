package com.siants.wiki.job;

import com.siants.wiki.service.DocService;
import com.siants.wiki.util.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class DocJob {

    @Resource
    private DocService docService;

    @Resource
    private SnowFlake snowFlake;

    /**
     * 每30秒更新电子书信息
     */
    @Scheduled(cron = "5/30 * * * * ?")
    public void cron() {
        // 增加日志流水号
        MDC.put("LOG_ID", String.valueOf(snowFlake.nextId()));
        log.info("更新电子书下的文档数据开始");
        long start = System.currentTimeMillis();
        docService.updateEbookInfo();
        log.info("更新电子书下的文档数据结束，耗时：{}毫秒", System.currentTimeMillis() - start);
    }


    /**
     * 每天更新一次电子书信息
     */
    @Scheduled(cron = "0 10 1 * * ?")
    public void crons() {
        // 增加日志流水号
        MDC.put("LOG_ID", String.valueOf(snowFlake.nextId()));
        log.info("更新电子书下的文档数据开始");
        long start = System.currentTimeMillis();
        docService.updateEbookInfo();
        log.info("更新电子书下的文档数据结束，耗时：{}毫秒", System.currentTimeMillis() - start);
    }
}
