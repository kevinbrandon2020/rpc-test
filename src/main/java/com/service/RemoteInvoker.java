package com.service;

import com.myjson.JsonDecoder;
import com.myjson.JsonEncoder;
import com.transport.TransportClient;
import org.apache.commons.io.IOUtils;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RemoteInvoker implements InvocationHandler {
    private Class clazz;
    private JsonEncoder encoder;
    private JsonDecoder decoder;
    private RandomSelector selector;

    public RemoteInvoker(Class clazz, JsonEncoder encoder, JsonDecoder decoder, RandomSelector selector) {
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder = decoder;
        this.selector = selector;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = new Request();
        request.setService(ServiceDescriptor.from(clazz, method));
        request.setParams(args);
        Response response = invokeRemote(request);
        if (response == null || response.getCode() != 0) {
            throw new IllegalStateException("fail to invoke");
        }
        return response.getData();
    }

    private Response invokeRemote(Request request) {
        TransportClient client = null;
        Response response = null;
        try {
            client = selector.select();
            byte[] encode = encoder.encode(request);
            InputStream in = client.write(new ByteArrayInputStream(encode));
            byte[] bytes = IOUtils.readFully(in, in.available());
            response = decoder.decode(bytes, Response.class);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (client != null) {
                selector.release(client);
            }
        }
        return response;
    }

}
