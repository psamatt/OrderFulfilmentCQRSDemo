package co.uk.acme.fulfillment;

/**
 * @author psamatt
 */
final public class OrderHasItemsStillRequiredToBePicked extends RuntimeException {

    public OrderHasItemsStillRequiredToBePicked() {
        super("There are order items still required to be picked");
    }
}
