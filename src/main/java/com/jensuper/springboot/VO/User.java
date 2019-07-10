package com.jensuper.springboot.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * All rights Reserved, Designed By www.rongdasoft.com
 *
 * @version V1.0
 * @Title: User
 * @Description:
 * @author:jichao
 * @date: 2019/5/30
 * @Copyright: 2019/5/30 www.rongdasoft.com
 * Inc. All rights reserved.
 */
@Data
public class User {

    @JsonProperty("用户id")
    private Integer id;
    @JsonProperty("用户名")
    private String name;

    public User() {
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
