package com.fredericboisguerin.pdf.graph;

import java.util.function.Function;

public interface CoordConverterProvider {

    Function<Coord, Coord> getConverter(Axis from, Axis to);

}
