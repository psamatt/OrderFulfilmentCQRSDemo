package co.uk.acme.hr;

import co.uk.acme.shared.StoreAssistantId;

/**
 * @author psamatt
 */
final public class EmployStoreAssistant {

    private final StoreAssistantId storeAssistantId;
    private final String name;

    public EmployStoreAssistant(StoreAssistantId storeAssistantId, String name) {
        this.storeAssistantId = storeAssistantId;
        this.name = name;
    }

    public StoreAssistantId getStoreAssistantId() {
        return storeAssistantId;
    }

    public String getName() {
        return name;
    }
}
