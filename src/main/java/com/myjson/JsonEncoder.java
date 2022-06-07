package com.myjson;


import com.alibaba.fastjson.JSON;

public class JsonEncoder{
    public byte[] encode(Object obj){
        return JSON.toJSONBytes(obj);
    }

}
