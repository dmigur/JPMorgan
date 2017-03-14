package com.jpmorgan.test;

/**
 * Created by dmitrigu on 13.03.17.
 */
public class SaleOrders {

    static SaleOrder[] saleOrders = {
            SaleOrder.createSingleSaleOrder(Product.Apple, 1.3),
            SaleOrder.createSingleSaleOrder(Product.Lemon, 1.5),
            SaleOrder.createSingleSaleOrder(Product.Orange, 2.0),
            SaleOrder.createSingleSaleOrder(Product.Banana, 1.5),
            SaleOrder.createSingleSaleOrder(Product.Plum, 2.0),

            SaleOrder.createSaleOrder(Product.Apple, 9, 1.3),
            SaleOrder.createSaleOrder(Product.Lemon, 9, 1.5),
            SaleOrder.createSaleOrder(Product.Orange, 9, 2.0),
            SaleOrder.createSaleOrder(Product.Banana, 9, 1.5),
            SaleOrder.createSaleOrder(Product.Plum, 9, 2.0),

            SaleOrder.createSaleOrder(Product.Apple, 20, 1.3),
            SaleOrder.createSaleOrder(Product.Lemon, 20, 1.5),
            SaleOrder.createSaleOrder(Product.Orange, 20, 2.0),
            SaleOrder.createSaleOrder(Product.Banana, 20, 1.5),
            SaleOrder.createSaleOrder(Product.Plum, 20, 1.5),

            SaleOrder.createAddChangeOrder(Product.Apple, 0.1),
            SaleOrder.createAddChangeOrder(Product.Lemon, 0.2),
            SaleOrder.createAddChangeOrder(Product.Orange, 0.3),
            SaleOrder.createAddChangeOrder(Product.Banana, 0.4),
            SaleOrder.createAddChangeOrder(Product.Plum, 0.4),

            SaleOrder.createSaleOrder(Product.Apple, 100, 1.3),
            SaleOrder.createSaleOrder(Product.Lemon, 100, 1.5),
            SaleOrder.createSaleOrder(Product.Orange, 200, 2.0),
            SaleOrder.createSaleOrder(Product.Banana, 100, 1.5),
            SaleOrder.createSaleOrder(Product.Plum, 100, 1.5),

            SaleOrder.createSubtractChangeOrder(Product.Apple, 1.1),
            SaleOrder.createSubtractChangeOrder(Product.Banana, 0.1),
            SaleOrder.createSubtractChangeOrder(Product.Orange, 0.1),
            SaleOrder.createSubtractChangeOrder(Product.Banana, 0.1),
            SaleOrder.createSubtractChangeOrder(Product.Plum, 0.1),

            SaleOrder.createSaleOrder(Product.Apple, 100, 1.3),
            SaleOrder.createSaleOrder(Product.Lemon, 100, 1.5),
            SaleOrder.createSaleOrder(Product.Orange, 200, 2.0),
            SaleOrder.createSaleOrder(Product.Banana, 100, 1.5),
            SaleOrder.createSaleOrder(Product.Plum, 100, 1.5),

            SaleOrder.createSaleOrder(Product.Apple, 100, 1.3),
            SaleOrder.createSaleOrder(Product.Lemon, 100, 1.5),
            SaleOrder.createSaleOrder(Product.Orange, 200, 2.0),
            SaleOrder.createSaleOrder(Product.Banana, 100, 1.5),
            SaleOrder.createSaleOrder(Product.Plum, 100, 1.5),

            SaleOrder.createSaleOrder(Product.Apple, 100, 1.3),
            SaleOrder.createSaleOrder(Product.Lemon, 100, 1.5),
            SaleOrder.createSaleOrder(Product.Orange, 200, 2.0),
            SaleOrder.createSaleOrder(Product.Banana, 100, 1.5),
            SaleOrder.createSaleOrder(Product.Plum, 100, 1.5),

            SaleOrder.createMultiplyChangeOrder(Product.Apple, 1.1),
            SaleOrder.createMultiplyChangeOrder(Product.Lemon, 1.2),
            SaleOrder.createMultiplyChangeOrder(Product.Orange, 2),
            SaleOrder.createMultiplyChangeOrder(Product.Banana, 2),
            SaleOrder.createMultiplyChangeOrder(Product.Plum, 2)

    };

    public static SaleOrder[] getSaleOrders() {
        return saleOrders;
    }

    public static void setSaleOrders(SaleOrder[] saleOrders) {
        SaleOrders.saleOrders = saleOrders;
    }
}
