import com.jpmorgan.test.OperationType;
import com.jpmorgan.test.ProductSaleApplication;
import com.jpmorgan.test.SaleOrder;
import com.jpmorgan.test.SaleOrders;
import org.junit.Test;

import static com.jpmorgan.test.Product.Apple;
import static com.jpmorgan.test.Product.Orange;

/**
 * Created by dmitrigu on 13.03.17.
 */

public class TestSailOrders {

    @Test
    public void testCreateSingleSales() {
        SaleOrder saleOrder = SaleOrder.createSingleSaleOrder(Apple, 1.5);
        assert (saleOrder.getProduct() == Apple);
        assert (saleOrder.getAmount() == 1);
        assert (saleOrder.getPrice() == 1.5);
    }

    @Test
    public void testCreateSailes() {
        SaleOrder saleOrder = SaleOrder.createSaleOrder(Apple, 20, 1.5);
        assert (saleOrder.getProduct() == Apple);
        assert (saleOrder.getPrice() == 1.5);
        assert (saleOrder.getAmount() == 20L);
    }

    @Test
    public void testCreateChangeAdd() {
        SaleOrder changeOrder = SaleOrder.createAddChangeOrder(Apple, 1.5);
        assert (changeOrder.getProduct() == Apple);
        assert (changeOrder.getOperation() == OperationType.Add);
        assert (changeOrder.getPrice() == 1.5);
    }

    @Test
    public void testCreateChangeSubtract() {
        SaleOrder changeOrder = SaleOrder.createSubtractChangeOrder(Apple, 1.5);
        assert (changeOrder.getProduct() == Apple);
        assert (changeOrder.getPrice() == 1.5);
        assert (changeOrder.getOperation() == OperationType.Subtract);
    }

    @Test
    public void testCreateChangeMultiply() {
        SaleOrder changeOrder = SaleOrder.createMultiplyChangeOrder(Apple, 1.5);
        assert (changeOrder.getProduct() == Apple);
        assert (changeOrder.getPrice() == 1.5);
        assert (changeOrder.getOperation() == OperationType.Multiply);
    }

    @Test
    public void testSaleReports() {

        SaleOrder[] saleOrders = {
                SaleOrder.createSaleOrder(Orange, 100, 1.5),
                SaleOrder.createSaleOrder(Apple, 100, 1.5),
                SaleOrder.createAddChangeOrder(Orange, 0.5),
                SaleOrder.createAddChangeOrder(Apple, 0.5),
                SaleOrder.createMultiplyChangeOrder(Apple, 1.5),
                SaleOrder.createSubtractChangeOrder(Apple, 0.5)};


        SaleOrders.setSaleOrders(saleOrders);
        ProductSaleApplication application = new ProductSaleApplication();
        application.getServer().setIntervalSaleReport(6);
        application.getServer().setIntervalChangeReport(6);
        application.start();
        assert (application.getServer().getOutSaleCommands().size() == 4);
        assert (application.getServer().getOutSaleOrders().size() == 2);
        assert (application.getServer().getSailChangeReports().size() == 1);
        assert (application.getServer().getSailReports().size() == 1);
        assert (application.getServer().getSailReports().get(0).getTotalAmount() == 200);
        assert (application.getServer().getSailReports().get(0).getTotalSum() == 450);
    }


}
