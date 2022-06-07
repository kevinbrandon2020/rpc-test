package com.service;

import com.bean.ServiceInstance;
import com.util.ReflictionUtil;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceManager {
    private Map<ServiceDescriptor, ServiceInstance> services;
    public ServiceManager(){
        this.services = new ConcurrentHashMap<>();
    }

    //注册服务
    public <T> void register(Class<T> interfaceClass, T bean){
        Method[] methods = ReflictionUtil.getPublicMethod(interfaceClass);
        for (Method method : methods) {
            ServiceInstance serviceInstance = new ServiceInstance(bean, method);
            ServiceDescriptor sdp = ServiceDescriptor.from(interfaceClass, method);
            services.put(sdp, serviceInstance);
        }
    }

    public ServiceInstance lookup(Request request){
        ServiceDescriptor service = request.getService();
        return services.get(service);
    }
}
