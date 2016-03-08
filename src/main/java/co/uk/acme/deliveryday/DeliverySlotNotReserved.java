package co.uk.acme.deliveryday;

/**
 * @author psamatt
 */
final public class DeliverySlotNotReserved extends RuntimeException {
    public DeliverySlotNotReserved() {
        super("Delivery slot has not been reserved");
    }
}
