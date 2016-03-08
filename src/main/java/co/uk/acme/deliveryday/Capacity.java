package co.uk.acme.deliveryday;

/**
 * @author psamatt
 */
final public class Capacity {

    final private int amount;

    public Capacity(int amount) {

        if (amount < 0) {
            throw new IllegalArgumentException("Cannot have a negative capacity");
        }

        this.amount = amount;
    }

    public Capacity decrement() {
        return new Capacity(this.amount - 1);
    }

    public Capacity increment() {
        return new Capacity(this.amount + 1);
    }

    public int getAmount() {
        return this.amount;
    }

    public boolean isEmpty() {
        return this.amount == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Capacity capacity = (Capacity) o;

        return amount == capacity.amount;
    }

    @Override
    public int hashCode() {
        return amount;
    }


}
