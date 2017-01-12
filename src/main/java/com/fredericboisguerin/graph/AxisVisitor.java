package com.fredericboisguerin.graph;

/**
 * Created by fred on 11/01/17.
 */
public interface AxisVisitor {
    void visit(LinearAxis linearAxis);

    void visit(LogAxis logAxis);
}
