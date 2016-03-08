package co.uk.acme.deliveryday;

import java.time.LocalDate;

/**
 * @author psamatt
 */
final public class DeliveryDayScheduled {

    private final DeliveryDayId id;
    private final Capacity capacity;
    private final LocalDate date;

    public DeliveryDayScheduled(DeliveryDayId id, Capacity capacity, LocalDate date) {
        this.id = id;
        this.capacity = capacity;
        this.date = date;
    }

    public DeliveryDayId getId() {
        return id;
    }

    public Capacity getCapacity() {
        return capacity;
    }

    public LocalDate getDate() {
        return date;
    }
}
