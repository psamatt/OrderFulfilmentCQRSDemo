package co.uk.acme.fulfillment;

import co.uk.acme.shared.OrderId;

/**
 * @author psamatt
 */
final public class OrderMustContainProducts extends RuntimeException {

    public OrderMustContainProducts(OrderId orderId) {
        super("Empty order has been placed");
    }
}
