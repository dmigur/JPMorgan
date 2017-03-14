package com.jpmorgan.test;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by dmitrigu on 14.03.17.
 */
public class SaleChangeReport {

    public SaleChangeReport(Map<Product, Map<OperationType, List<Double>>> data) {
        this.data = data;
    }

    Map<Product, Map<OperationType, List<Double>>> data;

    public Map<Product, Map<OperationType, List<Double>>> getData() {
        return data;
    }

    public String format() {

        StringBuffer buf = new StringBuffer(String.format("\n\nChanges Report"));
        if (data == null) {
            buf.append("\n");
            return buf.toString();
        }

        buf.append("\n");
        buf.append(String.format("%60s", "---------------------------------------------------------------\n"));
        buf.append(String.format("%30s", "Product name"));
        buf.append(String.format("%30s", "Operation"));
        buf.append("\n");
        buf.append(String.format("%60s", "---------------------------------------------------------------\n"));
        for (Product product : data.keySet()) {
            StringBuffer productBuf = new StringBuffer();
            Map<OperationType, List<Double>> operations = data.get(product);
            if (operations != null) {
                for (OperationType operation : operations.keySet()) {
                    List<Double> operationValues = operations.get(operation);
                    if (operationValues != null) {
                        if (productBuf.length() == 0) {
                            productBuf.append(String.format("%30s", product.name()));
                        } else {
                            productBuf.append("\n");
                            productBuf.append(String.format("%30s", "--\"--"));
                        }
                        StringBuffer priceBuf = new StringBuffer();
                        for (Double value : operationValues) {
                            if (priceBuf.length() != 0) {
                                priceBuf.append("\n");
                                priceBuf.append(String.format("%30s", "--\"--"));
                            }
                            priceBuf.append(String.format("%30s", operation.name().toUpperCase() + " by " + value));
                        }
                        productBuf.append(priceBuf);

                    }
                }
            }
            buf.append(productBuf);
            buf.append("\n");
        }
        buf.append(String.format("%60s", "---------------------------------------------------------------\n"));
        return buf.toString();
    }

}
