package co.uk.acme.fulfillment;

import co.uk.acme.shared.CustomerId;
import co.uk.acme.shared.OrderId;
import co.uk.acme.shared.TelephoneNumber;

import java.util.Map;

/**
 * @author psamatt
 */
final public class PlaceOrder {

    final private OrderId orderId;
    final private CustomerId customerId;
    final private String customerName;
    final private TelephoneNumber phoneNumber;
    final private Map<SKU, String> products;

    public PlaceOrder(OrderId orderId, CustomerId customerId, String customerName, TelephoneNumber phoneNumber, Map<SKU, String> products) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
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

    public TelephoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public Map<SKU, String> getProducts() {
        return products;
    }
}
