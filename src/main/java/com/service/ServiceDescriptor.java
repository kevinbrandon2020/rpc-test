package com.service;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ServiceDescriptor {
    private static final long serialVersionUID = -7410838223648929931L;
    private String clazz;
    private String method;
    private String returnType;
    private String[] paramTypes;

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || o.getClass() != getClass()) return false;
        ServiceDescriptor serviceDescriptor = (ServiceDescriptor)o;
        return serviceDescriptor.toString().equals(o.toString());
    }

    @Override
    public int hashCode(){
        return toString().hashCode();
    }

    @Override
    public String toString(){
        return "class: " + clazz
                + ", method: " + method
                + ", returnType: " + returnType
                + ", parameterTypes: " + Arrays.toString(paramTypes);
    }

    public static ServiceDescriptor from(Class clazz, Method method){
        ServiceDescriptor sdp = new ServiceDescriptor();
        sdp.setClazz(clazz.getName());
        sdp.setMethod(method.getName());
        sdp.setReturnType(method.getReturnType().getName());

        Class<?>[] parameterTypes = method.getParameterTypes();
        String[] parameters = new String[parameterTypes.length];
        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = parameterTypes[i].getName();
        }
        sdp.setParamTypes(parameters);
        return sdp;
    };

    public String getClazz() {
        return clazz;
    }

    public String getMethod() {
        return method;
    }

    public String getReturnType() {
        return returnType;
    }

    public String[] getParamTypes() {
        return paramTypes;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public void setParamTypes(String[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    public ServiceDescriptor() {
    }

    public ServiceDescriptor(String clazz, String method, String returnType, String[] paramTypes) {
        this.clazz = clazz;
        this.method = method;
        this.returnType = returnType;
        this.paramTypes = paramTypes;
    }
}
