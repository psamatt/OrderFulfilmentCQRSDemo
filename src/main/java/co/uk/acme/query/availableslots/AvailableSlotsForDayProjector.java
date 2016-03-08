package co.uk.acme.query.availableslots;

import co.uk.acme.deliveryday.*;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author psamatt
 */
final public class AvailableSlotsForDayProjector {

    private AvailableSlotsForDayRepository repository;

    @Autowired
    public void setAvailableSlotsRepository(AvailableSlotsForDayRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    void on(DeliveryDayScheduled event) {
        AvailableSlotsForDay daySlots = new AvailableSlotsForDay(event.getId().toString(), event.getDate().toString(), event.getCapacity().getAmount());

        repository.save(daySlots);
    }

    @EventHandler
    void on(DeliverySlotReserved event) {
        AvailableSlotsForDay daySlots = repository.findOne(event.getDeliveryDayId().toString());
        daySlots.decrementCapacity();

        repository.save(daySlots);
    }

    @EventHandler
    void on(DeliverySlotRetracted event) {
        AvailableSlotsForDay daySlots = repository.findOne(event.getDeliveryDayId().toString());
        daySlots.incrementCapacity();

        repository.save(daySlots);
    }
}
