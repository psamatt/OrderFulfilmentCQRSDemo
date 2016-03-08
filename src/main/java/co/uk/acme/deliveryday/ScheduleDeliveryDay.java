package co.uk.acme.deliveryday;

import java.time.LocalDate;

/**
 * @author psamatt
 */
final public class ScheduleDeliveryDay {

    final private DeliveryDayId deliveryDayId;
    final private Capacity capacity;
    final private LocalDate date;

    public ScheduleDeliveryDay(DeliveryDayId deliveryDayId, Capacity capacity, LocalDate date) {
        this.deliveryDayId = deliveryDayId;
        this.capacity = capacity;
        this.date = date;
    }

    public DeliveryDayId getDeliveryDayId() {
        return deliveryDayId;
    }

    public Capacity getCapacity() {
        return capacity;
    }

    public LocalDate getDate() {
        return date;
    }
}
