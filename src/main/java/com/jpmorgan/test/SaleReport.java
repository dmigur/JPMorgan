package com.jpmorgan.test;

import java.util.Map;

/**
 * Created by dmitrigu on 14.03.17.
 */
public class SaleReport {

    private Map<Product, Integer> resultAmount ;
    private Map<Product, Double> resultSum ;
    private Integer totalAmount ;
    private Double totalSum ;

    public SaleReport(Map<Product, Integer> resultAmount, Map<Product, Double> resultSum, Integer totalAmount, Double totalSum) {
        this.resultAmount = resultAmount;
        this.resultSum = resultSum;
        this.totalAmount = totalAmount;
        this.totalSum = totalSum;
    }

    public Map<Product, Integer> getResultAmount() {
        return resultAmount;
    }

    public Map<Product, Double> getResultSum() {
        return resultSum;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public Double getTotalSum() {
        return totalSum;
    }

    public String format() {
        StringBuffer buffer = new StringBuffer(String.format("%15s", "\n\nSale report"));

        if (resultAmount == null || resultSum == null) {
            buffer.append("\n");
            return buffer.toString();
        }

        buffer.append("\n");
        buffer.append(String.format("%50s", "---------------------------------------------------------------\n"));
        buffer.append(String.format("%25s", "Product name"));
        buffer.append(String.format("%15s", "Amount"));
        buffer.append(String.format("%15s", "Value"));
        buffer.append("\n");
        buffer.append(String.format("%50s", "---------------------------------------------------------------\n"));

        for (Product product : resultAmount.keySet()) {
            String str = "";
            str += String.format("%25s", product.name());
            str += String.format("%15s", resultAmount.get(product).toString());
            str += String.format("%15s", resultSum.get(product).toString());
            str += "\n";
            buffer.append(str);
        }
        buffer.append(String.format("%50s", "---------------------------------------------------------------\n"));
        buffer.append(String.format("%25s", "Total"));
        buffer.append(String.format("%15s", totalAmount.toString()));
        buffer.append(String.format("%15s", totalSum.toString()));
        buffer.append("\n");
        buffer.append(String.format("%50s", "---------------------------------------------------------------"));
        buffer.append("\n");
        return buffer.toString();
    }

}
