package com.jensuper.springboot.schedule;

import com.jensuper.springboot.config.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

/**
 * All rights Reserved, Designed By www.rongdasoft.com
 *
 * @version V1.0
 * @Title: ScheduleTask
 * @Description:
 * @author:jichao
 * @date: 2019/5/30
 * @Copyright: 2019/5/30 www.rongdasoft.com
 * Inc. All rights reserved.
 */
@Slf4j
@Component
public class ScheduleTask {

    @Autowired
    private WebSocketServer webSocketServer;

//    @Scheduled(cron = "*/6 * * * * ?")
    public void doTask() {
      log.info("......doTask.........,nowTime : {}", LocalDateTime.now());
    }

    /**
     * 6秒执行一次
     */
//    @Scheduled(cron = "*/6 * * * * ?")
    public void webMsg() {
        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            webSocketServer.sendMessage(" comefrom webMsg..... ");
        }
    }
}
