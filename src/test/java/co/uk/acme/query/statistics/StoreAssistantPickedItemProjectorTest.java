package co.uk.acme.query.statistics;

import co.uk.acme.fulfillment.ItemHasBeenMarkedAsPicked;
import co.uk.acme.fulfillment.SKU;
import co.uk.acme.shared.OrderId;
import co.uk.acme.shared.StoreAssistantId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author psamatt
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/springContext-test.xml")
public class StoreAssistantPickedItemProjectorTest {

    @Autowired
    private StoreAssistantPickedItemRepository repository;
    private StoreAssistantPickedItemProjector projector;

    @Before
    public void setUp() {
        repository.deleteAll();

        projector = new StoreAssistantPickedItemProjector();
        projector.setStoreAssistantPickedItemRepository(repository);
    }

    @Test
    public void it_should_record_picked_item_on_pick() {

        OrderId orderId = OrderId.generate();
        SKU sku = new SKU("123");
        StoreAssistantId storeAssistantId = StoreAssistantId.generate();
        String storeAssistantName = "Joe Bloggs";

        projector.on(new ItemHasBeenMarkedAsPicked(orderId, sku, storeAssistantId, storeAssistantName));

        StoreAssistantPickedItem item = repository.findOne(projector.buildId(orderId, sku, storeAssistantId));

        assertThat(item, is(equalTo(getReadModel(orderId, sku, storeAssistantId, storeAssistantName))));
    }

    private StoreAssistantPickedItem getReadModel(OrderId orderId, SKU sku, StoreAssistantId storeAssistantId, String storeAssistantName) {
        final String id = projector.buildId(orderId, sku, storeAssistantId);

        return new StoreAssistantPickedItem(id, sku.toString(), storeAssistantId.toString(), storeAssistantName);
    }
}