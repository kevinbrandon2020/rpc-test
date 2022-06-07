package com.example;

import com.bean.RequestHandler;
import com.bean.ServiceInstance;
import com.myjson.JsonDecoder;
import com.myjson.JsonEncoder;
import com.service.*;
import com.transport.TransportServer;
import com.util.ReflictionUtil;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class RpcServer {
    private RpcServerConfig config;
    private TransportServer net;
    private JsonEncoder jsonEncoder;
    private JsonDecoder jsonDecoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;

    public RpcServer(RpcServerConfig config) {
        this.config = config;
        this.net = ReflictionUtil.newInstance(config.getTransportClass());
        this.net.init(config.getPort(), this.handler);
        this.jsonEncoder = ReflictionUtil.newInstance(config.getEncodeClass());
        this.jsonDecoder = ReflictionUtil.newInstance(config.getDecodeClass());
        this.serviceManager =new ServiceManager();
        this.serviceInvoker =new ServiceInvoker();
    }

    public <T> void register(Class<T> interfaceClass, T bean){
        serviceManager.register(interfaceClass, bean);
    }

    public void start(){
        this.net.start();
    }

    public void stop(){
        this.net.stop();
    }

    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream recive, OutputStream toResp) {
            Response response = new Response();
            try {
                byte[] inBytes = IOUtils.readFully(recive, recive.available());
                Request request = jsonDecoder.decode(inBytes, Request.class);

                ServiceInstance lookup = serviceManager.lookup(request);
                Object invoke = serviceInvoker.invoke(lookup, request);
                response.setData(invoke);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    byte[] encode = jsonEncoder.encode(response);
                    toResp.write(encode);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
