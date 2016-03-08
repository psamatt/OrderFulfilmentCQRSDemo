package co.uk.acme.deliveryday;

import co.uk.acme.shared.OrderId;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

/**
 * @author psamatt
 */
final public class DeliveryDayCommandHandlerTest {

    private FixtureConfiguration<DeliveryDay> fixture;
    private DeliveryDayId deliveryDayId;
    private OrderId orderId;
    private LocalDate tomorrow;

    @Before
    public void setUp() {
        fixture = Fixtures.newGivenWhenThenFixture(DeliveryDay.class);
        DeliveryDayCommandHandler commandHandler = new DeliveryDayCommandHandler(fixture.getRepository());
        fixture.registerAnnotatedCommandHandler(commandHandler);

        deliveryDayId = DeliveryDayId.generate();
        orderId = OrderId.generate();
        tomorrow = LocalDate.now().plusDays(1);
    }

    @Test
    public void it_should_schedule_delivery_day() {

        Capacity capacity = new Capacity(20);

        fixture.given()
                .when(new ScheduleDeliveryDay(deliveryDayId, capacity, tomorrow))
                .expectVoidReturnType()
                .expectEvents(new DeliveryDayScheduled(deliveryDayId, capacity, tomorrow));
    }

    @Test
    public void it_should_reserve_slot_on_delivery_day() {

        fixture.given(new DeliveryDayScheduled(deliveryDayId, new Capacity(20), tomorrow))
                .when(new ReserveDeliverySlot(deliveryDayId, orderId))
                .expectVoidReturnType()
                .expectEvents(new DeliverySlotReserved(deliveryDayId, orderId));
    }

    @Test
    public void it_cannot_reserve_delivery_day_slot_with_no_capacity() {

        fixture.given(new DeliveryDayScheduled(deliveryDayId, new Capacity(0), tomorrow))
                .when(new ReserveDeliverySlot(deliveryDayId, orderId))
                .expectException(NoCapacityLeftForDeliveryDay.class);
    }

    @Test
    public void it_should_confirm_reserved_slot() {

        fixture.given(
                    new DeliveryDayScheduled(deliveryDayId, new Capacity(20), tomorrow),
                    new DeliverySlotReserved(deliveryDayId, orderId)
                ).when(new ConfirmDeliverySlot(deliveryDayId, orderId))
                .expectVoidReturnType()
                .expectEvents(new DeliverySlotConfirmed(deliveryDayId, orderId));
    }

    @Test
    public void it_cannot_confirm_unreserved_slot() {

        fixture.given(new DeliveryDayScheduled(deliveryDayId, new Capacity(20), tomorrow))
                .when(new ConfirmDeliverySlot(deliveryDayId, orderId))
                .expectException(DeliverySlotNotReserved.class);
    }

    @Test
    public void it_should_retract_reserved_slot() {

        fixture.given(
                    new DeliveryDayScheduled(deliveryDayId, new Capacity(20), tomorrow),
                    new DeliverySlotReserved(deliveryDayId, orderId)
                )
                .when(new RetractDeliverySlot(deliveryDayId, orderId))
                .expectVoidReturnType()
                .expectEvents(new DeliverySlotRetracted(deliveryDayId, orderId));
    }

    @Test
    public void it_should_retract_confirmed_slot() {

        fixture.given(
                    new DeliveryDayScheduled(deliveryDayId, new Capacity(20), tomorrow),
                    new DeliverySlotReserved(deliveryDayId, orderId),
                    new ConfirmDeliverySlot(deliveryDayId, orderId)
                )
                .when(new RetractDeliverySlot(deliveryDayId, orderId))
                .expectVoidReturnType()
                .expectEvents(new DeliverySlotRetracted(deliveryDayId, orderId));
    }

    @Test
    public void it_cannot_retract_unknown_slot() {

        fixture.given(
                    new DeliveryDayScheduled(deliveryDayId, new Capacity(20), tomorrow)
                )
                .when(new RetractDeliverySlot(deliveryDayId, orderId))
                .expectException(DeliverySlotNotReserved.class);
    }
}
