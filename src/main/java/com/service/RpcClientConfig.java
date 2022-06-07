package com.service;

import com.bean.Peer;
import com.myjson.JsonDecoder;
import com.myjson.JsonEncoder;
import com.transport.HttpTransportClient;
import com.transport.TransportClient;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class RpcClientConfig {
    private Class<? extends TransportClient> transportClient = HttpTransportClient.class;
    private Class<JsonEncoder> encodeClass = JsonEncoder.class;
    private Class<JsonDecoder> decodeClass = JsonDecoder.class;
    private Class<? extends TransportSelector> selectorClass = RandomSelector.class;
    private int connetCount = 1;
    private List<Peer> servers = Arrays.asList(new Peer("127.0.0.1", 3000));

    public Class<? extends TransportClient> getTransportClient() {
        return transportClient;
    }

    public void setTransportClient(Class<? extends TransportClient> transportClient) {
        this.transportClient = transportClient;
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

    public Class<? extends TransportSelector> getSelectorClass() {
        return selectorClass;
    }

    public void setSelectorClass(Class<? extends TransportSelector> selectorClass) {
        this.selectorClass = selectorClass;
    }

    public int getConnetCount() {
        return connetCount;
    }

    public void setConnetCount(int connetCount) {
        this.connetCount = connetCount;
    }

    public List<Peer> getServers() {
        return servers;
    }

    public void setServers(List<Peer> servers) {
        this.servers = servers;
    }
}