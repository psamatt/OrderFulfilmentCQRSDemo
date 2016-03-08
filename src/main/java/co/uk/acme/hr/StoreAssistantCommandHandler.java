package co.uk.acme.hr;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author psamatt
 */
final public class StoreAssistantCommandHandler {

    private Repository<StoreAssistant> repository;

    @Autowired
    public StoreAssistantCommandHandler(Repository<StoreAssistant> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(EmployStoreAssistant command) {
        StoreAssistant storeAssistant = StoreAssistant.employ(command.getStoreAssistantId(), command.getName());
        repository.add(storeAssistant);
    }
}
