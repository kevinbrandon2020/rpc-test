package com.service;

import com.myjson.JsonDecoder;
import com.myjson.JsonEncoder;
import com.transport.HttpTransportServer;
import com.transport.TransportServer;

public class RpcServerConfig {
    private Class<? extends TransportServer> transportClass = HttpTransportServer.class;
    private Class<JsonEncoder> encodeClass = JsonEncoder.class;
    private Class<JsonDecoder> decodeClass = JsonDecoder.class;
    private int port = 3000;

    public Class<? extends TransportServer> getTransportClass() {
        return transportClass;
    }

    public void setTransportClass(Class<? extends TransportServer> transportClass) {
        this.transportClass = transportClass;
    }

    public Class<JsonEncoder> getEncodeClass() {
        return encodeClass;
    }

    public void setEncodeClass(Class<JsonEncoder> encodeClass) {
        this.encodeClass = encodeClass;
    }

    public Class<JsonDecoder> getDecodeClass() {
        return decodeClass;
    }

    public void setDecodeClass(Class<JsonDecoder> decodeClass) {
        this.decodeClass = decodeClass;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
