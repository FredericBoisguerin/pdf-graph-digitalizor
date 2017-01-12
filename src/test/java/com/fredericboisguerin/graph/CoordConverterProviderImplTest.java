package com.fredericboisguerin.graph;

import static junit.framework.Assert.assertEquals;

import java.util.function.Function;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by fred on 11/01/17.
 */
public class CoordConverterProviderImplTest {

    private CoordConverterProvider coordConverterProvider;

    @Before
    public void setUp() throws Exception {
        coordConverterProvider = new CoordConverterProviderImpl();
    }

    @Test
    public void should_convert_linear() throws Exception {
        Coord old = Coord.of(50);
        Axis from = new LinearAxis(Coord.of(0), Coord.of(100));
        Axis to = new LinearAxis(Coord.of(1), Coord.of(3));

        Function<Coord, Coord> converter = coordConverterProvider.getConverter(from, to);
        Coord actual = converter.apply(old);

        assertEquals(Coord.of(2), actual);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void should_throw_NotImplementedException_when_convert_from_logarithmic_axis()
            throws Exception {
        Axis from = new LogAxis(Coord.of(0), Coord.of(100));
        Axis to = new LinearAxis(Coord.of(1), Coord.of(3));

        coordConverterProvider.getConverter(from, to);
    }

    @Test
    public void should_convert_log_case_1() throws Exception {
        Coord old = Coord.of(50);
        Axis from = new LinearAxis(Coord.of(0), Coord.of(100));
        Axis to = new LogAxis(Coord.of(10), Coord.of(1000));

        Function<Coord, Coord> converter = coordConverterProvider.getConverter(from, to);
        Coord actual = converter.apply(old);

        assertEquals(Coord.of(100), actual);
    }

    @Test
    public void should_convert_log_case_2() throws Exception {
        Coord old = Coord.of(50);
        Axis from = new LinearAxis(Coord.of(0), Coord.of(100));
        Axis to = new LogAxis(Coord.of(100), Coord.of(10000));

        Function<Coord, Coord> converter = coordConverterProvider.getConverter(from, to);
        Coord actual = converter.apply(old);

        assertEquals(Coord.of(1000), actual);
    }

    @Test
    public void should_convert_log_case_3() throws Exception {
        Coord old = Coord.of(50);
        Axis from = new LinearAxis(Coord.of(0), Coord.of(100));
        Axis to = new LogAxis(Coord.of(1e9f), Coord.of(1e11f));

        Function<Coord, Coord> converter = coordConverterProvider.getConverter(from, to);
        Coord actual = converter.apply(old);

        assertEquals(Coord.of(1e10f), actual);
    }

}