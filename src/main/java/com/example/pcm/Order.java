package com.example.pcm;

import java.time.LocalDateTime;

public class Order {
    // a UUID referring to customer order 
    private String orderNumber;

    private String foodOrdered;

    private LocalDateTime arrivalTime;

    public Order(String orderNumber, String foodOrdered, LocalDateTime arrivalTime) {
        this.orderNumber = orderNumber;
        this.foodOrdered = foodOrdered;
        this.arrivalTime = arrivalTime;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getFoodOrdered() {
        return foodOrdered;
    }

    public void setFoodOrdered(String foodOrdered) {
        this.foodOrdered = foodOrdered;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "orderNumber='" + orderNumber + '\'' +
                ", foodOrdered='" + foodOrdered + '\'' +
                ", arrivalTime=" + arrivalTime +
                '}';
    }
}
