package com.jpmorgan.test;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by dmitrigu on 10.03.17.
 */
public class SaleClient implements Runnable {

    Queue saleOrders;
    AtomicBoolean stop;
    int index;

    public SaleClient(int i, Queue<SaleOrder> sailqueue, AtomicBoolean stop) {
        this.saleOrders = sailqueue;
        this.index = i;
        this.stop = stop;
    }

    @Override
    public void run() {

        System.out.println("**** Starting Client");

        for (SaleOrder saleOrder : SaleOrders.getSaleOrders()) {

            if (stop.get()) {
                System.out.println("**** Client " + index + " waiting...");
                while (stop.get()) {
                    try {
                        Thread.sleep(20);
                    } catch (Exception e) {
                    }
                }
                continue;
            } else {
                processCommand(saleOrder);
            }

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }

        System.out.println(" Client " + index + " finished  ");
    }

    private void processCommand(SaleOrder saleOrder) {

        try {
            System.out.println("**** processing message " + saleOrder);
            process(saleOrder);

        } catch (Exception e) {
            System.out.println("error saving sale");
        }
    }


    private void process(SaleOrder saleorder) {
        if (saleOrders == null) return;
        saleOrders.add(saleorder);
    }
}
