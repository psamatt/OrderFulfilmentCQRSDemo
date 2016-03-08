package co.uk.acme.deliveryday;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author psamatt
 */
public class CapacityTest {

    @Test
    public void it_should_decrement_capacity_amount() {
        Capacity capacity = new Capacity(20);
        Capacity newCapacity = capacity.decrement();

        assertEquals(19, newCapacity.getAmount());
    }

    @Test
    public void it_should_allow_capacity_to_decrement_to_zero() {
        Capacity capacity = new Capacity(1);
        Capacity newCapacity = capacity.decrement();

        assertTrue(newCapacity.isEmpty());
        assertEquals(0, newCapacity.getAmount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void it_cannot_have_negative_amount() {
        Capacity capacity = new Capacity(0);
        capacity.decrement();
    }

    @Test
    public void it_should_increment_capacity_amount() {
        Capacity capacity = new Capacity(20);
        Capacity newCapacity = capacity.increment();

        assertEquals(21, newCapacity.getAmount());
    }
}