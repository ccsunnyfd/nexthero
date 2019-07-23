package com.nexthero.demo.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * WXSessionBO
 *
 * @version 1.0
 */
@Getter
@Setter
public class WXSessionBO {
    private String session_key;
    private String openId;
}
