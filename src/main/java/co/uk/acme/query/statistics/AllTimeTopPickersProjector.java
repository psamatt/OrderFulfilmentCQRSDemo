package co.uk.acme.query.statistics;

import co.uk.acme.fulfillment.ItemHasBeenMarkedAsPicked;
import co.uk.acme.hr.StoreAssistantHasBeenEmployed;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author psamatt
 */
final public class AllTimeTopPickersProjector {

    private AllTimeTopPickersRepository repository;

    @Autowired
    public void setAllTimeTopPickersRepository(AllTimeTopPickersRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    void on(StoreAssistantHasBeenEmployed event) {
        AllTimeTopPickers picker = new AllTimeTopPickers(event.getStoreAssistantId().toString());
        repository.save(picker);
    }

    @EventHandler
    void on(ItemHasBeenMarkedAsPicked event) {
        AllTimeTopPickers picker = this.repository.findOne(event.getStoreAssistantId().toString());
        picker.increment();

        repository.save(picker);
    }

}
