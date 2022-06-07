package com.example;

import com.myjson.JsonDecoder;
import com.myjson.JsonEncoder;
import com.service.RandomSelector;
import com.service.RemoteInvoker;
import com.service.RpcClientConfig;
import com.util.ReflictionUtil;

import java.lang.reflect.Proxy;

public class RpcClient {
    private RpcClientConfig config;
    private JsonEncoder encoder;
    private JsonDecoder decoder;
    private RandomSelector selector;

    public RpcClient() {
        this(new RpcClientConfig());
    }

    public RpcClient(RpcClientConfig config) {
        this.config = config;

        this.encoder = ReflictionUtil.newInstance(this.config.getEncodeClass());

        this.decoder = ReflictionUtil.newInstance(this.config.getDecodeClass());

        this.selector = (RandomSelector) ReflictionUtil.newInstance(this.config.getSelectorClass());

        this.selector.init(
                this.config.getServers(),
                this.config.getConnetCount(),
                this.config.getTransportClient()
        );
    }

    public <T> T getProxy(Class<T> clazz){
        return (T) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{clazz}, new RemoteInvoker(clazz, encoder, decoder, selector ));
    }
}
