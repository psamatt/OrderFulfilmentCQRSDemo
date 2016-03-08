package co.uk.acme.fulfillment;

/**
 * @author psamatt
 */
final public class SKU {

    final private String sku;

    public SKU(String sku) {
        this.sku = sku;
    }

    public String getSku() {
        return sku;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SKU sku1 = (SKU) o;

        return getSku().equals(sku1.getSku());

    }

    @Override
    public int hashCode() {
        return getSku().hashCode();
    }

    @Override
    public String toString() {
        return sku.toString();
    }
}
