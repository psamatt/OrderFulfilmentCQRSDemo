package co.uk.acme.deliveryday;

/**
 * @author psamatt
 */
final public class NoCapacityLeftForDeliveryDay extends RuntimeException {
    public NoCapacityLeftForDeliveryDay() {
        super("No capacity left for day of choice");
    }
}
