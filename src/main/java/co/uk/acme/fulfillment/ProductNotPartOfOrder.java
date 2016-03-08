package co.uk.acme.fulfillment;

/**
 * @author psamatt
 */
final public class ProductNotPartOfOrder extends RuntimeException {

    public ProductNotPartOfOrder() {
        super("Product not part of order");
    }
}
