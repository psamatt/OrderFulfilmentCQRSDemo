package co.uk.acme.query.availableslots;

import co.uk.acme.deliveryday.*;
import co.uk.acme.shared.OrderId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.CoreMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * @author psamatt
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/springContext-test.xml")
public class AvailableSlotsForDayProjectorTest {

    @Autowired
    private AvailableSlotsForDayRepository repository;
    private AvailableSlotsForDayProjector projector;

    @Before
    public void setUp() {
        repository.deleteAll();

        projector = new AvailableSlotsForDayProjector();
        projector.setAvailableSlotsRepository(repository);
    }

    @Test
    public void it_should_schedule_slots_for_day() {
        LocalDate date = LocalDate.now();
        DeliveryDayId deliveryDayId = DeliveryDayId.generate();

        projector.on(new DeliveryDayScheduled(deliveryDayId, new Capacity(50), date));

        AvailableSlotsForDay slot = repository.findOne(deliveryDayId.toString());

        assertThat(slot, is(equalTo(getReadModel(deliveryDayId.toString(), date, 50))));
    }

    @Test
    public void it_should_decrement_capacity_on_reservation() {
        LocalDate date = LocalDate.now();
        DeliveryDayId deliveryDayId = DeliveryDayId.generate();

        projector.on(new DeliveryDayScheduled(deliveryDayId, new Capacity(50), date));
        projector.on(new DeliverySlotReserved(deliveryDayId, OrderId.generate()));

        AvailableSlotsForDay slot = repository.findOne(deliveryDayId.toString());

        assertThat(slot, is(equalTo(getReadModel(deliveryDayId.toString(), date, 49))));
    }

    @Test
    public void it_should_increment_capacity_on_slot_retracted() {
        LocalDate date = LocalDate.now();
        DeliveryDayId deliveryDayId = DeliveryDayId.generate();

        projector.on(new DeliveryDayScheduled(deliveryDayId, new Capacity(50), date));
        projector.on(new DeliverySlotRetracted(deliveryDayId, OrderId.generate()));

        AvailableSlotsForDay slot = repository.findOne(deliveryDayId.toString());

        assertThat(slot, is(equalTo(getReadModel(deliveryDayId.toString(), date, 51))));
    }

    public AvailableSlotsForDay getReadModel(String id, LocalDate date, int capacity) {
        return new AvailableSlotsForDay(id, date.toString(), capacity);
    }

}