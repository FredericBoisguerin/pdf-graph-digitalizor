package com.fredericboisguerin.graph;

/**
 * Created by fred on 11/01/17.
 */
public class LinearAxis extends Axis {
    public LinearAxis(Coord min, Coord max) {
        super(min, max);
    }

    @Override
    public void accept(AxisVisitor visitor) {
        visitor.visit(this);
    }
}
