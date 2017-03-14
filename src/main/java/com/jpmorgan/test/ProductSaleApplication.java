package com.jpmorgan.test;

import java.util.Collection;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by dmitrigu on 13.03.17.
 */
public class ProductSaleApplication {


    static LinkedBlockingQueue<SaleOrder> saleOrders = new LinkedBlockingQueue<SaleOrder>();
    ExecutorService executor = Executors.newCachedThreadPool();
    AtomicBoolean stoppedFlag = new AtomicBoolean(false);
    SaleServer server = new SaleServer(saleOrders, stoppedFlag);

    public static void main(String[] data) {
        System.out.println("\n**** Starting ProductSaleApplication ****");
        ProductSaleApplication tradeApplication = new ProductSaleApplication();
        tradeApplication.start();
    }

    public void start() {

        SaleOrder[] orders = SaleOrders.getSaleOrders();
        if (orders == null || orders.length == 0){
            System.out.println("\n**** no sail orders, exiting");
        }

        executor.execute(server);
        Future future ;
        SaleClient client = new SaleClient(1, saleOrders, stoppedFlag);
        future = executor.submit(client);
        try {
            future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println("**** Stopping server...");
        server.setFinished(true);
        try {
            executor.awaitTermination(5L, TimeUnit.SECONDS);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        executor.shutdown();
        System.out.println("**** Program finished...");
    }

    public SaleServer getServer() {
        return server;
    }

 }
