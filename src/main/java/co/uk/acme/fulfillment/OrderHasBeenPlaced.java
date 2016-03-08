package co.uk.acme.fulfillment;

import co.uk.acme.shared.CustomerId;
import co.uk.acme.shared.OrderId;
import co.uk.acme.shared.TelephoneNumber;

import java.util.Map;

/**
 * @author psamatt
 */
final public class OrderHasBeenPlaced {

    final private OrderId orderId;
    final private CustomerId customerId;
    final private String customerName;
    final private TelephoneNumber telNumber;
    final private Map<SKU, String> products;

    public OrderHasBeenPlaced(OrderId orderId, CustomerId customerId, String customerName, TelephoneNumber telNumber, Map<SKU, String> products) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.telNumber = telNumber;
        this.products = products;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public TelephoneNumber getTelNumber() {
        return telNumber;
    }

    public Map<SKU, String> getProducts() {
        return products;
    }
}
