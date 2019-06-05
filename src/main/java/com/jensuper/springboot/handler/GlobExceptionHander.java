package com.jensuper.springboot.handler;

import com.jensuper.springboot.exception.GlobException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * All rights Reserved, Designed By www.rongdasoft.com
 *
 * @version V1.0
 * @Title: GlobExceptionHander
 * @Description:
 * @author:jichao
 * @date: 2019/6/5
 * @Copyright: 2019/6/5 www.rongdasoft.com
 * Inc. All rights reserved.
 */
@ControllerAdvice
@Slf4j
public class GlobExceptionHander {

    /**
     * 捕获指定异常
     * @param e
     * @return
     */
    @ExceptionHandler(GlobException.class)
    @ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED)
    @ResponseBody
    public Map<String, Object> getException(GlobException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", e.getCode());
        map.put("msg", e.getMsg());
        log.info("getException...");
        return map;
    }

}
