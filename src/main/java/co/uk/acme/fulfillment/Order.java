package co.uk.acme.fulfillment;

import co.uk.acme.shared.CustomerId;
import co.uk.acme.shared.OrderId;
import co.uk.acme.shared.StoreAssistantId;
import co.uk.acme.shared.TelephoneNumber;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

/**
 * @author psamatt
 */
final public class Order extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private OrderId id;

    private List<SKU> toPickSKUs = new ArrayList<>();
    private boolean hasDeliveryDate = false;

    @SuppressWarnings("UnusedDeclaration")
    Order() {}

    public Order(OrderId orderId, CustomerId customerId, String customerName, TelephoneNumber telNumber, Map<SKU, String> products) {
        if (products.isEmpty()) {
            throw new OrderMustContainProducts(orderId);
        }

        this.id = orderId;

        apply(new OrderHasBeenPlaced(orderId, customerId, customerName, telNumber, products));
    }

    public static Order place(OrderId orderId, CustomerId customerId, String customerName, TelephoneNumber telNumber, Map<SKU, String> products) {
        return new Order(orderId, customerId, customerName, telNumber, products);
    }

    public void pickItem(SKU sku, StoreAssistantId storeAssistantId, String storeAssistantName) {

        if (!toPickSKUs.contains(sku)) {
            throw new ProductNotPartOfOrder();
        }

        apply(new ItemHasBeenMarkedAsPicked(id, sku, storeAssistantId, storeAssistantName));
    }

    public void recordDeliveryDate(LocalDate deliveryDate) {
        if (!toPickSKUs.isEmpty()) {
            throw new OrderHasItemsStillRequiredToBePicked();
        }

        apply(new DeliveryDateHasBeenChosen(id, deliveryDate));
    }

    public void markAsDispatched() {
        if (!hasDeliveryDate) {
            throw new DeliveryDateNotChosenForDispatch();
        }

        apply(new OrderMarkedAsDispatched(id));
    }

    @EventSourcingHandler
    private void handleOrderHasBeenPlaced(OrderHasBeenPlaced event) {
        id = event.getOrderId();

        event.getProducts().forEach((k, v) -> toPickSKUs.add(k));
    }

    @EventSourcingHandler
    private void handleItemHasBeenMarkedAsPicked(ItemHasBeenMarkedAsPicked event) {
        toPickSKUs.remove(event.getSku());
    }

    @EventSourcingHandler
    private void handleDeliveryDateHasBeenChosen(DeliveryDateHasBeenChosen event) {
        hasDeliveryDate = true;
    }
}
