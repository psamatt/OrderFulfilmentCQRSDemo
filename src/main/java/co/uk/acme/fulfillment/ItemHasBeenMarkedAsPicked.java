package co.uk.acme.fulfillment;

import co.uk.acme.shared.OrderId;
import co.uk.acme.shared.StoreAssistantId;

/**
 * @author psamatt
 */
final public class ItemHasBeenMarkedAsPicked {

    final private OrderId orderId;
    final private SKU sku;
    final private StoreAssistantId storeAssistantId;
    final private String storeAssistantName;

    public ItemHasBeenMarkedAsPicked(OrderId orderId, SKU sku, StoreAssistantId storeAssistantId, String storeAssistantName) {
        this.orderId = orderId;
        this.sku = sku;
        this.storeAssistantId = storeAssistantId;
        this.storeAssistantName = storeAssistantName;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public SKU getSku() {
        return sku;
    }

    public StoreAssistantId getStoreAssistantId() {
        return storeAssistantId;
    }

    public String getStoreAssistantName() {
        return storeAssistantName;
    }
}
