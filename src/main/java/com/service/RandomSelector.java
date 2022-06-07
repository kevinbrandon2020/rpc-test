package com.service;

import com.bean.Peer;
import com.transport.TransportClient;
import com.util.ReflictionUtil;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class RandomSelector implements TransportSelector {
    private CopyOnWriteArrayList<TransportClient> clients = new CopyOnWriteArrayList<>();
    private int count;

    @Override
    public void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz) {
        count = Math.max(count, 1);
        for (Peer peer : peers) {
            for (int j = 0; j < count; j++) {
                TransportClient client = ReflictionUtil.newInstance(clazz);
                client.connect(peer);
                clients.add(client);
            }
        }
    }

    @Override
    public TransportClient select() {
        int i = new Random().nextInt(clients.size());
        return clients.get(i);
    }

    @Override
    public void release(TransportClient client) {
        clients.add(client);
    }

    @Override
    public void close() {
        for (TransportClient client : clients) {
            client.close();
        }
        clients.clear();
    }
}
