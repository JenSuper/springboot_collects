package com.jensuper.springboot.controller;

import com.jensuper.springboot.exportexcel.ExportExcel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * All rights Reserved, Designed By www.rongdasoft.com
 *
 * @version V1.0
 * @Title: TestController
 * @Description:
 * @author:jichao
 * @date: 2019/7/13
 * @Copyright: 2019/7/13 www.rongdasoft.com
 * Inc. All rights reserved.
 */
@Controller
public class TestController {

    @RequestMapping("/export")
    @ResponseBody
    public void export(String jsonString,HttpServletRequest request,HttpServletResponse response){
        jsonString = "[{\"id\":1,\"name\":\"张三\",\"addr\":\"海淀\"},{\"id\":2,\"name\":\"李四\",\"addr\":\"西城区\"}]";
        try {
            ExportExcel.exportMyCrm(jsonString, request,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
