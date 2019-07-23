package com.nexthero.demo.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

/**
 * NEXTJSONResult
 *
 * @version 1.0
 */
@Getter
@Setter
public class NEXTJSONResult {
    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    private String ok;    // 不使用

    public static NEXTJSONResult build(Integer status, String msg, Object data) {
        return new NEXTJSONResult(status, msg, data);
    }

    public static NEXTJSONResult ok(Object data) {
        return new NEXTJSONResult(data);
    }

    public static NEXTJSONResult errorMsg(String msg) {
        return new NEXTJSONResult(500, msg, null);
    }

    public static NEXTJSONResult errorMap(Object data) {
        return new NEXTJSONResult(501, "error", data);
    }

    public static NEXTJSONResult errorTokenMsg(String msg) {
        return new NEXTJSONResult(502, msg, null);
    }

    public static NEXTJSONResult errorException(String msg) {
        return new NEXTJSONResult(555, msg, null);
    }

    public NEXTJSONResult() {
    }

    public NEXTJSONResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public NEXTJSONResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public static NEXTJSONResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, NEXTJSONResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    public static NEXTJSONResult format(String json) {
        try {
            return MAPPER.readValue(json, NEXTJSONResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
