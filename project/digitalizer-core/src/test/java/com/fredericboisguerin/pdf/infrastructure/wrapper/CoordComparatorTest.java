package com.fredericboisguerin.pdf.infrastructure.wrapper;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CoordComparatorTest {

    private static final int EQUAL = 0;
    private static final int GREATER_THAN = 1;

    @Test
    public void should_return_equal_constant_when_values_are_stricly_equal() throws Exception {
        int returnedValue = new CoordComparator().compare(1.23456f, 1.23456f);
        assertThat(returnedValue).isEqualTo(EQUAL);
    }

    @Test
    public void should_return_equal_constant_when_negative_values_are_stricly_equal() throws Exception {
        int returnedValue = new CoordComparator().compare(-1.23456f, -1.23456f);
        assertThat(returnedValue).isEqualTo(EQUAL);
    }

    @Test
    public void should_return_equal_constant_when_negative_values_are_close_by_ten_ppm() throws Exception {
        int returnedValue = new CoordComparator().compare(-1000f, -1000.01f);
        assertThat(returnedValue).isEqualTo(EQUAL);
    }

    @Test
    public void should_return_greaterThan_constant_when_negative_values_are_close_by_one_percent() throws Exception {
        int returnedValue = new CoordComparator().compare(-1000f, -1010f);
        assertThat(returnedValue).isEqualTo(GREATER_THAN);
    }

    @Test
    public void should_return_greaterThan_constant_when_negative_values_are_close_by_one_thousand_ppm() throws Exception {
        int returnedValue = new CoordComparator().compare(-1000f, -1001f);
        assertThat(returnedValue).isEqualTo(GREATER_THAN);
    }
}