package com.jensuper.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * All rights Reserved, Designed By www.rongdasoft.com
 *
 * @version V1.0
 * @Title: ShutdownManager
 * @Description:
 * @author:jichao
 * @date: 2019/7/9
 * @Copyright: 2019/7/9 www.rongdasoft.com
 * Inc. All rights reserved.
 */
@Configuration
public class ShutdownManager {

    @Autowired
    private ApplicationContext appContext;

    public void initiateShutdown(int returnCode){
        SpringApplication.exit(appContext, () -> returnCode);
    }
}
