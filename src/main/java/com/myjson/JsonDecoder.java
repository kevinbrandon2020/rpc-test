package com.myjson;

import com.alibaba.fastjson.JSON;

public class JsonDecoder {
    public <T> T decode(byte[] bytes, Class<T> clazz){
        return JSON.parseObject(bytes, clazz);
    }
}
