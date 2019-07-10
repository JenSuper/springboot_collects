package com.jensuper.springboot.controller;

import com.google.gson.Gson;
import com.jensuper.springboot.VO.User;
import com.jensuper.springboot.service.ThreadAsyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * All rights Reserved, Designed By www.rongdasoft.com
 *
 * @version V1.0
 * @Title: ThreadController
 * @Description:
 * @author:jichao
 * @date: 2019/6/11
 * @Copyright: 2019/6/11 www.rongdasoft.com
 * Inc. All rights reserved.
 */
@RestController
@RequestMapping("/thread")
@Slf4j
public class ThreadController {

    @Autowired
    private ThreadAsyService threadAsyService;

    /**
     * 异步线程池
     * http://localhost:8888/thread/taskManager
     *
     * @return
     */
    @GetMapping("/taskManager")
    public void taskManager() {
        log.info("taskManager - controller -------START------");
        threadAsyService.sycService();
        log.info("taskManager - controller -------EDN------");
    }

    @GetMapping("/voTest")
    public String voTest() {
        User user = new User(1,"小明");
        return new Gson().toJson(user);
    }
}
