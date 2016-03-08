package co.uk.acme.fulfillment;

import co.uk.acme.shared.OrderId;

/**
 * @author psamatt
 */
final public class OrderMarkedAsDispatched {

    private final OrderId id;

    public OrderMarkedAsDispatched(OrderId id) {
        this.id = id;
    }

    public OrderId getId() {
        return id;
    }
}
