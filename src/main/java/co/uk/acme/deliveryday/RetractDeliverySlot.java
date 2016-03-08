package co.uk.acme.deliveryday;

import co.uk.acme.shared.OrderId;

/**
 * @author psamatt
 */
final public class RetractDeliverySlot {

    final private DeliveryDayId deliveryDayId;
    final private OrderId orderId;

    public RetractDeliverySlot(DeliveryDayId deliveryDayId, OrderId orderId) {
        this.deliveryDayId = deliveryDayId;
        this.orderId = orderId;
    }

    public DeliveryDayId getDeliveryDayId() {
        return deliveryDayId;
    }

    public OrderId getOrderId() {
        return orderId;
    }
}
