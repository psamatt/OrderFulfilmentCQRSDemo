package co.uk.acme.shared;

import java.util.UUID;

/**
 * @author psamatt
 */
final public class StoreAssistantId {

    private UUID id;

    public StoreAssistantId(UUID id) {
        this.id = id;
    }

    public static StoreAssistantId generate() {
        return new StoreAssistantId(UUID.randomUUID());
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
