package com.jensuper.springboot.controller;

import com.jensuper.springboot.VO.User;
import com.jensuper.springboot.exception.GlobException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * All rights Reserved, Designed By www.rongdasoft.com
 *
 * @version V1.0
 * @Title: RedisController
 * @Description:
 * @author:jichao
 * @date: 2019/5/30
 * @Copyright: 2019/5/30 www.rongdasoft.com
 * Inc. All rights reserved.
 */
@RestController
public class Controller {

    /**
     * 自动添加redis
     * @return
     */
    @GetMapping("/redis")
    @Cacheable("user-key")
    public String redisTest() {
        User user = new User();
        user.setId(2);
        user.setName("redis");
        System.out.println("执行成功");
        return "success";
    }

    /**
     * 过滤器
     */
    @GetMapping("/filter")
    public void filter(){
        System.out.println("filter-controller");
    }

    /**
     * 自定义异常捕获
     */
    @GetMapping("/ex")
    public void ex() {
        throw new GlobException(1, "zero");
    }

}
