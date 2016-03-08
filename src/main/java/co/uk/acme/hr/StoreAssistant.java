package co.uk.acme.hr;

import co.uk.acme.shared.StoreAssistantId;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

/**
 * @author psamatt
 */
final public class StoreAssistant extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private StoreAssistantId id;

    @SuppressWarnings("UnusedDeclaration")
    StoreAssistant() {}

    public StoreAssistant(StoreAssistantId id, String name) {
        this.id = id;

        apply(new StoreAssistantHasBeenEmployed(id, name));
    }

    public static StoreAssistant employ(StoreAssistantId id, String name) {
        return new StoreAssistant(id, name);
    }

    @EventSourcingHandler
    private void handleStoreAssistantHasBeenEmployed(StoreAssistantHasBeenEmployed event) {
        this.id = event.getStoreAssistantId();
    }
}
