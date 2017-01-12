package com.fredericboisguerin.graph;

import java.util.function.Function;

/**
 * Created by fred on 11/01/17.
 */
public interface CoordConverterProvider {

    Function<Coord, Coord> getConverter(Axis from, Axis to);

}
