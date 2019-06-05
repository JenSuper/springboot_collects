package com.jensuper.springboot.controller;

import com.jensuper.springboot.config.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * All rights Reserved, Designed By www.rongdasoft.com
 *
 * @version V1.0
 * @Title: IndexController
 * @Description:
 * @author:jichao
 * @date: 2019/5/31
 * @Copyright: 2019/5/31 www.rongdasoft.com
 * Inc. All rights reserved.
 */
@Controller
@Slf4j
public class IndexController {

    @Autowired
    private WebSocketServer webSocketServer;

    /**
     * 跳转到index页面
     *
     * @return
     */
    @RequestMapping("/web")
    public ModelAndView index() {
        log.info("...........index............");
        return new ModelAndView("/index");
    }

    /**
     * websocket
     * 发送消息
     */
    @GetMapping("/sendMsg")
    @ResponseBody
    public void sendMsg(){
        String msg = " comefrom webMsg.....";
        webSocketServer.sendMessage(msg);
    }
}
