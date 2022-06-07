package com.service;

import java.io.Serializable;
import java.util.Arrays;

public class Request implements Serializable {

    private static final long serialVersionUID = -3841906485808439929L;
    private ServiceDescriptor service;
    private Object[] params;

    public ServiceDescriptor getService() {
        return service;
    }

    public void setService(ServiceDescriptor service) {
        this.service = service;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "Request{" +
                "service=" + service +
                ", params=" + Arrays.toString(params) +
                '}';
    }
}
