package co.uk.acme.query.statistics;

import co.uk.acme.fulfillment.ItemHasBeenMarkedAsPicked;
import co.uk.acme.fulfillment.SKU;
import co.uk.acme.hr.StoreAssistantHasBeenEmployed;
import co.uk.acme.shared.OrderId;
import co.uk.acme.shared.StoreAssistantId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * @author psamatt
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/springContext-test.xml")
public class AllTimeTopPickersProjectorTest {

    @Autowired
    private AllTimeTopPickersRepository repository;
    private AllTimeTopPickersProjector allTimeTopPickersProjector;

    @Before
    public void setUp() {
        repository.deleteAll();

        allTimeTopPickersProjector = new AllTimeTopPickersProjector();
        allTimeTopPickersProjector.setAllTimeTopPickersRepository(repository);
    }

    @Test
    public void it_should_create_read_model_on_new_employment() {
        final StoreAssistantId storeAssistantId = StoreAssistantId.generate();

        allTimeTopPickersProjector.on(new StoreAssistantHasBeenEmployed(storeAssistantId, "Bob James"));

        AllTimeTopPickers picker = repository.findOne(storeAssistantId.toString());

        assertThat(picker, is(equalTo(getReadModel(storeAssistantId.toString(), 0))));
    }

    @Test
    public void it_should_increment_counter_when_item_picked() {
        final StoreAssistantId storeAssistantId = StoreAssistantId.generate();
        final String storeAssistantName = "Bob James";

        allTimeTopPickersProjector.on(new StoreAssistantHasBeenEmployed(storeAssistantId, storeAssistantName));
        allTimeTopPickersProjector.on(new ItemHasBeenMarkedAsPicked(OrderId.generate(), new SKU("SKU123"), storeAssistantId, storeAssistantName));

        AllTimeTopPickers picker = repository.findOne(storeAssistantId.toString());

        assertThat(picker, is(equalTo(getReadModel(storeAssistantId.toString(), 1))));
    }

    private AllTimeTopPickers getReadModel(String storeAssistantId, int pickerCount) {
        AllTimeTopPickers picker = new AllTimeTopPickers(storeAssistantId);

        for (int i = 0; i < pickerCount; i++) {
            picker.increment();
        }

        return picker;
    }
}