package co.uk.acme.query.statistics;

import co.uk.acme.fulfillment.ItemHasBeenMarkedAsPicked;
import co.uk.acme.fulfillment.SKU;
import co.uk.acme.shared.OrderId;
import co.uk.acme.shared.StoreAssistantId;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author psamatt
 */
final public class StoreAssistantPickedItemProjector {

    private StoreAssistantPickedItemRepository repository;

    @Autowired
    public void setStoreAssistantPickedItemRepository(StoreAssistantPickedItemRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    void on(ItemHasBeenMarkedAsPicked event) {
        String id = buildId(event.getOrderId(), event.getSku(), event.getStoreAssistantId());

        StoreAssistantPickedItem mostPicked = new StoreAssistantPickedItem(id, event.getSku().toString(), event.getStoreAssistantId().toString(), event.getStoreAssistantName());
        this.repository.save(mostPicked);
    }

    protected String buildId(OrderId orderId, SKU sku, StoreAssistantId storeAssistantId) {
        StringBuilder idBuilder = new StringBuilder();
        idBuilder.append(orderId);
        idBuilder.append(sku);
        idBuilder.append(storeAssistantId);

        return idBuilder.toString();
    }
}
