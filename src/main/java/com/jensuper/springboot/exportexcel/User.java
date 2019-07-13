package com.jensuper.springboot.exportexcel;

import lombok.Data;

/**
 * All rights Reserved, Designed By www.rongdasoft.com
 *
 * @version V1.0
 * @Title: User
 * @Description:
 * @author:jichao
 * @date: 2019/7/13
 * @Copyright: 2019/7/13 www.rongdasoft.com
 * Inc. All rights reserved.
 */
@Data
public class User {

    private Integer id;
    private String name;
    private String addr;

    public User() {
    }

    public User(Integer id, String name, String addr) {
        this.id = id;
        this.name = name;
        this.addr = addr;
    }
}
