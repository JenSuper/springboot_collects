package com.jensuper.springboot.exportexcel;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * All rights Reserved, Designed By www.rongdasoft.com
 *
 * @version V1.0
 * @Title: ExportExcel
 * @Description:
 * @author:jichao
 * @date: 2019/7/13
 * @Copyright: 2019/7/13 www.rongdasoft.com
 * Inc. All rights reserved.
 */
public class ExportExcel {

    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        User user = new User(1,"张三","海淀");
        User user2 = new User(2,"李四","西城区");
        list.add(user);
        list.add(user2);

        Gson gson = new Gson();
        String jsonString = gson.toJson(list);

        System.out.println(jsonString);
        System.out.println("-----------------------结束 -------------");
    }
    public static  void exportMyCrm(String jsonString,HttpServletRequest request,HttpServletResponse response) throws IOException {
        List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();
        // 构造对象等同于@Excel
        entity.add(new ExcelExportEntity("用户id", "id"));
        entity.add(new ExcelExportEntity("名字", "name"));
        entity.add(new ExcelExportEntity("地址", "addr"));

        Gson gson = new Gson();
        List<User> userList = gson.fromJson(jsonString, new TypeToken<List<User>>() {
        }.getType());

        List<Map<String, Object>> list = new ArrayList<>();

        userList.stream().forEach(user -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", user.getId());
            map.put("name", user.getName());
            map.put("addr", user.getAddr());
            list.add(map);
        });

        // 当前日期，用于导出文件名称
        LocalDateTime dateTime = LocalDateTime.now();
        String fileName = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"exportexcel";
        // 乱码处理
        fileName = FileNameUtils.encoding(request, fileName);
        response.setContentType("application/x-msdownload");
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户", "用户信息"), entity, list);
        workbook.write(response.getOutputStream());
    }

}
