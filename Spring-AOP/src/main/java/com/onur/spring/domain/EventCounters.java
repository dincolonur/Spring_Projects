package com.onur.spring.domain;

public class EventCounters {

    private int callByNameCounter = 0;
    private int priceQueriedCounter = 0;
    private int ticketsBookedCounter = 0;

    public void setCallByNameCounter(int callByNameCounter) {
        this.callByNameCounter = callByNameCounter;
    }

    public void setPriceQueriedCounter(int priceQueriedCounter) {
        this.priceQueriedCounter = priceQueriedCounter;
    }

    public void setTicketsBookedCounter(int ticketsBookedCounter) {
        this.ticketsBookedCounter = ticketsBookedCounter;
    }

    public int getCallByNameCounter() {
        return this.callByNameCounter;
    }

    public int getPriceQueriedCounter() {
        return  this.priceQueriedCounter;
    }

    public int getTicketsBookedCounter() {
        return  this.ticketsBookedCounter;
    }
}
