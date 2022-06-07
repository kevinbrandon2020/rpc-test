package com.example;

import com.service.RpcServerConfig;

public class Server {
    public static void main(String[] args) {
        RpcServer rpcServer = new RpcServer(new RpcServerConfig());
        rpcServer.register(CalcService.class, new CalcServiceImp());
        rpcServer.start();
    }
}
