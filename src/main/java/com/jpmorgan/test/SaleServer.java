package com.jpmorgan.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by dmitrigu on 13.03.17.
 */
public class SaleServer implements Runnable {

    static final int INTERVAL_SAIL_REPORT = 10;
    static final int INTERVAL_CHANGE_REPORT = 50;
    static final int GET_TIMEOUT = 20;

    private LinkedBlockingQueue<SaleOrder> inSaleOrders;
    private List<SaleOrder> outSaleOrders = new ArrayList<SaleOrder>();
    private List<SaleOrder> outSaleCommands = new ArrayList<SaleOrder>();

    private AtomicBoolean stopped;
    private int intervalSaleReport = INTERVAL_SAIL_REPORT;
    private int intervalChangeReport = INTERVAL_CHANGE_REPORT;
    private boolean finished = false;
    List<SaleChangeReport> sailChangeReports = new ArrayList<SaleChangeReport>();
    List<SaleReport> sailReports = new ArrayList<SaleReport>();

    public SaleServer(LinkedBlockingQueue saleOrders, AtomicBoolean stopped) {
        this.inSaleOrders = saleOrders;
        this.stopped = stopped;

    }

    public void run() {

        System.out.println("**** Starting Sail Server");

        while (!finished) {

            try {
                int size = outSaleOrders.size() + outSaleCommands.size();

                if (size > 0 && (size % intervalSaleReport == 0)) {

                    SaleReport saleReport = Report.generateSaleReport(outSaleOrders);
                    sailReports.add(saleReport);
                    System.out.println(saleReport.format());

                }
                if (size > 0 && (size % intervalChangeReport == 0)) {
                    setStopReceiveNessages(true);
                    System.out.println("**** Stopping accepting messages...");

                    SaleChangeReport report = Report.generateChangesReport(outSaleCommands);
                    System.out.println(report.format());
                    sailChangeReports.add(report);


                    System.out.println("**** Resuming accepting messages...");
                    setStopReceiveNessages(false);

                }

                SaleOrder sail = null;
                sail = inSaleOrders.poll(GET_TIMEOUT, TimeUnit.SECONDS);

                if (sail == null) {
                    continue;
                }

                if (MessageType.ChangeSale.equals(sail.getMessageType())) {
                    setStopReceiveNessages(true);
                    transformSailCommand(sail);
                    setStopReceiveNessages(false);
                    //System.out.println("**** adding sail change commmand");
                    outSaleCommands.add(sail);
                } else {
                    //System.out.println("**** adding sail order");
                    outSaleOrders.add(sail);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void setStopReceiveNessages(boolean stopped) {
        this.stopped.set(stopped);
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    private void transformSailCommand(final SaleOrder sale) {
        if (outSaleOrders.isEmpty()) return;
        if (sale == null) return;

        for (SaleOrder saleOrder : outSaleOrders) {

            if (!sale.getProduct().equals(saleOrder.getProduct())) {
                continue;
            }
            switch (sale.getOperation()) {
                case Add:
                    saleOrder.setPrice(saleOrder.getPrice() + sale.getPrice());
                    break;
                case Subtract:
                    Double price = saleOrder.getPrice() - sale.getPrice();
                    price = price < 0 ? 0 : price;
                    saleOrder.setPrice(price);
                    break;
                case Multiply:
                    saleOrder.setPrice(saleOrder.getPrice() * sale.getPrice());
                    break;

            }
        }
        ;

    }

    public List<SaleOrder> getOutSaleOrders() {
        return outSaleOrders;
    }

    public List<SaleOrder> getOutSaleCommands() {
        return outSaleCommands;
    }

    public List<SaleChangeReport> getSailChangeReports() {
        return sailChangeReports;
    }

    public void setSailChangeReports(List<SaleChangeReport> sailChangeReports) {
        this.sailChangeReports = sailChangeReports;
    }

    public List<SaleReport> getSailReports() {
        return sailReports;
    }

    public void setSailReports(List<SaleReport> sailReports) {
        this.sailReports = sailReports;
    }

    public int getIntervalSailReport() {
        return intervalSaleReport;
    }

    public void setIntervalSaleReport(int intervalSailReport) {
        this.intervalSaleReport = intervalSailReport;
    }

    public int getIntervalChangeReport() {
        return intervalChangeReport;
    }

    public void setIntervalChangeReport(int intervalChangeReport) {
        this.intervalChangeReport = intervalChangeReport;
    }
}

