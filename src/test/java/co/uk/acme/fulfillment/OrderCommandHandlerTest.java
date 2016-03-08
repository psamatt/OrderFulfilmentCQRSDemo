package co.uk.acme.fulfillment;

import co.uk.acme.shared.CustomerId;
import co.uk.acme.shared.OrderId;
import co.uk.acme.shared.StoreAssistantId;
import co.uk.acme.shared.TelephoneNumber;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * @author psamatt
 */
final public class OrderCommandHandlerTest {

    private FixtureConfiguration<Order> fixture;
    private OrderId orderId = OrderId.generate();
    private CustomerId customerId = CustomerId.generate();
    private String customerName = "";
    private TelephoneNumber telNumber = new TelephoneNumber("01234567890");

    @Before
    public void setUp() {
        fixture = Fixtures.newGivenWhenThenFixture(Order.class);
        OrderCommandHandler commandHandler = new OrderCommandHandler(fixture.getRepository());
        fixture.registerAnnotatedCommandHandler(commandHandler);
    }

    @Test
    public void it_should_place_order_with_products() {

        fixture.given()
                .when(new PlaceOrder(orderId, customerId, customerName, telNumber, getXProducts(2)))
                .expectVoidReturnType()
                .expectEvents(new OrderHasBeenPlaced(orderId, customerId, customerName, telNumber, getXProducts(2)));
    }

    @Test
    public void it_should_not_place_order_with_no_products() {

        fixture.given()
                .when(new PlaceOrder(orderId, customerId, customerName, telNumber, getXProducts(0)))
                .expectException(OrderMustContainProducts.class);
    }

    @Test
    public void it_should_pick_item_of_order() {

        SKU sku = new SKU("SKU1");
        StoreAssistantId storeAssistantId = StoreAssistantId.generate();
        String storeAssistantName = "Joe Bloggs";

        fixture.given(new OrderHasBeenPlaced(orderId, customerId, customerName, telNumber, getXProducts(2)))
                .when(new MarkItemAsPicked(orderId, sku, storeAssistantId, storeAssistantName))
                .expectVoidReturnType()
                .expectEvents(new ItemHasBeenMarkedAsPicked(orderId, sku, storeAssistantId, storeAssistantName));
    }

    @Test
    public void it_cannot_pick_item_that_is_not_part_of_order() {

        SKU sku = new SKU("Non Existing SKU");
        StoreAssistantId storeAssistantId = StoreAssistantId.generate();
        String storeAssistantName = "Joe Bloggs";

        fixture.given(new OrderHasBeenPlaced(orderId, customerId, customerName, telNumber, getXProducts(2)))
                .when(new MarkItemAsPicked(orderId, sku, storeAssistantId, storeAssistantName))
                .expectException(ProductNotPartOfOrder.class);
    }

    @Test
    public void it_should_pick_all_items_before_choosing_delivery_date() {

        StoreAssistantId storeAssistantId = StoreAssistantId.generate();
        String storeAssistantName = "Joe Bloggs";
        LocalDate deliveryDate = LocalDate.now();

        fixture.given(
                    new OrderHasBeenPlaced(orderId, customerId, customerName, telNumber, getXProducts(2)),
                    new ItemHasBeenMarkedAsPicked(orderId, new SKU("SKU1"), storeAssistantId, storeAssistantName),
                    new ItemHasBeenMarkedAsPicked(orderId, new SKU("SKU2"), storeAssistantId, storeAssistantName)
                )
                .when(new RecordDeliveryDate(orderId, deliveryDate))
                .expectVoidReturnType()
                .expectEvents(new DeliveryDateHasBeenChosen(orderId, deliveryDate));
    }

    @Test
    public void it_cannot_choose_delivery_date_if_all_items_not_picked() {

        StoreAssistantId storeAssistantId = StoreAssistantId.generate();
        String storeAssistantName = "Joe Bloggs";

        fixture.given(
                    new OrderHasBeenPlaced(orderId, customerId, customerName, telNumber, getXProducts(2)),
                    new ItemHasBeenMarkedAsPicked(orderId, new SKU("SKU1"), storeAssistantId, storeAssistantName)
                )
                .when(new RecordDeliveryDate(orderId, LocalDate.now()))
                .expectException(OrderHasItemsStillRequiredToBePicked.class);
    }

    @Test
    public void it_should_mark_as_dispatched_when_delivery_date_has_been_chosen() {

        StoreAssistantId storeAssistantId = StoreAssistantId.generate();
        String storeAssistantName = "Joe Bloggs";
        LocalDate deliveryDate = LocalDate.now();

        fixture.given(
                    new OrderHasBeenPlaced(orderId, customerId, customerName, telNumber, getXProducts(2)),
                    new ItemHasBeenMarkedAsPicked(orderId, new SKU("SKU1"), storeAssistantId, storeAssistantName),
                    new ItemHasBeenMarkedAsPicked(orderId, new SKU("SKU2"), storeAssistantId, storeAssistantName),
                    new DeliveryDateHasBeenChosen(orderId, deliveryDate)
                )
                .when(new MarkAsDispatched(orderId))
                .expectVoidReturnType()
                .expectEvents(new OrderMarkedAsDispatched(orderId));
    }

    @Test
    public void it_cannot_dispatch_order_when_delivery_date_has_not_been_chosen() {

        StoreAssistantId storeAssistantId = StoreAssistantId.generate();
        String storeAssistantName = "Joe Bloggs";

        fixture.given(
                    new OrderHasBeenPlaced(orderId, customerId, customerName, telNumber, getXProducts(2)),
                    new ItemHasBeenMarkedAsPicked(orderId, new SKU("SKU1"), storeAssistantId, storeAssistantName),
                    new ItemHasBeenMarkedAsPicked(orderId, new SKU("SKU2"), storeAssistantId, storeAssistantName)
                )
                .when(new MarkAsDispatched(orderId))
                .expectException(DeliveryDateNotChosenForDispatch.class);
    }

    private HashMap<SKU, String> getXProducts(int numProducts) {
        HashMap<SKU, String> products = new HashMap<>();

        for (int i=0; i < numProducts; i++) {
            int productNumber = i+1;
            products.put(new SKU("SKU" + productNumber), "Product " + productNumber);
        }

        return products;
    }
}
