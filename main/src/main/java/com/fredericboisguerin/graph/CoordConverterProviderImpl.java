package com.fredericboisguerin.graph;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by fred on 11/01/17.
 */
public class CoordConverterProviderImpl implements CoordConverterProvider {
    @Override
    public Function<Coord, Coord> getConverter(Axis from, Axis to) {
        ToVisitor toVisitor = new ToVisitor();
        to.accept(toVisitor);

        FromVisitor fromVisitor = new FromVisitor(toVisitor);
        from.accept(fromVisitor);

        return fromVisitor.function;
    }

    private static class FromVisitor implements AxisVisitor {

        private final ToVisitor toVisitor;
        private Function<Coord, Coord> function;

        private FromVisitor(ToVisitor toVisitor) {
            this.toVisitor = toVisitor;
        }

        @Override
        public void visit(LinearAxis from) {
            function = coord -> toVisitor.linearCoordConverter.apply(from, coord);
        }

        @Override
        public void visit(LogAxis from) {
            throw new UnsupportedOperationException();
        }
    }

    private static class ToVisitor implements AxisVisitor {

        private BiFunction<LinearAxis, Coord, Coord> linearCoordConverter;

        @Override
        public void visit(LinearAxis to) {
            linearCoordConverter = (from, coord) -> convertFromLinearToLinearScale(to, from, coord);
        }

        @Override
        public void visit(LogAxis to) {
            linearCoordConverter = (from, coord) -> convertFromLinearToLogScale(to, from, coord);
        }
    }

    private static Coord convertFromLinearToLinearScale(LinearAxis to, LinearAxis from,
            Coord coord) {
        float temp = (coord.diff(from.getMin()) * to.getLength()) / from.getLength();
        Coord toMin = to.getMin();
        return toMin.translation(temp);
    }

    private static Coord convertFromLinearToLogScale(LogAxis to, LinearAxis from, Coord coord) {
        double temp = (coord.diff(from.getMin()) * Math.log(
                to.getMax().ratio(to.getMin()))) / from.getLength();
        double exp = Math.exp(temp + Math.log(to.getMin().getCoord()));
        float newValue = Double.valueOf(exp).floatValue();
        return Coord.of(newValue);
    }
}
