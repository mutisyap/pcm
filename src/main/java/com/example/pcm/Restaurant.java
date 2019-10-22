package com.example.pcm;

import java.util.LinkedList;
import java.util.Queue;

public class Restaurant {
    private Queue<Order> orders = new LinkedList<>();

    public Queue<Order> getOrders() {
        return orders;
    }

    public Order removeFromQueue() {
        return this.orders.poll();
    }

    public void addToQueue(Order order) {
        this.orders.add(order);
    }
}
