package com.service;

import com.bean.ServiceInstance;
import com.util.ReflictionUtil;

public class ServiceInvoker {
    public Object invoke(ServiceInstance service, Request request){
        return ReflictionUtil.invoke(service.getTarget(), service.getMethod(), request.getParams());
    }
}
