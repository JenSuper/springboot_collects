package com.jensuper.springboot.controller;

import com.jensuper.springboot.config.websocket.WebSocketServer;
import io.xjar.XKit;
import io.xjar.boot.XBoot;
import io.xjar.key.XKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.awt.image.PixelConverter;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

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
        return new ModelAndView("index");
    }

    /**
     * websocket
     * 发送消息
     */
    @GetMapping("/sendMsg")
    @ResponseBody
    public void sendMsg() {
        String msg = " comefrom webMsg.....";
        log.info("【sendMsg】msg={msg}", msg);
        log.info("【sendMsg】{webSocketServer}", webSocketServer);
        System.out.println(msg);
        System.out.println(webSocketServer);
        webSocketServer.sendMessage(msg);
    }

    public static void main(String[] args) {

        // Spring-Boot Jar包加密
        String password = "io.xjar";
        XKey xKey = null;
        try {
            xKey = XKit.key(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
//            XBoot.encrypt("E:\\softs\\myproject\\poly\\web\\target\\web-0.0.1-SNAPSHOT.jar", "E:\\softs\\myproject\\poly\\web\\target\\springboot-web.jar", xKey);
            XBoot.encrypt("E:\\softs\\ide\\hcb\\2.0\\Rd-Check\\Rd-Check-View\\target\\Rd-Check-View-0.0.1-SNAPSHOT.jar", "E:\\softs\\ide\\hcb\\2.0\\Rd-Check\\Rd-Check-View\\target\\springboot-view.jar", xKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
