package com.houyongju.staservice.schedule;

import com.houyongju.staservice.service.StatisticsDailyService;
import com.houyongju.staservice.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author HouYongJu
 * @create 2021-08-22 16:51
 */
@Component
public class ScheduleTask {
    @Autowired
    private StatisticsDailyService statisticsDailyService;

    /**
     * 每天凌晨1点执行定时
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void task2() {
        //获取上一天的日期
        String day = DateUtil.formatDate(DateUtil.addDays(new Date(), -1));
        statisticsDailyService.registerCount(day);
    }
}
