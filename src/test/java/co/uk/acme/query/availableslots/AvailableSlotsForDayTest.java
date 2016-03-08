package co.uk.acme.query.availableslots;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * @author psamatt
 */
public class AvailableSlotsForDayTest {

    private AvailableSlotsForDay slots;

    @Before
    public void setUp() {
        this.slots = new AvailableSlotsForDay("1", LocalDate.now().toString(), 20);
    }

    @Test
    public void it_should_decrement_capacity() {
        slots.decrementCapacity();

        assertEquals(19, slots.getCapacity());
    }

    @Test
    public void it_should_increment_capacity() {
        slots.incrementCapacity();

        assertEquals(21, slots.getCapacity());
    }
}