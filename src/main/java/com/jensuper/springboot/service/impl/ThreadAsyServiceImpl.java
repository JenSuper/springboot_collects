package com.jensuper.springboot.service.impl;

import com.jensuper.springboot.service.ThreadAsyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * All rights Reserved, Designed By www.rongdasoft.com
 *
 * @version V1.0
 * @Title: ThreadAsyServiceImpl
 * @Description:
 * @author:jichao
 * @date: 2019/6/11
 * @Copyright: 2019/6/11 www.rongdasoft.com
 * Inc. All rights reserved.
 */
@Service
@Slf4j
public class ThreadAsyServiceImpl implements ThreadAsyService {

    @Override
    @Async
    public void sycService() {
        log.info("ThreadAsyServiceImpl ----START----");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("ThreadAsyServiceImpl ----END----");
    }
}
