package com.jpmorgan.test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by dmitrigu on 13.03.17.
 */
public class SaleOrder {

    Product product;
    MessageType messageType;
    Integer amount;
    Double price;
    OperationType operation;

    private SaleOrder() {
    }

    @Override
    public String toString() {
        return "SaleOrder{" +
                "product=" + product +
                ", messageType=" + messageType +
                (amount!=null? ", amount=" + amount : "")+
                ", price=" + price +
                (operation != null ? ", operation=" + operation : "") +
                '}';
    }

    public static SaleOrder createSaleOrder(Product product, Integer amount, Double price) {
        SaleOrder saleOrder = new SaleOrder();
        saleOrder.setProduct(product);
        saleOrder.setMessageType(MessageType.MultipleSale);
        saleOrder.setPrice(price);
        saleOrder.setAmount(amount);
        return saleOrder;
    }

    public static SaleOrder createSingleSaleOrder(Product product, Double price) {
        SaleOrder saleOrder = new SaleOrder();
        saleOrder.setProduct(product);
        saleOrder.setMessageType(MessageType.SingleSale);
        saleOrder.setPrice(price);
        saleOrder.setAmount(1);
        return saleOrder;
    }

    public static SaleOrder createAddChangeOrder(Product product, Number change) {
        SaleOrder saleOrder = new SaleOrder();
        saleOrder.setProduct(product);
        saleOrder.setMessageType(MessageType.ChangeSale);
        saleOrder.setPrice(change.doubleValue());
        saleOrder.setOperation(OperationType.Add);
        return saleOrder;
    }

    public static SaleOrder createSubtractChangeOrder(Product product, Number change) {
        SaleOrder saleOrder = new SaleOrder();
        saleOrder.setProduct(product);
        saleOrder.setMessageType(MessageType.ChangeSale);
        saleOrder.setPrice(change.doubleValue());
        saleOrder.setOperation(OperationType.Subtract);
        return saleOrder;
    }

    public static SaleOrder createMultiplyChangeOrder(Product product, Number change) {
        SaleOrder saleOrder = new SaleOrder();
        saleOrder.setProduct(product);
        saleOrder.setMessageType(MessageType.ChangeSale);
        saleOrder.setPrice(change.doubleValue());
        saleOrder.setOperation(OperationType.Multiply);
        return saleOrder;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public Double getValue() {
        Double d = price * amount;
        return BigDecimal.valueOf(d).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public OperationType getOperation() {
        return operation;
    }

    public void setOperation(OperationType operation) {
        this.operation = operation;
    }
}
