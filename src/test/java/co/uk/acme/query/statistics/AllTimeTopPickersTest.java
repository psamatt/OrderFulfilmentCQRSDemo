package co.uk.acme.query.statistics;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author psamatt
 */
public class AllTimeTopPickersTest {

    private AllTimeTopPickers pickers;

    @Before
    public void setUp() {
        this.pickers = new AllTimeTopPickers("1");
    }

    @Test
    public void it_should_increment_amount() {
        this.pickers.increment();
        assertEquals(1, this.pickers.getCount());

        this.pickers.increment();
        assertEquals(2, this.pickers.getCount());

    }
}