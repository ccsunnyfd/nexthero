package com.nexthero.demo.model;

import lombok.Getter;
import lombok.Setter;

/**
 * RespBean
 *
 * @version 1.0
 */
@Getter
@Setter
public class RespBean {
    private String status;
    private String msg;

    public RespBean(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
