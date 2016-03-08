package co.uk.acme.fulfillment;

import co.uk.acme.shared.OrderId;

import java.time.LocalDate;

/**
 * @author psamatt
 */
final public class RecordDeliveryDate {

    final private OrderId orderId;
    final private LocalDate deliveryDate;

    public RecordDeliveryDate(OrderId orderId, LocalDate deliveryDate) {
        this.orderId = orderId;
        this.deliveryDate = deliveryDate;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }
}
