package com.transport;

import com.bean.Peer;
import com.bean.RequestHandler;

import java.io.InputStream;
import java.io.OutputStream;

public interface TransportServer {
    void init(int port, RequestHandler toResp);

    void start();

    void stop();
}
