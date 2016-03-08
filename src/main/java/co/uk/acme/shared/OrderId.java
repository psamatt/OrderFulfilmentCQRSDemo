package co.uk.acme.shared;

import java.util.UUID;

/**
 * @author psamatt
 */
final public class OrderId {

    private UUID id;

    private OrderId(UUID id) {
        this.id = id;
    }

    public static OrderId generate() {
        return new OrderId(UUID.randomUUID());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderId orderId = (OrderId) o;

        return id.equals(orderId.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
