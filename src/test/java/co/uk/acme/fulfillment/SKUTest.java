package co.uk.acme.fulfillment;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * @author psamatt
 */
public class SKUTest {

    @Test
    public void it_should_equal_same_sku() {
        SKU sku1 = new SKU("12345");
        SKU sku2 = new SKU("12345");

        assertThat(sku1, is(equalTo(sku2)));
    }

    @Test
    public void it_should_not_equal_different_sku() {
        SKU sku1 = new SKU("12345");
        SKU sku2 = new SKU("67890");

        assertThat(sku1, not(equalTo(sku2)));
    }
}