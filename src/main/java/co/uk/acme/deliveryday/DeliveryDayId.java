package co.uk.acme.deliveryday;

import java.util.UUID;

/**
 * @author psamatt
 */
final public class DeliveryDayId {

    private UUID id;

    private DeliveryDayId(UUID id) {
        this.id = id;
    }

    public static DeliveryDayId generate() {
        return new DeliveryDayId(UUID.randomUUID());
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
