package co.uk.acme.fulfillment;

/**
 * @author psamatt
 */
final public class DeliveryDateNotChosenForDispatch extends RuntimeException {

    public DeliveryDateNotChosenForDispatch() {
        super("Delivery Date not chosen for order to be dispatched");
    }
}
