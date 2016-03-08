package co.uk.acme.shared;

import java.util.UUID;

/**
 * @author psamatt
 */
final public class CustomerId {

    private UUID id;

    private CustomerId(UUID id) {
        this.id = id;
    }

    public static CustomerId generate() {
        return new CustomerId(UUID.randomUUID());
    }
}
