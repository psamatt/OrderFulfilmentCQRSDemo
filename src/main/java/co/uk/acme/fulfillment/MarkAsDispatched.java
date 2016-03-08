package co.uk.acme.fulfillment;

import co.uk.acme.shared.OrderId;

/**
 * @author psamatt
 */
final public class MarkAsDispatched {

    private final OrderId orderId;

    public MarkAsDispatched(OrderId orderId) {
        this.orderId = orderId;
    }

    public OrderId getOrderId() {
        return orderId;
    }
}
