package co.uk.acme.query.employees;

import co.uk.acme.hr.StoreAssistantHasBeenEmployed;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author psamatt
 */
final public class EmployedStoreAssistantProjector {

    private EmployedStoreAssistantRepository repository;

    @Autowired
    public void setEmployedStoreAssistantRepository(EmployedStoreAssistantRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    void on(StoreAssistantHasBeenEmployed event) {
        EmployedStoreAssistant assistant = new EmployedStoreAssistant(event.getStoreAssistantId().toString(), event.getName());
        repository.save(assistant);
    }
}