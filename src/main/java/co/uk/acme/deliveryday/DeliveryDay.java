package co.uk.acme.deliveryday;

import co.uk.acme.shared.OrderId;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author psamatt
 */
final public class DeliveryDay extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private DeliveryDayId id;
    private Capacity capacity;
    private List<OrderId> reservations = new ArrayList<>();
    private List<OrderId> confirmations = new ArrayList<>();

    @SuppressWarnings("UnusedDeclaration")
    DeliveryDay() {}

    private DeliveryDay(DeliveryDayId id, Capacity capacity, LocalDate date) {
        this.id = id;

        apply(new DeliveryDayScheduled(id, capacity, date));
    }

    public static DeliveryDay schedule(DeliveryDayId id, Capacity capacity, LocalDate date) {
        return new DeliveryDay(id, capacity, date);
    }

    public void reserveSlot(OrderId orderId) {

        if (!reservations.contains(orderId) && !confirmations.contains(orderId)) {
            if (capacity.isEmpty()) {
                throw new NoCapacityLeftForDeliveryDay();
            }

            apply(new DeliverySlotReserved(id, orderId));
        }
    }

    public void retractSlot(OrderId orderId) {
        if (reservations.contains(orderId)) {
            apply(new DeliverySlotRetracted(id, orderId));
            return;
        }

        if (confirmations.contains(orderId)) {
            apply(new DeliverySlotRetracted(id, orderId));
            return;
        }

        throw new DeliverySlotNotReserved();
    }

    public void confirmSlot(OrderId orderId) {

        if (!confirmations.contains(orderId)) {
            if (reservations.contains(orderId)) {
                apply(new DeliverySlotConfirmed(id, orderId));
                return;
            }
        }

        throw new DeliverySlotNotReserved();
    }

    @EventSourcingHandler
    private void handleDayOfChoiceScheduled(DeliveryDayScheduled event) {
        this.id = event.getId();
        this.capacity = event.getCapacity();
    }

    @EventSourcingHandler
    private void handleDeliverySlotConfirmed(DeliverySlotConfirmed event) {
        this.reservations.remove(event.getOrderId());
        this.confirmations.add(event.getOrderId());
    }

    @EventSourcingHandler
    private void handleDeliverySlotReserved(DeliverySlotReserved event) {
        this.capacity = this.capacity.decrement();
        this.reservations.add(event.getOrderId());
    }

    @EventSourcingHandler
    private void handleDeliverySlotRetracted(DeliverySlotRetracted event) {
        this.capacity = this.capacity.increment();
        this.reservations.add(event.getOrderId());
    }
}