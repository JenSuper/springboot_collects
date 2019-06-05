package com.jensuper.springboot.exception;

import lombok.Data;

/**
 * All rights Reserved, Designed By www.rongdasoft.com
 *
 * @version V1.0
 * @Title: GlobException
 * @Description:
 * @author:jichao
 * @date: 2019/6/5
 * @Copyright: 2019/6/5 www.rongdasoft.com
 * Inc. All rights reserved.
 */
@Data
public class GlobException extends RuntimeException {
    private Integer code;
    private String msg;

    public GlobException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
