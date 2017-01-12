package com.fredericboisguerin.graph;

/**
 * Created by fred on 11/01/17.
 */
public class LogAxis extends Axis {
    public LogAxis(Coord min, Coord max) {
        super(min, max);
    }

    @Override
    public void accept(AxisVisitor visitor) {
        visitor.visit(this);
    }
}
