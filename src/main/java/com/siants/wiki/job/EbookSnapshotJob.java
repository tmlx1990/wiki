package com.siants.wiki.job;

import com.siants.wiki.service.EbookSnapshotService;
import com.siants.wiki.util.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class EbookSnapshotJob {

    @Resource
    private EbookSnapshotService ebookSnapshotService;

    @Resource
    private SnowFlake snowFlake;

    @Scheduled(cron = "0/5 * * * * ?")
    public void doSnapshot() {
        // 增加日志流水号
        MDC.put("LOG_ID", String.valueOf(snowFlake.nextId()));
        log.info("生成今日电子书快照开始");
        long start = System.currentTimeMillis();
        ebookSnapshotService.genSnapshot();
        log.info("生成今日电子书快照结束，耗时：{}毫秒", System.currentTimeMillis() - start);
    }
}
